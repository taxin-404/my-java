#!/usr/bin/env bash
#
# fix-tmcbeans-scaling.sh
#
# Fixes "upscaled" / "broken pixel" text in TMCBeans (NetBeans 11.1 + OpenJDK 11)
# on Hyprland (Omarchy). Two root causes, one script:
#
#   1. GDK_SCALE=2 leaked into the session -> OpenJDK renders the UI at 2x on a
#      1x (non-HiDPI) display, so fonts/icons look twice as big.
#   2. JDK's XRender + subpixel (LCD) text AA pipeline garbles glyphs under
#      XWayland -> "broken pixel" text.
#
# Fixes applied:
#   - Force GDK_SCALE=1 in ~/.config/hypr/monitors.lua
#   - Point systemd --user session env at GDK_SCALE=1 (no re-login required)
#   - Create a user-level tmcbeans.desktop launching TMCBeans with:
#       -Dsun.java2d.xrender=false -Dawt.useSystemAAFontSettings=on -Dswing.aatext=true
#   - Hide the snap's duplicate TMCBeans desktop entry (NoDisplay=true)
#   - Reset TMCBeans' stale window layout (saved against a half-size screen)
#
# Idempotent: safe to run repeatedly.

set -euo pipefail

MONITORS_LUA="$HOME/.config/hypr/monitors.lua"
DESKTOP_FILE="$HOME/.local/share/applications/tmcbeans.desktop"
SNAP_ICON="/var/lib/snapd/snap/tmcbeans/current/tmcbeans/nb/tmcbeans.png"
NB_USERDIR="$HOME/.tmcbeans/11.1/config/Windows2Local"

echo "==> 1. Force GDK_SCALE=1 in $MONITORS_LUA"

if [[ -f "$MONITORS_LUA" ]]; then
    if grep -q 'omarchy_gdk_scale *= *2' "$MONITORS_LUA"; then
        cp "$MONITORS_LUA" "$MONITORS_LUA.bak.$(date +%s)"
        sed -i 's/omarchy_gdk_scale *= *2/omarchy_gdk_scale = 1/' "$MONITORS_LUA"
        echo "    omarchy_gdk_scale set to 1 (backup kept in $MONITORS_LUA.bak.*)"
    else
        echo "    omarchy_gdk_scale already 1 (or missing) - nothing to do"
    fi
else
    echo "    $MONITORS_LUA not found - skipping (customize your monitor config instead)"
fi

echo "==> 2. GDK_SCALE=1 for the current systemd --user session"

systemctl --user set-environment GDK_SCALE=1
echo "    systemd user env now: $(systemctl --user show-environment | grep '^GDK_SCALE=')"

echo "==> 3. TMCBeans launcher with text-rendering fixes"

mkdir -p "$HOME/.local/share/applications"

cat > "$DESKTOP_FILE" <<EOF
[Desktop Entry]
X-SnapInstanceName=tmcbeans
Name=TMCBeans
Comment=Integrated Development Environment
X-SnapAppName=tmcbeans
Exec=env GDK_SCALE=1 /var/lib/snapd/snap/bin/tmcbeans -J-Dsun.java2d.xrender=false -J-Dawt.useSystemAAFontSettings=on -J-Dswing.aatext=true %U
Icon=$SNAP_ICON
Categories=Development;IDE;Java;
Terminal=false
Type=Application
Keywords=development;Java;IDE;platform;javafx;javase;
StartupWMClass=NetBeans IDE 11.1
EOF
echo "    wrote $DESKTOP_FILE"

echo "==> 4. Hide the snap's duplicate TMCBeans desktop entry"

SNAP_DESKTOP="/var/lib/snapd/desktop/applications/tmcbeans_tmcbeans.desktop"
if [[ -f "$SNAP_DESKTOP" ]] && ! grep -q '^NoDisplay=.*true' "$SNAP_DESKTOP"; then
    if cp "$SNAP_DESKTOP" "$SNAP_DESKTOP.bak" 2>/dev/null && sed -i '1a NoDisplay=true' "$SNAP_DESKTOP" 2>/dev/null; then
        echo "    hid $SNAP_DESKTOP (backup at $SNAP_DESKTOP.bak; may reappear on 'snap refresh')"
    else
        echo "    !! could not edit $SNAP_DESKTOP (needs root) - run manually:"
        echo "       sudo sed -i '1a NoDisplay=true' $SNAP_DESKTOP"
    fi
else
    echo "    already hidden or not found - nothing to do"
fi

echo "==> 5. Reset stale TMCBeans window layout (half-size screen snapshot)"

if [[ -d "$NB_USERDIR" ]]; then
    timestamp=$(date +%s)
    backup="${NB_USERDIR}.bak.$timestamp"
    mv "$NB_USERDIR" "$backup"
    echo "    moved $NB_USERDIR -> $backup (regenerates on next launch)"
else
    echo "    no stale layout found - nothing to do"
fi

echo
echo "Done. Fully close and reopen TMCBeans."
echo "Reload Hyprland to pick up the new GDK_SCALE for freshly launched apps:"
echo "    hyprctl reload"
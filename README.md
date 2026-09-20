# Java Programming MOOC (mooc.fi) — Setup Guide for Arch Linux

Course: [Java Programming I & II](https://java-programming.mooc.fi/) — University of Helsinki, free, legacy course (no ECTS credits, but a completion certificate for each part).

This guide adapts the official install instructions for Arch Linux (Omarchy/Hyprland), since the official guide is written mainly for Windows/Mac/Ubuntu.

---

## What you need

1. A MOOC.fi account
2. Java JDK — **specifically version 11**
3. NetBeans with the Test My Code (TMC) plugin — bundled as **TMCBeans**

---

## Why JDK 11 specifically?

- **JDK** (Java Development Kit) = compiler (`javac`) + JVM (runs the code) + standard libraries. It's the full toolkit needed to write *and* run Java, unlike a JRE which can only run already-compiled programs.
- The course and TMCBeans were built and tested against **JDK 11**, an LTS (Long Term Support) release still widely used in industry — this isn't "old Java," just a stable target version.
- TMCBeans (the NetBeans+TMC bundle) is old and lightly maintained. Running it on a newer JDK is the single most common cause of it failing to start or breaking the test-submission pipeline.
- The Java *language fundamentals* taught in the course (variables, OOP, exceptions, collections, algorithms) are unchanged across Java versions — only the IDE tooling is version-sensitive.

---

## Setup steps

### 1. Install JDK 11
```bash
sudo pacman -S jdk11-openjdk
```
Do **not** use `jdk-openjdk` (rolling latest) for this course.

### 2. Set JDK 11 as the active Java
```bash
sudo archlinux-java set java-11-openjdk
java -version   # should print 11.x
```
Other JDKs can stay installed alongside it — `archlinux-java` just controls which one is active.

### 3. Install snapd (TMCBeans has no native Arch/AUR package)
```bash
yay -S snapd   # or: paru -S snapd
sudo systemctl enable --now snapd.socket
sudo ln -s /var/lib/snapd/snap /snap
```
A relog (or reboot) may be needed for snap's paths to take effect.

### 4. Install TMCBeans
```bash
sudo snap install tmcbeans --classic
```
Note: this snap hasn't been updated in a while, but it's the standard path for non-Ubuntu distros.

### 5. First launch — pick org and course
Open TMCBeans and select:
- Organization: **MOOC**
- Course: **Java Programming I** (not the 2019 course shown in the official demo video)

### 6. Create your MOOC.fi account and log in
Sign up at the top of the [course material](https://java-programming.mooc.fi/), then log in inside TMCBeans so it can sync and submit exercises.

---

## Fallback option: terminal-based workflow

If TMCBeans is flaky on Wayland/Hyprland, `tmc-cli` is a terminal client for the same TestMyCode grading system — lets you write code in Neovim and submit from the terminal instead of the NetBeans GUI. Caveat: the official course instructions and demo video assume the GUI, so support/troubleshooting resources assume TMCBeans.

---

## Troubleshooting: huge or "broken pixel" text in TMCBeans

On Hyprland/Wayland, TMCBeans (Java/XWayland) can open with fonts/icons scaled ~2x,
and/or text rendered with garbled "broken pixels". Both come from the session
environment leaking `GDK_SCALE=2` (OpenJDK treats it as a 2x display) and JDK's
XRender/subpixel text pipeline misbehaving under XWayland.

Run the bundled fixer script:

```bash
./fix-tmcbeans-scaling.sh
```

It forces `GDK_SCALE=1`, writes a user desktop launcher with
`-Dsun.java2d.xrender=false -Dawt.useSystemAAFontSettings=on -Dswing.aatext=true`,
resets TMCBeans' stale window layout, and reapplies the env to the current session.
Idempotent — safe to re-run. Fully close and reopen TMCBeans afterwards.

---

## Certificate note

- Completing each course (Java Programming I & II) gets you a **free certificate**, generated at https://www.mooc.fi/en/profile/completions.
- No exams are held anymore and no ECTS credits are awarded — for university credits, the current [Python Programming MOOC](https://programming-24.mooc.fi) is the active option.

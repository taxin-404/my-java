
import java.util.Scanner;

public class AgeOfTheOldest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int old = 0;
        while (true) {
            String info = scanner.nextLine();
            if (info.equals("")) {
                break;
            }
            String[] infos = info.split(",");
            if (old < Integer.valueOf(infos[1])) {
                old = Integer.valueOf(infos[1]);
            }
        }
        System.out.println("Age of the oldest: " + old);
    }
}

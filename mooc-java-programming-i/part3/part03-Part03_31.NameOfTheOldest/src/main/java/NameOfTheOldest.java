
import java.util.Scanner;

public class NameOfTheOldest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String old = "";
        int age = 0;
        while (true) {
            String info = scanner.nextLine();
            if (info.equals("")) {
                break;
            }
            String[] infos = info.split(",");
            if (age < Integer.valueOf(infos[1])) {
                age = Integer.valueOf(infos[1]);
                old = infos[0];
            }
        }
        System.out.println("Name of the oldest: " + old);
    }
}

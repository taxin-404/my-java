
import java.util.Scanner;

public class GradesAndPoints {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Give points [0-100]:");
        int num0 = Integer.valueOf(scan.nextLine());
        if (num0 < 0) {
            System.out.println("impossible!");
        } else if (num0 <= 49) {
            System.out.println("failed");
        } else if (num0 <= 59) {
            System.out.println("1");
        } else if (num0 <= 69) {
            System.out.println("2");
        } else if (num0 <= 79) {
            System.out.println("3");
        } else if (num0 <= 89) {
            System.out.println("4");
        } else if (num0 <= 100) {
            System.out.println("5");
        } else if (num0 > 100) {
            System.out.println("incredible!");
        }
    }
}

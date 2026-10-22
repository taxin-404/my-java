
import java.util.Scanner;

public class Factorial {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Give a number:");
        int nUm = Integer.valueOf(scanner.nextLine());
        int i = 1;
        int aDD = 1;

        while (i <= nUm) {
            aDD *= i;
            i++;
        }

        System.out.println("Factorial: " + aDD);
    }
}

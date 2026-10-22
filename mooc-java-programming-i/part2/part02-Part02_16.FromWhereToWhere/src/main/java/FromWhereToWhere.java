
import java.util.Scanner;

public class FromWhereToWhere {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Write your program here
        System.out.print("Where to?");
        int nuM = Integer.valueOf(scanner.nextLine());
        System.out.print("Where from?");
        int nuMl = Integer.valueOf(scanner.nextLine());
        for (int i = nuMl; i <= nuM; i++) {
            System.out.println(i);
        }
    }
}

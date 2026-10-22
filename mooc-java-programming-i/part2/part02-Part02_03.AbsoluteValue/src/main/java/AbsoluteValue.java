
import java.util.Scanner;

public class AbsoluteValue {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOne = Integer.valueOf(scanner.nextLine());
        if (numOne < 0) {
            System.out.println(numOne * -1);
        } else {
            System.out.println(numOne);
        }
    }
}

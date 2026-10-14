
import java.util.Scanner;

public class Squared {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int squa = Integer.valueOf(scanner.nextLine());
        squa = squa * squa;
        System.out.println(squa);
    }
}

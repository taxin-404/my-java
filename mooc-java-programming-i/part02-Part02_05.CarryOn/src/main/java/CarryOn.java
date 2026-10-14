
import java.util.Scanner;

public class CarryOn {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Shall we carry on?");
            String input = String.valueOf(scanner.nextLine());
            if (input.equals("no") || input.equals("NO") || input.equals("nO") || input.equals("No")) {
                break;
            }
        }
    }
}

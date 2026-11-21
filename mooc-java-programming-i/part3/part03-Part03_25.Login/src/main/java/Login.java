
import java.util.Scanner;

public class Login {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nam1 = "alex", nam2 = "emma", pas1 = "sunshine", pas2 = "haskell";
        System.out.print("Enter username: ");
        String id = scanner.nextLine();
        System.out.print("Enter password: ");
        String pass = scanner.nextLine();
        if ((nam1.equals(id) && pas1.equals(pass)) || (nam2.equals(id) && pas2.equals(pass))) {
            System.out.println("You have successfully logged in!");
        } else {
            System.out.println("Incorrect username or password!");
        }
    }
}

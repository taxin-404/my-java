
import java.util.Scanner;

public class Same {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Write your program here. 
        System.out.println("Enter the first string:");
        String textOne = scan.nextLine();
        System.out.println("Enter the second string:");
        String textTwo = scan.nextLine();
        if (textOne.equals(textTwo)) {
            System.out.println("Same");
        } else {
            System.out.println("Different");
        }
    }
}

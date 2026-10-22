
import java.util.Scanner;

public class SumOfASequence {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("First number?");
        int num1 = Integer.valueOf(scanner.nextLine()), i = 1, sUm = 0;

        while (i <= num1) {
            sUm += i;
            i++;
            
        }

        System.out.println("The sum is " + sUm);
    }
}
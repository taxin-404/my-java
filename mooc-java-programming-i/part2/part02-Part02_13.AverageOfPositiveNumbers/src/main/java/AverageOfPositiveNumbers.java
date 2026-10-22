
import java.util.Scanner;

public class AverageOfPositiveNumbers {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int inputs = 0;
        int sum = 0;
        while (true) {
            int number = Integer.valueOf(scanner.nextLine());
            if (number == 0) {
                if (sum == 0) {
                    System.out.println("Cannot calculate the average");
                } else if (sum > 0) {
                    System.out.println((double) sum / inputs);
                }
                break;
            }
            if (number > 0) {
                sum = number + sum;
                inputs = inputs + 1;
                continue;
            }
        }
    }
}

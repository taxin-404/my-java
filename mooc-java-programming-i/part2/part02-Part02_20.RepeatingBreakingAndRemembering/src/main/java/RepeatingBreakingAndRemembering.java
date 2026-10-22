
import java.util.Scanner;

public class RepeatingBreakingAndRemembering {

    public static void main(String[] args) {
        
        // This exercise is worth five exercise points, and it is 
        // gradually extended part by part.
        
        // If you want, you can send this exercise to the server
        // when it's just partially done. In that case the server will complain about 
        // the parts you haven't done, but you'll get points for the finished parts.
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give numbers:");
        int sUm = 0;
        int i = 0;
        double aVg = 0;
        int evEn = 0;
        int oDD = 0;
        while (true) {
            int nUm = Integer.valueOf(scanner.nextLine());
            if (nUm == -1) {
                aVg = (double) sUm / i;
                break;
            }
            if (nUm % 2 == 0) {
                evEn++;
            }
            if (nUm % 2 != 0) {
                oDD++;
            }
            i++;
            sUm += nUm;
        }
        System.out.println("Thx! Bye!");
        System.out.println("Sum: " + sUm);
        System.out.println("Numbers: " + i);
        System.out.println("Average: " + aVg);
        System.out.println("Even: " + evEn);
        System.out.println("Odd: " + oDD);
    }
}

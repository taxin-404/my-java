import java.util.Scanner;
public class VariousVariables {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // MODIFY THESE:

        int numberOfChicken = 9000;
        double baconWeight = 0.1;
        String tractor = "Zetor";
        // String input = scanner.nextLine();

        // DON'T MODIFY THESE:
        // System.out.println(input);
        System.out.println("Chicken:");
        System.out.println(numberOfChicken);
        System.out.println("Bacon (kg):");
        System.out.println(baconWeight);
        System.out.println("Tractor:");
        System.out.println(tractor);
        System.out.println("");
        System.out.println("And finally, a summary:");
        System.out.println(numberOfChicken);
        System.out.println(baconWeight);
        System.out.println(tractor);
    }
}

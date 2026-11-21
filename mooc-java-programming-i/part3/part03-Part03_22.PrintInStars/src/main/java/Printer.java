
public class Printer {

    public static void main(String[] args) {
        // You can test the method here
        int[] array = {5, 1, 3, 4, 2};
        printArrayInStars(array);
    }

    public static void printArrayInStars(int[] array) {
        // Write some code in here
        int count = 0;
        while (count < array.length) {
            for (int w = 0; w < array[count]; w++) {
                if (w == array[count] - 1) {
                    System.out.println("*");
                    break;
                }
                System.out.print("*");
            }
            count++;
        }
    }

}

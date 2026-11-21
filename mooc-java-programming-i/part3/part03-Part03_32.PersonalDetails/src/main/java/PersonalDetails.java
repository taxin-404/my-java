import java.util.Scanner;

public class PersonalDetails {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = "";
        int sum = 0, count = 0;
        while (true) {
            String text = scanner.nextLine();
            if (text.equals("")) {
                break;
            }
            String[] word = text.split(",");
            if (name.length() < word[0].length()) {
                name = word[0];
            }
            sum += Integer.valueOf(word[1]);
            count++;
        }
        if (count > 0) {
            System.out.println("Longest name: " + name);
            System.out.println("Average of the birth years: " + ((double) sum / count));
        }
    }
}

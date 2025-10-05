
import java.util.*;

public class DisariumNumber {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter a number: ");
            int a = sc.nextInt();

            if (isDisarium(a)) {
                System.out.println("Disarium");
            } else {
                System.out.println("Not Disarium");
            }
        }
    }

    public static boolean isDisarium(int n) {
        int sum = 0;
        String numStr = String.valueOf(n);

        for (int i = 0; i < numStr.length(); i++) {
            int digit = numStr.charAt(i) - '0';
            sum += (int) Math.pow(digit, i + 1);
        }

        return sum == n;
    }
}


import java.util.*;

class NarcissisticChecker {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter a number: ");
            int a = sc.nextInt();

            if (isNarcissistic(a)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }

    static boolean isNarcissistic(int n) {
        int original_num = n, m = n, sum = 0, numDigits = 0;

        while (m > 0) {
            numDigits++;
            m /= 10;
        }

        while (n > 0) {
            int temp = n % 10;
            sum += (int) Math.pow(temp, numDigits);
            n /= 10;
        }

        return sum == original_num;
    }
}

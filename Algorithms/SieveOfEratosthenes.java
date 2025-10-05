import java.util.Scanner;

/**
 * Program Title: Sieve of Eratosthenes Prime Number Generator
 * Author: [Sreenivasulu-03]
 * Date: 2025-10-05
 *
 * Description: This program generates all prime numbers up to a user-specified
 * limit using the Sieve of Eratosthenes algorithm. It demonstrates arrays,
 * loops, and basic algorithm implementation in Java.
 * 
 * Time Complexity: O(n log log n)
 * Space Complexity: O(n)
 */
public class SieveOfEratosthenes {

    /**
     * Generates and prints all prime numbers up to the specified limit.
     * @param limit The upper bound to generate prime numbers.
     */
    public static void generatePrimes(int limit) {
        if (limit < 2) {
            System.out.println("There are no prime numbers less than 2.");
            return;
        }

        boolean[] isPrime = new boolean[limit + 1];

        // Initialize all entries as true. True means the number is prime
        for (int i = 2; i <= limit; i++) {
            isPrime[i] = true;
        }

        // Mark multiples of each prime number as non-prime
        for (int i = 2; i * i <= limit; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        // Print all prime numbers
        System.out.println("Prime numbers up to " + limit + ":");
        for (int i = 2; i <= limit; i++) {
            if (isPrime[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the upper limit n: ");

        while (!sc.hasNextInt()) {
            System.out.print("Invalid input! Enter a numeric value: ");
            sc.next();
        }
        int n = sc.nextInt();

        generatePrimes(n);
        sc.close();
    }
}

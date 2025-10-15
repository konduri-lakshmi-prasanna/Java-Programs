/**
 * RabinKarp.java
 *
 * Program to implement the Rabin-Karp String Matching Algorithm.
 * This algorithm uses hashing to efficiently find a pattern in a text.
 *
 * Example:
 * Input:
 *   Text: "ABCCDDAEFG"
 *   Pattern: "CDD"
 * Output:
 *   Pattern found at index 3
 *
 * Time Complexity: O(n + m) on average
 * Space Complexity: O(1)
 */

import java.util.*;

public class RabinKarp {

    // A large prime number for hashing
    static final int PRIME = 101;
    // The number of possible characters (assuming extended ASCII)
    static final int CHAR_SIZE = 256;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter the text: ");
            String text = sc.nextLine();

            System.out.print("Enter the pattern: ");
            String pattern = sc.nextLine();

            search(text, pattern);
        }
    }

    /**
     * Rabin-Karp search function
     * @param text - The text string to be searched
     * @param pattern - The pattern string to find
     */
    public static void search(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        if (m > n) {
            System.out.println("Pattern length cannot be greater than text length.");
            return;
        }

        int patternHash = 0;  // Hash value for pattern
        int textHash = 0;     // Hash value for current text window
        int h = 1;

        // The value of h = (CHAR_SIZE^(m-1)) % PRIME
        for (int i = 0; i < m - 1; i++) {
            h = (h * CHAR_SIZE) % PRIME;
        }

        // Calculate hash value for pattern and first window of text
        for (int i = 0; i < m; i++) {
            patternHash = (CHAR_SIZE * patternHash + pattern.charAt(i)) % PRIME;
            textHash = (CHAR_SIZE * textHash + text.charAt(i)) % PRIME;
        }

        // Slide the pattern over text one by one
        for (int i = 0; i <= n - m; i++) {
            // If the hash values match, check characters one by one
            if (patternHash == textHash) {
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        match = false;
                        break;
                    }
                }

                if (match) {
                    System.out.println("Pattern found at index " + i);
                }
            }

            // Calculate hash value for next window of text
            if (i < n - m) {
                textHash = (CHAR_SIZE * (textHash - text.charAt(i) * h) + text.charAt(i + m)) % PRIME;

                // Ensure positive hash value
                if (textHash < 0) {
                    textHash += PRIME;
                }
            }
        }
    }
}

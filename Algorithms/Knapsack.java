// Knapsack Problem using Dynamic Programming
public class Knapsack {

    public static int knapsack(int capacity, int[] weights, int[] values, int n) {
        // Create a 2D array to store the maximum value at each n and weight combination
        int[][] dp = new int[n + 1][capacity + 1];

        // Build the dp array in bottom-up manner
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                }

                else if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], values[i - 1] + dp[i - 1][w - weights[i - 1]]);
                }
                
                else {
                    
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[n][capacity];
    }

    public static void main(String[] args) {
        int[] weights = {1, 3, 4, 5};
        int[] values = {1, 4, 5, 7};
        int capacity = 7;
        int n = weights.length; 

        // Function call
        int maxValue = knapsack(capacity, weights, values, n);
        System.out.println("Maximum value in Knapsack = " + maxValue);
    }
}

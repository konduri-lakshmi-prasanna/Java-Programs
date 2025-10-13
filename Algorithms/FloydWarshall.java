
/**
 * Program: Floyd-Warshall Algorithm
 * Description:
 * The Floyd-Warshall algorithm is a dynamic programming approach used to find 
 * the shortest paths between all pairs of vertices in a weighted graph.
 * 
 * It can handle positive and negative edge weights (but not negative cycles).
 *
 * Example:
 * Input: 4 vertices, adjacency matrix with edge weights
 * Output: Shortest path matrix showing minimum distances between every pair
 *
 * Author: Joyston Monteiro
 * Contribution: Hacktoberfest 2025
 */

import java.util.*;

public class FloydWarshall {

    // Define infinity as a large value
    static final int INF = 99999;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();

        int[][] graph = new int[n][n];
        System.out.println("Enter adjacency matrix (use 99999 for no edge):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        floydWarshall(graph, n);
        sc.close();
    }

    // Core algorithm
    public static void floydWarshall(int[][] graph, int n) {
        int[][] dist = new int[n][n];

        // Initialize distance matrix
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                dist[i][j] = graph[i][j];

        // Dynamic Programming logic
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }

        printSolution(dist, n);
    }

    // Display the result
    public static void printSolution(int[][] dist, int n) {
        System.out.println("\nShortest distances between every pair of vertices:");
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (dist[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }
}

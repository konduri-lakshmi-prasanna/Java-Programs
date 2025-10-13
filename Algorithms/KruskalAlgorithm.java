/**
 * Program Title: Kruskal's Algorithm for MST
 * Author: Pranav-Ijantkar
 * Date: 2025-10-13
 *
 * Description: Finds the Minimum Spanning Tree (MST) of a weighted, undirected graph 
 * using Kruskal's greedy algorithm, optimized with Disjoint Set Union (DSU) by rank and path compression.
 *
 * Language: Java
 *
 * Time Complexity: O(E log E) or O(E log V)
 * Space Complexity: O(V + E)
 */

import java.util.*;

// Represents an edge in the graph
class Edge implements Comparable<Edge> 
{
    int src, dest, weight;

    public Edge(int src, int dest, int weight) 
    {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    // Used for sorting edges based on their weight
    @Override
    public int compareTo(Edge otherEdge) 
    {
        return this.weight - otherEdge.weight;
    }

    @Override
    public String toString() 
    {
        return "[" + src + " - " + dest + ", weight=" + weight + "]";
    }
}

public class KruskalAlgorithm 
{

    private int V; // Number of vertices
    private List<Edge> edges; // List of all edges in the graph

    public KruskalAlgorithm(int v) 
    {
        V = v;
        edges = new ArrayList<>();
    }

    // Function to add an edge to the graph
    public void addEdge(int src, int dest, int weight) 
    {
        edges.add(new Edge(src, dest, weight));
    }

    // Disjoint Set Union (DSU) find operation
    // Finds the representative (or root) of the set containing element i
    private int find(int[] parent, int i) 
    {
        if (parent[i] == i) return i;
        
        // Path compression for efficiency
        return parent[i] = find(parent, parent[i]);
    }

    // Disjoint Set Union (DSU) union operation
    // Unites the sets that contain x and y
    private void union(int[] parent, int[] rank, int x, int y) 
    {
        int rootX = find(parent, x);
        int rootY = find(parent, y);

        // Union by rank for efficiency
        if (rank[rootX] < rank[rootY]) parent[rootX] = rootY;
        else if (rank[rootY] < rank[rootX]) parent[rootY] = rootX;
        else 
        {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }

    // The main function to construct MST using Kruskal's algorithm
    public void findMST() 
    {
        List<Edge> resultMST = new ArrayList<>();
        int edgeCount = 0;
        int i = 0;

        // Step 1: Sort all the edges in non-decreasing order of their weight.
        Collections.sort(edges);

        // Allocate memory for creating V subsets for DSU
        int[] parent = new int[V];
        int[] rank = new int[V];
        for (int j = 0; j < V; j++) 
        {
            parent[j] = j; // Initially, each vertex is in its own set
            rank[j] = 0;
        }

        // Step 2: Iterate through sorted edges
        // The number of edges in MST is V-1
        while (edgeCount < V - 1 && i < edges.size()) 
        {
            Edge nextEdge = edges.get(i++);

            int rootSrc = find(parent, nextEdge.src);
            int rootDest = find(parent, nextEdge.dest);

            // Step 3: If including this edge doesn't cause a cycle, include it.
            // A cycle is formed if both vertices of an edge are in the same set.
            if (rootSrc != rootDest) 
            {
                resultMST.add(nextEdge);
                union(parent, rank, rootSrc, rootDest);
                edgeCount++;
            }
        }

        // Print the resulting MST
        System.out.println("Edges in the Minimum Spanning Tree:");
        int totalWeight = 0;
        for (Edge edge : resultMST) 
        {
            System.out.println(edge);
            totalWeight += edge.weight;
        }
        System.out.println("Total weight of MST: " + totalWeight);
    }

    public static void main(String[] args) 
    {
        int V = 4; // Number of vertices
        KruskalAlgorithm graph = new KruskalAlgorithm(V);

        // Adding edges to the graph
        // (source, destination, weight)
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 4);

        // Function call to find the MST
        graph.findMST();
    }
}

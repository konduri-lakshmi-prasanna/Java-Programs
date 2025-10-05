import java.util.*;

class Dijkstra {

    static class Edge {
        int to, weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class Node {
        int vertex, dist;
        Node(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }
    }

    static void dijkstra(List<List<Edge>> adj, int source) {
        int n = adj.size();
        int[] dist = new int[n];
        int[] parent = new int[n]; // for path reconstruction
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        dist[source] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.dist));
        pq.offer(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int u = curr.vertex;

            if (curr.dist > dist[u]) continue;

            for (Edge edge : adj.get(u)) {
                int v = edge.to;
                int weight = edge.weight;

                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    parent[v] = u; // keep track of the path
                    pq.offer(new Node(v, dist[v]));
                }
            }
        }

        // Print shortest distances and paths
        System.out.println("\nShortest distances from node " + source + ":");
        for (int i = 0; i < n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("To " + i + " = INF (unreachable)");
            } else {
                System.out.print("To " + i + " = " + dist[i] + " | Path: ");
                printPath(i, parent);
                System.out.println();
            }
        }
    }

    static void printPath(int node, int[] parent) {
        if (node == -1) return;
        Stack<Integer> path = new Stack<>();
        while (node != -1) {
            path.push(node);
            node = parent[node];
        }
        while (!path.isEmpty()) {
            System.out.print(path.pop());
            if (!path.isEmpty()) System.out.print(" -> ");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int edges = sc.nextInt();

        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        System.out.println("Enter edges (u v weight):");
        for (int i = 0; i < edges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            adj.get(u).add(new Edge(v, w)); 
            // For undirected graph, uncomment next line:
            // adj.get(v).add(new Edge(u, w));
        }

        System.out.print("Enter starting node: ");
        int source = sc.nextInt();

        dijkstra(adj, source);
    }
}

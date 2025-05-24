import java.util.*;

// Implements the Ford-Fulkerson algorithm using BFS (Edmonds-Karp variant)
public class MaxFlow {
    // Computes the maximum flow from source (s) to sink (t)
    public static int fordFulkerson(FlowNetwork graph, int s, int t) {
        int maxFlow = 0;
        int[] parent = new int[graph.numNodes]; // To store the path found by BFS
        // Repeat until no more augmenting paths are found
        while (bfs(graph, s, t, parent)) {
            int pathFlow = Integer.MAX_VALUE;
            int v = t;
            // Find the bottleneck capacity in the path
            while (v != s) {
                int u = parent[v];
                Edge forward = findEdge(graph, u, v);
                pathFlow = Math.min(pathFlow, forward.residualCapacity());
                v = u;
            }
            // Add flow to all edges along the path
            v = t;
            while (v != s) {
                int u = parent[v];
                Edge forward = findEdge(graph, u, v);
                forward.addFlow(pathFlow);
                v = u;
            }
            // Add the path flow to total max flow
            maxFlow += pathFlow;

        }

        return maxFlow;
    }
    // Uses BFS to find an augmenting path from s to t
    private static boolean bfs(FlowNetwork graph, int s, int t, int[] parent) {
        boolean[] visited = new boolean[graph.numNodes];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;
        // Standard BFS loop
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (Edge edge : graph.getAdjList()[u]) {
                // Visit neighbor if residual capacity is available and not visited yet
                if (!visited[edge.to] && edge.residualCapacity() > 0) {
                    queue.add(edge.to);
                    parent[edge.to] = u;
                    visited[edge.to] = true;
                }
            }
        }
        // If sink was reached, a path exists
        return visited[t];
    }
    // Finds the edge object from 'from' to 'to' in the adjacency list
    private static Edge findEdge(FlowNetwork graph, int from, int to) {
        for (Edge edge : graph.getAdjList()[from]) {
            if (edge.to == to) return edge;
        }
        return null;// Edge not found (should not happen if BFS built the path)
    }
}

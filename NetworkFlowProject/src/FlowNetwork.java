import java.util.*;

// Represents the entire flow network using an adjacency list
public class FlowNetwork {
    int numNodes; // Total number of nodes in the network
    List<Edge>[] adjList; // Adjacency list to store edges for each node


    // Constructor to initialize the network with a given number of nodes
    @SuppressWarnings("unchecked")
    public FlowNetwork(int numNodes) {
        this.numNodes = numNodes;
        adjList = new ArrayList[numNodes];
        for (int i = 0; i < numNodes; i++) {
            adjList[i] = new ArrayList<>(); // Initialize list for each node
        }
    }
    // Adds a directed edge to the network from 'from' to 'to' with given capacity
    public void addEdge(int from, int to, int capacity) {
        Edge edge = new Edge(from, to, capacity);
        adjList[from].add(edge);
    }
    // Returns the entire adjacency list
    public List<Edge>[] getAdjList() {
        return adjList;
    }
    // Returns a list of all edges in the network
    public List<Edge> getAllEdges() {
        List<Edge> edges = new ArrayList<>();
        for (List<Edge> list : adjList) {
            edges.addAll(list);
        }
        return edges;
    }
}

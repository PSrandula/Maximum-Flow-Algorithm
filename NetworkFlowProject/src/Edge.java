// Represents an edge in the flow network
public class Edge {
    int from, to; // Start and end nodes of the edge
    int capacity, flow; // Maximum capacity and current flow through the edge

    // Constructor to initialize edge with from-node, to-node, and capacity
    public Edge(int from, int to, int capacity) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = 0; // Initially, no flow has been added
    }

    // Calculates and returns the remaining capacity
    public int residualCapacity() {
        return capacity - flow;
    }
    // Adds flow to the edge (used during augmentation)
    public void addFlow(int amount) {
        this.flow += amount;
    }
    // Returns a string representation of the flow on this edge
    @Override
    public String toString() {
        return "f(" + from + "," + to + ") = " + flow;
    }
}

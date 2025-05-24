import java.io.*;

public class FileParser {
    // Method to parse the file and construct a FlowNetwork object
    public static FlowNetwork parseFile(String filename) throws IOException {
        // Create a BufferedReader to read from the given file
        BufferedReader br = new BufferedReader(new FileReader(filename));
        // Get the number of nodes
        int n = Integer.parseInt(br.readLine().trim());
        // Create the flow network
        FlowNetwork network = new FlowNetwork(n);

        String line;
        // Read each line from the file
        while ((line = br.readLine()) != null) {
            // Split the line into parts
            String[] parts = line.trim().split("\\s+");
            // If the line has 3 parts (from, to, capacity)
            if (parts.length == 3) {
                // Parse the values
                int from = Integer.parseInt(parts[0]);
                int to = Integer.parseInt(parts[1]);
                int capacity = Integer.parseInt(parts[2]);

                // Add the edge to the network
                network.addEdge(from, to, capacity);
            }
        }
        // Close the reader
        br.close();
        // Return the constructed flow network
        return network;
    }
}

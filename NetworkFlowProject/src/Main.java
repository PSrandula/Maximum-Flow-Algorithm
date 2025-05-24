// UOW - w2052255 IIT - 20221876 Name - K.A.P.S.Randula

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

//Main class
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File selectedFile;

        // Loop until the user provides a valid input file
        while (true) {
            System.out.print("\nEnter the name of a file in the 'benchmarks' folder (e.g., bridge_1.txt/ladder_1.txt): ");
            String input = scanner.nextLine().trim();

            // Check if input is empty
            if (input.isEmpty()) {
                System.out.println("No file name provided. Please try again.\n");
                continue;
            }

            selectedFile = new File("benchmarks/" + input);

            // Check if the file exists in the benchmarks folder
            if (!selectedFile.exists()) {
                System.out.println("The file '" + input + "' does not exist in the 'benchmarks' folder. Please try again.\n");
            } else {
                break; // Valid file found, exit loop
            }
        }

        try {
            // Parse file and build flow network
            String filePath = selectedFile.getPath();
            String fileName = selectedFile.getName();

            FlowNetwork network = FileParser.parseFile(filePath);
            int source = 0;
            int sink = network.numNodes - 1;

            // Start time for performance measurement
            long startTime = System.nanoTime();
            // Run the Ford-Fulkerson algorithm to compute max flow
            int maxFlow = MaxFlow.fordFulkerson(network, source, sink);
            // End time for performance measurement
            long endTime = System.nanoTime();
            long durationMillis = (endTime - startTime) / 1_000_000;

            // Output results
            System.out.println("=====================================\n");
            System.out.println("File Name       : " + fileName);

            // Print network structure
            System.out.println("\nNetwork Structure:");
            for (int i = 0; i < network.numNodes; i++) {
                boolean hasEdges = false;
                StringBuilder sb = new StringBuilder();
                sb.append("node ").append(i).append(": ");

                for (Edge edge : network.getAdjList()[i]) {
                    if (edge.capacity > 0) {
                        hasEdges = true;
                        sb.append(edge.to)
                                .append("->").append(edge.capacity)
                                .append("/").append(edge.flow)
                                .append("  ");
                    }
                }

                if (!hasEdges) {
                    sb.append("(no outgoing edges)");
                }

                System.out.println(sb);
            }

            // Display final results
            System.out.println("\nMaximum Flow    : " + maxFlow);
            System.out.println("Execution Time  : " + durationMillis + " ms");
            System.out.println("=====================================\n");

            // Error handling
        } catch (IOException e) {
            System.out.println("Error reading the file: " + selectedFile.getName());
            System.out.println("IOException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error processing the file: " + selectedFile.getName());
            System.out.println("Exception: " + e.getMessage());
        }
    }
}


import java.util.*;
import javax.swing.*;

public class Postofficetof2 {

    // Edge class to store destination node and weight
    static class Edge {
        String node;
        int weight;

        Edge(String node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public Postofficetof2() {
        // Define the graph as an adjacency list
        Map<String, List<Edge>> graph = new HashMap<>();
        graph.put("PostOffice", Arrays.asList(new Edge("F1", 2), new Edge("Market", 1)));
        graph.put("F1", Arrays.asList(new Edge("M", 4), new Edge("F2", 3), new Edge("F3", 4)));
        graph.put("M", Arrays.asList(new Edge("F2", 2))); // Corrected weight
        graph.put("Market", Arrays.asList(new Edge("BookShop", 2)));
        graph.put("BookShop", Arrays.asList(new Edge("M", 2)));
        graph.put("F2", Collections.emptyList());
        graph.put("F3", Arrays.asList(new Edge("F1", 4)));

        // Compute the shortest path
        String[] path = {"PostOffice", "F1", "M", "F2"};
        int totalDistance = calculatePathDistance(graph, path);

        // Prepare output to display
        StringBuilder output = new StringBuilder();
        output.append("Shortest path: ").append(String.join(" -> ", path)).append("\n");
        output.append("Total distance: ").append(totalDistance);

        // Display output in a JFrame
        displayInJFrame(output.toString());
    }

    public static int calculatePathDistance(Map<String, List<Edge>> graph, String[] path) {
        int totalDistance = 0;

        // Traverse through the path and sum up distances
        for (int i = 0; i < path.length - 1; i++) {
            String current = path[i];
            String next = path[i + 1];
            boolean found = false;

            for (Edge edge : graph.get(current)) {
                if (edge.node.equals(next)) {
                    totalDistance += edge.weight;
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("No direct path exists between " + current + " and " + next);
                return -1; // Return -1 if the path is invalid
            }
        }

        return totalDistance;
    }

    public static void displayInJFrame(String text) {
        // Create JFrame
        JFrame frame = new JFrame("Shortest Path Result");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);

        // Create JTextArea to display the output
        JTextArea textArea = new JTextArea();
        textArea.setText(text);
        textArea.setEditable(false);

        // Add the JTextArea to the JFrame inside a JScrollPane
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Make the frame visible
        frame.setVisible(true);
    }

    // Main method to run this class directly
    public static void main(String[] args) {
        new Postofficetof2(); // Launch the Postofficetof2 logic
    }
}


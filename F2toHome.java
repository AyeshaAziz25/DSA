

import java.util.*;
import javax.swing.*;

public class F2toHome {

    // Edge class to represent a connection between nodes
    static class Edge {
        String node;
        int weight;

        Edge(String node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public F2toHome() {
        // Call the method to calculate and display the shortest path
        calculateAndDisplayShortestPath();
    }

    public static void calculateAndDisplayShortestPath() {
        // Unified graph definition
        Map<String, List<Edge>> graph = new HashMap<>();
        graph.put("F2", Arrays.asList(new Edge("F3", 2)));
        graph.put("F3", Arrays.asList(new Edge("F1", 1)));
        graph.put("F1", Arrays.asList(new Edge("M", 4)));
        graph.put("M", Collections.emptyList());

        // Calculate the shortest path from F2 to F1
        String[] path1 = {"F2", "F3", "F1"};
        int distanceF2toF1 = calculatePathDistance(graph, path1);

        // Calculate the shortest path from F1 to Home
        String[] path2 = {"F1", "M"};
        int distanceF1toHome = calculatePathDistance(graph, path2);

        // Prepare output to display
        StringBuilder output = new StringBuilder();
        output.append("Shortest path from F2 to F1: ").append(String.join(" -> ", path1)).append("\n");
        output.append("Total distance from F2 to F1: ").append(distanceF2toF1).append("\n\n");
        output.append("Shortest path from F1 to Home: ").append(String.join(" -> ", path2)).append("\n");
        output.append("Total distance from F1 to Home: ").append(distanceF1toHome).append("\n\n");

        // Calculate total distance
        int totalDistance = distanceF2toF1 + distanceF1toHome;
        output.append("Total distance from F2 to Home: ").append(totalDistance).append("\n");

        // Display final message
        if (path2[path2.length - 1].equals("M")) {
            output.append("Finally reached home!");
        }

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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(F2toHome::new);
    }
}

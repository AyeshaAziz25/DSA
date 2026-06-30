
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ShortestPath extends JFrame {
    private JTextArea textArea;
    private JLabel label;

    public ShortestPath() {
        // Set up the window properties
        setTitle("Shortest Path Visualization");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a text area to show the shortest path and total cost
        textArea = new JTextArea();
        textArea.setEditable(false); // Make it non-editable
        textArea.setLineWrap(true);  // Allow line wrapping
        textArea.setWrapStyleWord(true); // Wrap at word boundaries
        JScrollPane scrollPane = new JScrollPane(textArea);  // Add scroll support if content overflows

        label = new JLabel("Shortest Path Algorithm Output");

        // Layout setup
        setLayout(new BorderLayout());
        add(label, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Add the logic for calculating and displaying the shortest path
        displayShortestPath();

        // Make the window visible
        setVisible(true);
    }

    private void displayShortestPath() {
        Graph graph = new Graph();

        // Add edges to the graph
        graph.addEdge("M", "f2", 2);
        graph.addEdge("f2", "f3", 2);
        graph.addEdge("f3", "f1", 1);
        graph.addEdge("f1", "M", 4);
        graph.addEdge("f2", "market", 6);

        // Initialize traversal path and total cost
        List<String> path = Arrays.asList("M", "f2", "f3", "f1", "M", "f2", "market");
        int totalCost = 0;

        try {
            for (int i = 0; i < path.size() - 1; i++) {
                totalCost += graph.getEdgeWeight(path.get(i), path.get(i + 1));
            }

            // Display the traversal path and total cost on the JFrame
            StringBuilder output = new StringBuilder();
            output.append("Traversal Path: \n");
            for (String location : path) {
                output.append(location).append(" -> ");
            }
            output.delete(output.length() - 4, output.length());  // Remove the last " -> "
            output.append("\n\nTotal Cost: ").append(totalCost);

            textArea.setText(output.toString());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            textArea.setText("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShortestPath());
    }
}

// Graph class to store and retrieve edge weights
class Graph {
    private final Map<String, List<Edge>> adjacencyList = new HashMap<>();

    // Method to add an edge to the graph
    public void addEdge(String from, String to, int weight) {
        adjacencyList.computeIfAbsent(from, k -> new ArrayList<>()).add(new Edge(to, weight));
    }

    // Method to get the weight of an edge
    public int getEdgeWeight(String from, String to) {
        List<Edge> edges = adjacencyList.get(from);
        if (edges != null) {
            for (Edge edge : edges) {
                if (edge.to.equals(to)) {
                    return edge.weight;
                }
            }
        }
        throw new IllegalArgumentException("Edge not found: " + from + " -> " + to);
    }
}

// Edge class to store information about the edge
class Edge {
    String to;
    int weight;

    Edge(String to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

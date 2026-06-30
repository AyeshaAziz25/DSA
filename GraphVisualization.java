import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class GraphVisualization extends JFrame {

    public GraphVisualization() {
        super("Graph Visualization");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);
        add(new GraphPanel());
        setVisible(true);
    
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GraphVisualization());
    }
}

class GraphPanel extends JPanel {
    private final int NODE_RADIUS = 40; // Increased radius for larger nodes

    private void drawNode(Graphics2D g2d, String label, int x, int y, Color color) {
        g2d.setColor(color);
        g2d.fill(new Ellipse2D.Double(x - NODE_RADIUS, y - NODE_RADIUS, 2 * NODE_RADIUS, 2 * NODE_RADIUS)); // Draw circle
        g2d.setColor(Color.BLACK);
        FontMetrics fm = g2d.getFontMetrics();
        int labelWidth = fm.stringWidth(label);
        int labelHeight = fm.getHeight();
        g2d.drawString(label, x - labelWidth / 2, y + labelHeight / 4); // Center label inside the node
    }

    private void drawEdge(Graphics2D g2d, int x1, int y1, int x2, int y2, int cost, int offsetX, int offsetY) {
        g2d.setColor(Color.BLACK);

        // Calculate direction vector
        double dx = x2 - x1;
        double dy = y2 - y1;
        double length = Math.sqrt(dx * dx + dy * dy);

        // Adjust start and end points to be outside the nodes
        double offsetStartX = (dx / length) * NODE_RADIUS + offsetX;
        double offsetStartY = (dy / length) * NODE_RADIUS + offsetY;
        double offsetEndX = (dx / length) * NODE_RADIUS - offsetX;
        double offsetEndY = (dy / length) * NODE_RADIUS - offsetY;

        int startX = (int) (x1 + offsetStartX);
        int startY = (int) (y1 + offsetStartY);
        int endX = (int) (x2 - offsetEndX);
        int endY = (int) (y2 - offsetEndY);

        // Draw the line
        g2d.draw(new Line2D.Double(startX, startY, endX, endY));

        // Draw the arrowhead
        double arrowAngle = Math.toRadians(30);
        int arrowLength = 10;
        double angle = Math.atan2(dy, dx);

        int xArrow1 = (int) (endX - arrowLength * Math.cos(angle - arrowAngle));
        int yArrow1 = (int) (endY - arrowLength * Math.sin(angle - arrowAngle));
        int xArrow2 = (int) (endX - arrowLength * Math.cos(angle + arrowAngle));
        int yArrow2 = (int) (endY - arrowLength * Math.sin(angle + arrowAngle));

        g2d.drawLine(endX, endY, xArrow1, yArrow1);
        g2d.drawLine(endX, endY, xArrow2, yArrow2);

        // Draw the cost label
        int midX = (startX + endX) / 2;
        int midY = (startY + endY) / 2;
        g2d.setColor(Color.BLACK);
        g2d.drawString(String.valueOf(cost), midX, midY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw nodes with unique colors
        drawNode(g2d, "F1", 100, 150, Color.LIGHT_GRAY);
        drawNode(g2d, "F2", 150, 250, Color.LIGHT_GRAY);
        drawNode(g2d, "F3", 50, 280, Color.LIGHT_GRAY);
        drawNode(g2d, "M", 250, 220, Color.LIGHT_GRAY);
        drawNode(g2d, "Post Office", 400, 150, Color.LIGHT_GRAY);
        drawNode(g2d, "Market", 550, 150, Color.LIGHT_GRAY);
        drawNode(g2d, "Book Shop", 500, 250, Color.LIGHT_GRAY);

        // Draw edges with adjusted offsets
        drawEdge(g2d, 250, 220, 150, 250, 2, -5, -5); // M to F2
        drawEdge(g2d, 150, 250, 550, 150, 6, 5, 5);   // F2 to Market
        drawEdge(g2d, 150, 250, 50, 280, 2, 0, 0);    // F2 to F3
        drawEdge(g2d, 50, 280, 100, 150, 1, 0, 0);    // F3 to F1
        drawEdge(g2d, 150, 250, 100, 150, 3, 0, 0);   // F2 to F1
        drawEdge(g2d, 100, 150, 250, 220, 4, 0, 0);   // F1 to M
        drawEdge(g2d, 400, 150, 100, 150, 2, 0, 0);   // Post Office to F1
        drawEdge(g2d, 250, 220, 400, 150, 3, 0, 0);   // M to Post Office
        drawEdge(g2d, 550, 150, 400, 150, 1, 0, 0);   // Market to Post Office
        drawEdge(g2d, 550, 150, 500, 250, 2, 0, 0);   // Market to Book Shop
        drawEdge(g2d, 500, 250, 250, 220, 2, 0, 0);   // Book Shop to M
        drawEdge(g2d, 300, 230, 30, 280, 3, 15, 10); // M to F3 (Adjusted)
    }
}


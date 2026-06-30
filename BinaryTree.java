import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class BinaryTree extends JFrame {

    private static class Node {
        String fruit;
        int price; // Price in Rs.
        Node left;
        Node right;

        public Node(String fruit, int price) {
            this.fruit = fruit;
            this.price = price;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public BinaryTree() {
        super("Fruit Tree");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the tree with fruits and prices
        root = new Node("Apple", 250);
        root.left = new Node("Orange", 300);
        root.right = new Node("Mango", 200);
        root.left.left = new Node("Banana", 150);
        root.left.right = new Node("Grapes", 100);
        root.right.left = new Node("Strawberry", 230);
        root.right.right = new Node("Cherry", 350);

        // Add the tree to the frame
        add(new TreePanel(root));
        setVisible(true);
    }

    private class TreePanel extends JPanel {

        private Node root;
        private final int NODE_RADIUS = 40;

        public TreePanel(Node root) {
            this.root = root;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            drawTree(g2d, root, getWidth() / 2, 50, getWidth() / 4);
        }

        private void drawTree(Graphics2D g2d, Node node, int x, int y, int spacing) {
            if (node != null) {
                // Draw the node as a circle
                g2d.setColor(Color.WHITE);
                g2d.fill(new Ellipse2D.Double(x - NODE_RADIUS, y - NODE_RADIUS, 2 * NODE_RADIUS, 2 * NODE_RADIUS));
                g2d.setColor(Color.BLACK);
                g2d.draw(new Ellipse2D.Double(x - NODE_RADIUS, y - NODE_RADIUS, 2 * NODE_RADIUS, 2 * NODE_RADIUS));

                // Draw the label (fruit name and price) inside the circle
                String label = node.fruit + "\nRs." + node.price;
                FontMetrics fm = g2d.getFontMetrics();
                int labelWidth = fm.stringWidth(node.fruit);
                int priceWidth = fm.stringWidth("Rs." + node.price);

                g2d.drawString(node.fruit, x - labelWidth / 2, y - 5);
                g2d.drawString("Rs." + node.price, x - priceWidth / 2, y + 15);

                // Draw left subtree
                if (node.left != null) {
                    g2d.drawLine(x, y + NODE_RADIUS, x - spacing, y + 50 - NODE_RADIUS);
                    drawTree(g2d, node.left, x - spacing, y + 50, spacing / 2);
                }

                // Draw right subtree
                if (node.right != null) {
                    g2d.drawLine(x, y + NODE_RADIUS, x + spacing, y + 50 - NODE_RADIUS);
                    drawTree(g2d, node.right, x + spacing, y + 50, spacing / 2);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BinaryTree::new);
    }
}

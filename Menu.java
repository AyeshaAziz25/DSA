
import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
    private int currentStep = 0; // Track the current step of navigation

    public Menu() {
        setTitle("Main Menu");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, 1)); // Vertical layout

        // Create buttons for each class
        JButton graphVisualizationButton = new JButton("Graph Visualization");
        JButton shortestPathButton = new JButton("Shortest Path");
        JButton binaryTreeButton = new JButton("Binary Tree");
        JButton cartButton = new JButton("Cart");
        JButton checkOutButton = new JButton("Check Out");
        JButton marketToBookshopButton = new JButton("Market to Bookshop");
        JButton bookshopToPostofficeButton = new JButton("Bookshop to Post Office");
        JButton postOfficeToF2Button = new JButton("Post Office to F2");
        JButton f2ToHomeButton = new JButton("F2 to Home");

        // Add action listeners to buttons with validation logic
        graphVisualizationButton.addActionListener(e -> navigateTo(0, () -> new GraphVisualization()));
        shortestPathButton.addActionListener(e -> navigateTo(1, () -> new ShortestPath()));
        binaryTreeButton.addActionListener(e -> navigateTo(2, () -> new BinaryTree()));
        cartButton.addActionListener(e -> navigateTo(3, () -> new Cart()));
        checkOutButton.addActionListener(e -> navigateTo(4, () -> new CheckOut()));
        marketToBookshopButton.addActionListener(e -> navigateTo(5, () -> new MarkettoBookshopApp()));
        bookshopToPostofficeButton.addActionListener(e -> navigateTo(6, () -> new BookshoptoPostoffice()));
        postOfficeToF2Button.addActionListener(e -> navigateTo(7, () -> new Postofficetof2()));
        f2ToHomeButton.addActionListener(e -> navigateTo(8, F2toHome::new));

        // Add buttons to the frame
        add(graphVisualizationButton);
        add(shortestPathButton);
        add(binaryTreeButton);
        add(cartButton);
        add(checkOutButton);
        add(marketToBookshopButton);
        add(bookshopToPostofficeButton);
        add(postOfficeToF2Button);
        add(f2ToHomeButton);

        setVisible(true);
    }

    /**
     * Navigates to the next screen if the sequence is followed.
     * 
     * @param requiredStep The step index required to access this page.
     * @param action The action to perform (opening the new page).
     */
    private void navigateTo(int requiredStep, Runnable action) {
        if (currentStep == requiredStep) {
            // If the current step is valid, proceed with opening the frame
            action.run();
            currentStep++; // Move to the next step
        } else if (currentStep > requiredStep) {
            // If this step is already completed, show a message
            JOptionPane.showMessageDialog(this, "You've already completed this step!");
        } else {
            // Show message if the user is trying to skip a step
            JOptionPane.showMessageDialog(this, "Please complete the previous steps before proceeding!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Menu::new);
    }
}


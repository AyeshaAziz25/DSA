

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class MarkettoBookshopApp extends JFrame {

    public MarkettoBookshopApp() {
        super("Market to Book Shop - Book Sorting");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        add(new MarketToBookShopPanel());
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MarkettoBookshopApp::new);
    }
}

class MarketToBookShopPanel extends JPanel {
    // Use an array instead of ArrayList
    private final String[] books = {
        "Harry Potter","Novel",  "Object Oriented Programming", "C++",
        "Java", "English Literature", "Urdu", "Civics"
    };
    private String[] sortedBooks;

    public MarketToBookShopPanel() {
        // Step 6: Market -> Book Shop
        sortedBooks = Arrays.copyOf(books, books.length); // Copy books array to sortedBooks array
        sortBooksAlphabetically();
    }

    private void sortBooksAlphabetically() {
        // Sorting using Bubble Sort (alternative: Arrays.sort(sortedBooks))
        for (int i = 0; i < sortedBooks.length - 1; i++) {
            for (int j = 0; j < sortedBooks.length - i - 1; j++) {
                if (sortedBooks[j].compareTo(sortedBooks[j + 1]) > 0) {
                    // Swap if sortedBooks[j] > sortedBooks[j + 1]
                    String temp = sortedBooks[j];
                    sortedBooks[j] = sortedBooks[j + 1];
                    sortedBooks[j + 1] = temp;
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw labels for Market and Book Shop
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("Market", 100, 100);
        g2d.drawLine(150, 100, 300, 100); // Arrow from Market to Book Shop
        g2d.drawString("Book Shop", 320, 100);

        // Display the original list of books
        g2d.setFont(new Font("Arial", Font.PLAIN, 14));
        g2d.drawString("Original List of Books:", 50, 150);
        int y = 170;
        for (String book : books) {
            g2d.drawString("- " + book, 50, y);
            y += 20;
        }

        // Display the sorted list of books
        g2d.drawString("Sorted List of Books (A-Z):", 300, 150);
        y = 170;
        for (String book : sortedBooks) {
            g2d.drawString("- " + book, 300, y);
            y += 20;
        }
    }}
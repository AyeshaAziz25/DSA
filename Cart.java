import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;

public class Cart extends JFrame {

    // Node class to represent fruits and their details
    private static class Node {
        String fruit;
        int price; // Price in Rs.
        int quantity; // Quantity of the fruit in the cart

        public Node(String fruit, int price) {
            this.fruit = fruit;
            this.price = price;
            this.quantity = 1; // Initial quantity is 1
        }

        // Increase quantity
        public void increaseQuantity() {
            this.quantity++;
        }

        // Decrease quantity
        public void decreaseQuantity() {
            if (this.quantity > 1) {
                this.quantity--;
            }
        }

        @Override
        public String toString() {
            return fruit + " (Rs." + price + ") x " + quantity;
        }
    }

    private LinkedList<Node> cart; // LinkedList as a stack for cart
    private Node[] fruits; // Array of all available fruits

    // GUI components
    private JList<String> fruitList;
    private DefaultListModel<String> fruitListModel;
    private JTextArea cartTextArea;
    private JButton addButton, removeButton, increaseButton, decreaseButton, viewCartButton, checkoutButton;

    public Cart() {
        // Initialize cart and fruits
        cart = new LinkedList<>();
        fruits = new Node[]{
            new Node("Apple", 250),
            new Node("Orange", 300),
            new Node("Mango", 200),
            new Node("Banana", 150),
            new Node("Grapes", 100),
            new Node("Strawberry", 230),
            new Node("Cherry", 350)
        };

        // GUI setup
        setTitle("Fruit Cart");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
setVisible(true);
        // Panel setup
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Fruit list setup
        fruitListModel = new DefaultListModel<>();
        for (Node fruit : fruits) {
            fruitListModel.addElement(fruit.fruit + " (Rs." + fruit.price + ")");
        }
        fruitList = new JList<>(fruitListModel);
        fruitList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane fruitScrollPane = new JScrollPane(fruitList);
        panel.add(fruitScrollPane, BorderLayout.WEST);

        // Cart display setup
        cartTextArea = new JTextArea();
        cartTextArea.setEditable(false);
        JScrollPane cartScrollPane = new JScrollPane(cartTextArea);
        panel.add(cartScrollPane, BorderLayout.CENTER);

        // Buttons setup
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add to Cart");
        removeButton = new JButton("Remove from Cart");
        increaseButton = new JButton("Increase Quantity");
        decreaseButton = new JButton("Decrease Quantity");
        viewCartButton = new JButton("View Cart");
        checkoutButton = new JButton("Checkout");

        // Add buttons to button panel
        buttonPanel.setLayout(new GridLayout(6, 1, 10, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(increaseButton);
        buttonPanel.add(decreaseButton);
        buttonPanel.add(viewCartButton);
        buttonPanel.add(checkoutButton);

        panel.add(buttonPanel, BorderLayout.EAST);

        add(panel);

        // Add ActionListeners for buttons
        addButton.addActionListener(e -> addToCart());
        removeButton.addActionListener(e -> removeFromCart());
        increaseButton.addActionListener(e -> increaseQuantity());
        decreaseButton.addActionListener(e -> decreaseQuantity());
        viewCartButton.addActionListener(e -> viewCart());
        checkoutButton.addActionListener(e -> checkout());
    }

    private void addToCart() {
        if (cart.size() < 4) {
            int selectedIndex = fruitList.getSelectedIndex();
            if (selectedIndex != -1) {
                Node selectedFruit = fruits[selectedIndex];
                cart.addFirst(selectedFruit);
                JOptionPane.showMessageDialog(this, selectedFruit.fruit + " added to the cart.");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a fruit to add to the cart.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Cart is full! Maximum 4 items allowed.");
        }
    }

    private void removeFromCart() {
        if (!cart.isEmpty()) {
            Node removedFruit = cart.removeFirst(); // Removes from the top of the stack
            JOptionPane.showMessageDialog(this, removedFruit.fruit + " removed from the cart.");
        } else {
            JOptionPane.showMessageDialog(this, "Cart is empty!");
        }
    }

    private void increaseQuantity() {
        if (!cart.isEmpty()) {
            Node fruit = cart.getFirst();
            fruit.increaseQuantity();
            JOptionPane.showMessageDialog(this, "Quantity of " + fruit.fruit + " increased to " + fruit.quantity);
        } else {
            JOptionPane.showMessageDialog(this, "Cart is empty!");
        }
    }

    private void decreaseQuantity() {
        if (!cart.isEmpty()) {
            Node fruit = cart.getFirst();
            fruit.decreaseQuantity();
            JOptionPane.showMessageDialog(this, "Quantity of " + fruit.fruit + " decreased to " + fruit.quantity);
        } else {
            JOptionPane.showMessageDialog(this, "Cart is empty!");
        }
    }

    private void viewCart() {
        if (!cart.isEmpty()) {
            StringBuilder cartDetails = new StringBuilder();
            int total = 0;
            for (Node fruit : cart) {
                cartDetails.append(fruit).append("\n");
                total += fruit.price * fruit.quantity;
            }
            cartDetails.append("\nTotal: Rs.").append(total);
            cartTextArea.setText(cartDetails.toString());
        } else {
            cartTextArea.setText("Cart is empty!");
        }
    }

    private void checkout() {
        if (!cart.isEmpty()) {
            StringBuilder checkoutDetails = new StringBuilder();
            int total = 0;
            for (int i = cart.size() - 1; i >= 0; i--) { // Iterate in FIFO order
                Node fruit = cart.get(i);
                checkoutDetails.append(fruit).append("\n");
                total += fruit.price * fruit.quantity;
            }
            checkoutDetails.append("\nTotal: Rs.").append(total);
            JOptionPane.showMessageDialog(this, "Checkout Details:\n" + checkoutDetails.toString());
        } else {
            JOptionPane.showMessageDialog(this, "Cart is empty!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Cart gui = new Cart();
            gui.setVisible(true);
        });
    }
}

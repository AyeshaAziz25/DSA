
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;

public class CheckOut extends JFrame {

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

    private LinkedList<Node> cart; // LinkedList as a queue for cart
    private Node[] fruits; // Array of all available fruits

    // GUI components
    private JList<String> fruitList;
    private DefaultListModel<String> fruitListModel;
    private JList<String> cartList;
    private DefaultListModel<String> cartListModel;
    private JTextArea cartTextArea;
    private JButton addButton, removeButton, increaseButton, decreaseButton, checkoutButton, viewCartButton;

    public CheckOut() {
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
        setTitle("Check out");
        setSize(600, 400);
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

        // Cart list setup
        cartListModel = new DefaultListModel<>();
        cartList = new JList<>(cartListModel);
        cartList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane cartScrollPane = new JScrollPane(cartList);
        panel.add(cartScrollPane, BorderLayout.CENTER);

        // Buttons setup
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add to Cart");
        removeButton = new JButton("Remove from Cart");
        increaseButton = new JButton("Increase Quantity");
        decreaseButton = new JButton("Decrease Quantity");
        checkoutButton = new JButton("Checkout");
        viewCartButton = new JButton("View Cart");

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
        int selectedIndex = fruitList.getSelectedIndex();
        if (selectedIndex != -1 && cart.size() < 4) {
            Node selectedFruit = fruits[selectedIndex];
            cart.addLast(selectedFruit); // Adds the selected fruit to the end of the cart (FIFO order)
            cartListModel.addElement(selectedFruit.toString());
            JOptionPane.showMessageDialog(this, selectedFruit.fruit + " added to the cart.");
        } else {
            JOptionPane.showMessageDialog(this, "Please select a fruit or cart is full!");
        }
    }

    private void removeFromCart() {
        int selectedIndex = cartList.getSelectedIndex();
        if (selectedIndex != -1) {
            Node removedFruit = cart.remove(selectedIndex); // Remove selected fruit from cart
            cartListModel.remove(selectedIndex); // Remove from the JList
            JOptionPane.showMessageDialog(this, removedFruit.fruit + " removed from the cart.");
        } else {
            JOptionPane.showMessageDialog(this, "Please select a fruit to remove!");
        }
    }

    private void increaseQuantity() {
        int selectedIndex = cartList.getSelectedIndex();
        if (selectedIndex != -1) {
            Node fruit = cart.get(selectedIndex);
            fruit.increaseQuantity();
            cartListModel.set(selectedIndex, fruit.toString());
            JOptionPane.showMessageDialog(this, "Quantity of " + fruit.fruit + " increased to " + fruit.quantity);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a fruit from the cart!");
        }
    }

    private void decreaseQuantity() {
        int selectedIndex = cartList.getSelectedIndex();
        if (selectedIndex != -1) {
            Node fruit = cart.get(selectedIndex);
            fruit.decreaseQuantity();
            cartListModel.set(selectedIndex, fruit.toString());
            JOptionPane.showMessageDialog(this, "Quantity of " + fruit.fruit + " decreased to " + fruit.quantity);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a fruit from the cart!");
        }
    }

    private void viewCart() {
        StringBuilder cartDetails = new StringBuilder();
        int total = 0;
        for (Node fruit : cart) {
            cartDetails.append(fruit).append("\n");
            total += fruit.price * fruit.quantity;
        }
        cartDetails.append("\nTotal: Rs.").append(total);
        JOptionPane.showMessageDialog(this, cartDetails.toString());
    }

    // Checkout method using queue (FIFO)
    private void checkout() {
        if (!cart.isEmpty()) {
            StringBuilder checkoutDetails = new StringBuilder();
            int total = 0;
            checkoutDetails.append("Checkout - Items in FIFO order:\n");

            while (!cart.isEmpty()) {
                Node fruit = cart.removeFirst(); // Process items in FIFO order
                checkoutDetails.append(fruit).append("\n");
                total += fruit.price * fruit.quantity;
            }

            checkoutDetails.append("\nTotal Amount: Rs.").append(total);
            JOptionPane.showMessageDialog(this, checkoutDetails.toString());
        } else {
            JOptionPane.showMessageDialog(this, "Cart is empty! Nothing to checkout.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CheckOut gui = new CheckOut();
            gui.setVisible(true);
        });
    }
}


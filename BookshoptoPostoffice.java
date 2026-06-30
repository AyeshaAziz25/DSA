// import java.util.Scanner;

// public class BookshoptoPostoffice {

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         boolean atBookshop = true;  // Initially, we are at the bookshop
//         boolean atPostOffice = false;
        
//         System.out.println("Welcome to the navigation system!");

//         while (true) {
//             if (atBookshop) {
//                 System.out.println("\nYou are at the Bookshop.");
//                 System.out.println("1. Go to Post Office");
//                 System.out.println("2. Exit");
//                 System.out.print("Enter your choice: ");
//                 int choice = scanner.nextInt();

//                 switch (choice) {
//                     case 1:
//                         // Navigate to Post Office
//                         atBookshop = false;
//                         atPostOffice = true;
//                         System.out.println("You are now at the Post Office.");
//                         break;
//                     case 2:
//                         System.out.println("Exiting...");
//                         scanner.close();
//                         return;
//                     default:
//                         System.out.println("Invalid choice! Please try again.");
//                 }
//             }

//             if (atPostOffice) {
//                 System.out.println("\nYou are at the Post Office.");
//                 System.out.println("1. View Post Boxes");
//                 System.out.println("2. Go back to Bookshop");
//                 System.out.println("3. Exit");
//                 System.out.print("Enter your choice: ");
//                 int choice = scanner.nextInt();

//                 switch (choice) {
//                     case 1:
//                         // Display available post boxes (simplified)
//                         System.out.println("\nPost Boxes available:");
//                         System.out.println("1. Post Box #101");
//                         System.out.println("2. Post Box #102");
//                         System.out.println("3. Post Box #103");
//                         System.out.println("4. Post Box #104");
//                         break;
//                     case 2:
//                         // Go back to the Bookshop
//                         atBookshop = true;
//                         atPostOffice = false;
//                         System.out.println("You are now back at the Bookshop.");
//                         break;
//                     case 3:
//                         System.out.println("Exiting...");
//                         scanner.close();
//                         return;
//                     default:
//                         System.out.println("Invalid choice! Please try again.");
//                 }
//             }
//         }
//     }
// }
// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// public class BookshoptoPostoffice {
//     private JFrame frame;
//     private JPanel panel;
//     private JButton goToPostOfficeButton, viewPostBoxesButton, goBackToBookshopButton, exitButton;
//     private JTextArea outputArea;

//     private boolean atBookshop = true;
//     private boolean atPostOffice = false;

//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(() -> {
//             try {
//                 BookshoptoPostoffice window = new BookshoptoPostoffice();
//                 window.frame.setVisible(true);
//             } catch (Exception e) {
//                 e.printStackTrace();
//             }
//         });
//     }

//     public BookshoptoPostoffice() {
//         frame = new JFrame("Navigation System");
//         frame.setBounds(100, 100, 450, 300);
//         frame.setLocationRelativeTo(null);
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
//         panel = new JPanel();
//         frame.getContentPane().add(panel, BorderLayout.CENTER);
//         panel.setLayout(new BorderLayout(0, 0));

//         outputArea = new JTextArea();
//         outputArea.setEditable(false);
//         panel.add(outputArea, BorderLayout.CENTER);

//         JPanel buttonPanel = new JPanel();
//         panel.add(buttonPanel, BorderLayout.SOUTH);

//         goToPostOfficeButton = new JButton("Go to Post Office");
//         viewPostBoxesButton = new JButton("View Post Boxes");
//         goBackToBookshopButton = new JButton("Go back to Bookshop");
//         exitButton = new JButton("Exit");

//         goToPostOfficeButton.setEnabled(false);
//         viewPostBoxesButton.setEnabled(false);
//         goBackToBookshopButton.setEnabled(false);

//         buttonPanel.add(goToPostOfficeButton);
//         buttonPanel.add(viewPostBoxesButton);
//         buttonPanel.add(goBackToBookshopButton);
//         buttonPanel.add(exitButton);

//         // Initial state: Bookshop
//         updateUIForBookshop();

//         goToPostOfficeButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 // Navigate to Post Office
//                 atBookshop = false;
//                 atPostOffice = true;
//                 updateUIForPostOffice();
//             }
//         });

//         goBackToBookshopButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 // Go back to Bookshop
//                 atPostOffice = false;
//                 atBookshop = true;
//                 updateUIForBookshop();
//             }
//         });

//         viewPostBoxesButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 // Display Post Boxes
//                 String postBoxes = "Post Boxes available:\n";
//                 postBoxes += "1. Post Box #101\n";
//                 postBoxes += "2. Post Box #102\n";
//                 postBoxes += "3. Post Box #103\n";
//                 postBoxes += "4. Post Box #104\n";
//                 outputArea.setText(postBoxes);
//             }
//         });

//         exitButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 System.exit(0);
//             }
//         });
//     }

//     private void updateUIForBookshop() {
//         outputArea.setText("You are at the Bookshop.\n");
//         goToPostOfficeButton.setEnabled(true);
//         viewPostBoxesButton.setEnabled(false);
//         goBackToBookshopButton.setEnabled(false);
//     }

//     private void updateUIForPostOffice() {
//         outputArea.setText("You are at the Post Office.\n");
//         goToPostOfficeButton.setEnabled(false);
//         viewPostBoxesButton.setEnabled(true);
//         goBackToBookshopButton.setEnabled(true);
//     }
// }

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookshoptoPostoffice {
    private JFrame frame;
    private JPanel panel;
    private JButton goToPostOfficeButton, viewPostBoxesButton, goBackToBookshopButton, exitButton;
    private JTextArea outputArea;

    private boolean atBookshop = true;
    private boolean atPostOffice = false;

    private final int costBookshopToM = 2;
    private final int costMToPostOffice = 3;
    private final int totalCost = costBookshopToM + costMToPostOffice;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                BookshoptoPostoffice window = new BookshoptoPostoffice();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public BookshoptoPostoffice() {
        frame = new JFrame("Navigation System");
        frame.setBounds(100, 100, 400, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setVisible(true);
        panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        panel.add(outputArea, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        panel.add(buttonPanel, BorderLayout.SOUTH);

        goToPostOfficeButton = new JButton("Go to Post Office");
        viewPostBoxesButton = new JButton("View Post Boxes");
        goBackToBookshopButton = new JButton("Go back to Bookshop");
        exitButton = new JButton("Exit");

        goToPostOfficeButton.setEnabled(false);
        viewPostBoxesButton.setEnabled(false);
        goBackToBookshopButton.setEnabled(false);

        buttonPanel.add(goToPostOfficeButton);
        buttonPanel.add(viewPostBoxesButton);
        buttonPanel.add(goBackToBookshopButton);
        buttonPanel.add(exitButton);

        // Initial state: Bookshop
        updateUIForBookshop();

        goToPostOfficeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Navigate to Post Office
                atBookshop = false;
                atPostOffice = true;
                updateUIForPostOffice();
            }
        });

        goBackToBookshopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go back to Bookshop
                atPostOffice = false;
                atBookshop = true;
                updateUIForBookshop();
            }
        });

        viewPostBoxesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display Post Boxes
                String postBoxes = "Post Boxes available:\n";
                postBoxes += "1. Post Box #101\n";
                postBoxes += "2. Post Box #102\n";
                postBoxes += "3. Post Box #103\n";
                postBoxes += "4. Post Box #104\n";
                outputArea.setText(postBoxes);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void updateUIForBookshop() {
        outputArea.setText("You are at the Bookshop.\n");
        goToPostOfficeButton.setEnabled(true);
        viewPostBoxesButton.setEnabled(false);
        goBackToBookshopButton.setEnabled(false);
    }

    private void updateUIForPostOffice() {
        outputArea.setText("You are at the Post Office.\n" +
                "Cost from Bookshop to Post Office: " + totalCost + "\n");
        goToPostOfficeButton.setEnabled(false);
        viewPostBoxesButton.setEnabled(true);
        goBackToBookshopButton.setEnabled(true);
    }
}

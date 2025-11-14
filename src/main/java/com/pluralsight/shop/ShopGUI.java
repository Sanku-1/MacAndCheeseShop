package com.pluralsight.shop;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ShopGUI extends JFrame {
    private Storefront storefront;
    private Person currentUser;
    private CustomerCart cart;

    private JTextArea orderDisplay;
    private JLabel totalLabel;
    private JLabel userLabel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
                    ShopGUI newGUI;
                    try {
                        newGUI = new ShopGUI();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    newGUI.setVisible(true);
                }
        );
    }

    public ShopGUI() throws IOException {
        storefront = StorefrontManager.getStorefront();

        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        JTextField emailField = new JTextField();
        JTextField passwordField = new JTextField();

        loginPanel.add(new JLabel("Email:"));
        loginPanel.add(emailField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel("(Leave blank for Guest)"));
        loginPanel.add(new JLabel(""));

        int result = JOptionPane.showConfirmDialog(this, loginPanel,
                "Login to " + storefront.getName(), JOptionPane.OK_CANCEL_OPTION);

        currentUser = new Person("Guest", "guest@example.com");

        if (result == JOptionPane.OK_OPTION) {
            String email = emailField.getText().trim();
            String password = passwordField.getText().trim();

            if (!email.isEmpty()) {
                for (Person person:storefront.customerList) {
                    if (email.equalsIgnoreCase(person.getEmail())) {
                        if (password.equals(person.getPassword())) {
                            currentUser = person;
                            JOptionPane.showMessageDialog(this, "Welcome back, " + person.getName());
                        } else {
                            JOptionPane.showMessageDialog(this, "Incorrect password - using Guest mode");
                        }
                        break;
                    }
                }
            }
        }
        cart = new CustomerCart(new ArrayList<>(), currentUser);

        // Setup window
        setTitle(storefront.getName());
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createGUI();
    }

    private void createGUI() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel(storefront.getName(), SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        userLabel = new JLabel("User: " + currentUser.getName() + " | Points: " +
                String.format("%.2f", currentUser.getRewardPoints()) + "  ", SwingConstants.RIGHT);
        topPanel.add(title, BorderLayout.CENTER);
        topPanel.add(userLabel, BorderLayout.LINE_END);
        add(topPanel, BorderLayout.PAGE_START);

        orderDisplay = new JTextArea();
        orderDisplay.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(orderDisplay);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1, 5, 5));

        JButton addMacButton = new JButton("Add Mac & Cheese");
        JButton addDrinkButton = new JButton("Add Drink");
        JButton addSideButton = new JButton("Add Side");
        totalLabel = new JLabel("Total: $0.00", SwingConstants.CENTER);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JButton checkoutButton = new JButton("Checkout");
        JButton clearButton = new JButton("Clear Order");

        addMacButton.addActionListener(e -> addMacAndCheese());
        addDrinkButton.addActionListener(e -> addDrink());
        addSideButton.addActionListener(e -> addSide());
        checkoutButton.addActionListener(e -> checkout());
        clearButton.addActionListener(e -> clearOrder());

        buttonPanel.add(addMacButton);
        buttonPanel.add(addDrinkButton);
        buttonPanel.add(addSideButton);
        buttonPanel.add(totalLabel);
        buttonPanel.add(checkoutButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.LINE_END);
    }

    private void addMacAndCheese() {
        String[] sizes = {"Small ($7.00)", "Medium ($10.00)", "Large ($15.00)"};
        String sizeChoice = (String) JOptionPane.showInputDialog(
                this, "Choose size:", "Mac & Cheese Size",
                JOptionPane.QUESTION_MESSAGE, null, sizes, sizes[0]);

        if (sizeChoice == null) return; // User cancelled

        String size = "small";
        if (sizeChoice.contains("Medium")) {
            size = "medium";
        } else if (sizeChoice.contains("Large")) {
            size = "large";
        }

        String[] flavors = {"Buffalo", "BBQ", "Plain", "Four Cheese"};
        String flavor = (String) JOptionPane.showInputDialog(
                this, "Choose flavor:", "Mac & Cheese Flavor",
                JOptionPane.QUESTION_MESSAGE, null, flavors, flavors[0]);

        if (flavor == null) return;

        int extraCheese = JOptionPane.showConfirmDialog(
                this, "Add extra cheese?", "Extra Cheese", JOptionPane.YES_NO_OPTION);

        int extraMeat = JOptionPane.showConfirmDialog(
                this, "Add extra meat?", "Extra Meat", JOptionPane.YES_NO_OPTION);

        int breadBowl = JOptionPane.showConfirmDialog(
                this, "Serve in bread bowl?", "Bread Bowl", JOptionPane.YES_NO_OPTION);

        MacAndCheese mac = new MacAndCheese(
                "Mac and Cheese",
                size,
                flavor,
                extraCheese == JOptionPane.YES_OPTION,
                extraMeat == JOptionPane.YES_OPTION,
                breadBowl == JOptionPane.YES_OPTION
        );

        addToppings(mac);

        cart.addToCart(mac);
        updateDisplay();
        JOptionPane.showMessageDialog(this, "Mac & Cheese added!");
    }

    // Add toppings to mac & cheese (SIMPLIFIED)
    private void addToppings(MacAndCheese mac) {
        // Build list of ALL toppings with prices
        ArrayList<String> allToppingNames = new ArrayList<>();
        ArrayList<Topping> allToppings = new ArrayList<>();

        for (Topping topping : storefront.toppingOfferings) {
            String displayName = topping.name;

            // Add price to name
            if (topping instanceof MeatTopping) {
                displayName += " ($1.00)";
            } else if (topping instanceof PremiumTopping) {
                displayName += " ($0.75)";
            }

            allToppingNames.add(displayName);
            allToppings.add(topping);
        }

        // Show ONE list with all toppings
        JList<String> toppingList = new JList<>(allToppingNames.toArray(new String[0]));
        toppingList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(toppingList);
        scrollPane.setPreferredSize(new Dimension(300, 200));

        int result = JOptionPane.showConfirmDialog(
                this, scrollPane, "Select Toppings (Ctrl+Click for multiple)",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            for (int index : toppingList.getSelectedIndices()) {
                mac.addTopping(allToppings.get(index));
            }
        }
    }

    private void addDrink() {
        String[] sizes = {"Small ($2.00)", "Medium ($2.50)", "Large ($3.00)"};
        String sizeChoice = (String) JOptionPane.showInputDialog(
                this, "Choose size:", "Drink Size",
                JOptionPane.QUESTION_MESSAGE, null, sizes, sizes[0]);

        if (sizeChoice == null) return;

        String size = "small";
        if (sizeChoice.contains("Medium")) size = "medium";
        if (sizeChoice.contains("Large")) size = "large";

        String[] flavors = {"Coke", "Sprite", "Root Beer", "Lemonade", "Iced Tea", "Water"};
        String flavor = (String) JOptionPane.showInputDialog(
                this, "Choose drink:", "Drinks",
                JOptionPane.QUESTION_MESSAGE, null, flavors, flavors[0]);
        if (flavor == null) return;

        Drink drink = new Drink("Drink", size, flavor);
        cart.addToCart(drink);
        updateDisplay();
        JOptionPane.showMessageDialog(this, "Drink added!");
    }

    private void addSide() {
        try {
            ArrayList<Side> sides = StorefrontManager.loadSides();
            String[] sideNames = new String[sides.size()];
            for (int i = 0; i < sides.size(); i++) {
                sideNames[i] = sides.get(i).name;
            }

            String choice = (String) JOptionPane.showInputDialog(
                    this, "Choose a side ($1.50):", "Add Side",
                    JOptionPane.QUESTION_MESSAGE, null, sideNames, sideNames[0]);

            if (choice == null) return;

            for (Side side : sides) {
                if (side.name.equals(choice)) {
                    cart.addToCart(side);
                    updateDisplay();
                    JOptionPane.showMessageDialog(this, "Side added!");
                    break;
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error loading sides!");
        }
    }

    private void checkout() {
        if (cart.cartList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Your cart is empty!");
            return;
        }

        double total = cart.getBaseCost();
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Total: $" + String.format("%.2f", total) + "\n\nConfirm order?",
                "Checkout",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                saveReceipt();
                double pointsEarned = cart.getRewardPoints();
                cart.checkOut();
                JOptionPane.showMessageDialog(this,
                        "Order complete!\nYou earned " + String.format("%.2f", pointsEarned) +
                                " reward points!\nTotal Points: " + String.format("%.2f", currentUser.getRewardPoints()) +
                                "\nReceipt saved to receipts folder.");
                clearOrder();
                updateUserLabel();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving receipt!");
            }
        }
    }

    private void saveReceipt() throws IOException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String filename = "receipts/" + now.format(formatter) + ".txt";

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename));

        bufferedWriter.write(storefront.getName());
        bufferedWriter.newLine();
        bufferedWriter.write(storefront.address);
        bufferedWriter.newLine();
        bufferedWriter.write(storefront.phone);
        bufferedWriter.newLine();
        bufferedWriter.write("=====================================");
        bufferedWriter.newLine();
        bufferedWriter.write("Customer: " + currentUser.getName());
        bufferedWriter.newLine();
        bufferedWriter.write("Date: " + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        bufferedWriter.newLine();
        bufferedWriter.newLine();

        int itemNum = 1;
        for (Product product : cart.cartList) {
            bufferedWriter.write(itemNum + ". ");

            if (product instanceof MacAndCheese) {
                MacAndCheese mac = (MacAndCheese) product;
                bufferedWriter.write(mac.size + " " + mac.flavor + " Mac & Cheese");
                bufferedWriter.newLine();
                if (mac.isExtraCheese) bufferedWriter.write("   + Extra Cheese\n");
                if (mac.isExtraMeat) bufferedWriter.write("   + Extra Meat\n");
                if (mac.isBreadBowl) bufferedWriter.write("   + Bread Bowl\n");
                if (!mac.toppingsList.isEmpty()) {
                    bufferedWriter.write("   Toppings: ");
                    for (int i = 0; i < mac.toppingsList.size(); i++) {
                        bufferedWriter.write(mac.toppingsList.get(i).name);
                        if (i < mac.toppingsList.size() - 1) bufferedWriter.write(", ");
                    }
                    bufferedWriter.newLine();
                }
                bufferedWriter.write("   Price: $" + String.format("%.2f", mac.getCost()));
            } else if (product instanceof Drink) {
                Drink drink = (Drink) product;
                bufferedWriter.write(drink.size + " " + drink.name);
                bufferedWriter.newLine();
                bufferedWriter.write("   Price: $" + String.format("%.2f", drink.getCost()));
            } else if (product instanceof Side) {
                Side side = (Side) product;
                bufferedWriter.write(side.name);
                bufferedWriter.newLine();
                bufferedWriter.write("   Price: $" + String.format("%.2f", side.getCost()));
            }

            bufferedWriter.newLine();
            bufferedWriter.newLine();
            itemNum++;
        }

        bufferedWriter.write("-------------------------------------");
        bufferedWriter.newLine();
        bufferedWriter.write("TOTAL: $" + String.format("%.2f", cart.getBaseCost()));
        bufferedWriter.newLine();
        bufferedWriter.write("Reward Points Earned: " + String.format("%.2f", cart.getRewardPoints()));
        bufferedWriter.newLine();
        bufferedWriter.write("Thank you for your order!");

        bufferedWriter.close();
    }

    // Clear the cart
    private void clearOrder() {
        cart = new CustomerCart(new ArrayList<>(), currentUser);
        updateDisplay();
    }

    private void updateDisplay() {
    }

    private void updateUserLabel() {
    }
}

package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class MiniStatement extends JFrame {

    // Labels to display mini statement details
    JLabel mini, cardLabel, balanceLabel;

    // Constructor takes the user's PIN number
    MiniStatement(String pinnumber) {
        setTitle("NBI - Mini Statement");

        // Set layout and background color
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Bank name label at the top
        JLabel bankLabel = new JLabel("National Bank Of India");
        bankLabel.setFont(new Font("Raleway", Font.BOLD, 22));
        bankLabel.setBounds(80, 20, 300, 30);
        add(bankLabel);

        // Label to show masked card number
        cardLabel = new JLabel();
        cardLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        cardLabel.setBounds(20, 80, 350, 30);
        add(cardLabel);

        // Label to display the list of transactions
        mini = new JLabel();
        mini.setFont(new Font("Raleway", Font.PLAIN, 14));
        mini.setVerticalAlignment(SwingConstants.TOP);
        mini.setBounds(20, 130, 360, 300);
        add(mini);

        // Label to display current account balance
        balanceLabel = new JLabel();
        balanceLabel.setFont(new Font("Raleway", Font.BOLD, 16));
        balanceLabel.setBounds(20, 450, 350, 30);
        add(balanceLabel);

        // Fetch and display card and transaction details
        fetchCardDetails(pinnumber);
        fetchTransactionDetails(pinnumber);

        // Set frame size and position, then make it visible
        setSize(400, 550);
        setLocation(500, 100);
        setVisible(true);
    }

    // Method to fetch card details from login table and mask card number
    private void fetchCardDetails(String pinnumber) {
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("SELECT * FROM login WHERE pin = '" + pinnumber + "'");
            if (rs.next()) {
                String cardNumber = rs.getString("cardnumber");
                // Show only first 4 and last 4 digits, mask middle 8 digits with X
                cardLabel.setText("Card Number: " 
                    + cardNumber.substring(0, 4) + "XXXXXXXX" + cardNumber.substring(12));
            }
        } catch (Exception e) {
            System.out.println("Error fetching card details: " + e);
        }
    }

    // Method to fetch transaction history and calculate current balance
    private void fetchTransactionDetails(String pinnumber) {
        try {
            Conn conn = new Conn();
            int balance = 0;

            // Query all transactions for this PIN from bank table
            ResultSet rs = conn.s.executeQuery("SELECT * FROM bank WHERE pin = '" + pinnumber + "'");

            // Build an HTML string to display transactions with formatting
            StringBuilder transactions = new StringBuilder("<html>");
            while (rs.next()) {
                String date = rs.getString("date");      // Transaction date
                String type = rs.getString("type");      // Deposit or Withdrawl
                String amount = rs.getString("amount");  // Transaction amount

                // Append formatted transaction info to the string
                transactions.append(date).append("&nbsp;&nbsp;&nbsp;&nbsp;")
                            .append(type).append("&nbsp;&nbsp;&nbsp;&nbsp;Rs ").append(amount)
                            .append("<br><br>");

                // Calculate running balance
                if (type.equals("Deposit")) {
                    balance += Integer.parseInt(amount);
                } else {
                    balance -= Integer.parseInt(amount);
                }
            }
            transactions.append("</html>");  // Close HTML formatting

            // Set text to the mini statement label and balance label
            mini.setText(transactions.toString());
            balanceLabel.setText("Current Balance: Rs " + balance);
        } catch (Exception e) {
            System.out.println("Error fetching transactions: " + e);
        }
    }

    // Main method for quick testing (pass a valid pin to test)
    public static void main(String[] args) {
        new MiniStatement(""); // Provide a valid PIN here for testing
    }
}









package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class MiniStatement extends JFrame {

    JLabel mini, cardLabel, balanceLabel;

    MiniStatement(String pinnumber) {
        setTitle("NBI - Mini Statement");

        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel bankLabel = new JLabel("National Bank Of India");
        bankLabel.setFont(new Font("Raleway", Font.BOLD, 22));
        bankLabel.setBounds(80, 20, 300, 30);
        add(bankLabel);

        cardLabel = new JLabel();
        cardLabel.setFont(new Font("Raleway", Font.PLAIN, 16));
        cardLabel.setBounds(20, 80, 350, 30);
        add(cardLabel);

        mini = new JLabel();
        mini.setFont(new Font("Raleway", Font.PLAIN, 14));
        mini.setVerticalAlignment(SwingConstants.TOP);
        mini.setBounds(20, 130, 360, 300);
        add(mini);

        balanceLabel = new JLabel();
        balanceLabel.setFont(new Font("Raleway", Font.BOLD, 16));
        balanceLabel.setBounds(20, 450, 350, 30);
        add(balanceLabel);

        fetchCardDetails(pinnumber);
        fetchTransactionDetails(pinnumber);

        setSize(400, 550);
        setLocation(500, 100);
        setVisible(true);
    }

    private void fetchCardDetails(String pinnumber) {
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("SELECT * FROM login WHERE pin = '" + pinnumber + "'");
            if (rs.next()) {
                String cardNumber = rs.getString("cardnumber");
                cardLabel.setText("Card Number: " + cardNumber.substring(0, 4) + "XXXXXXXX" + cardNumber.substring(12));
            }
        } catch (Exception e) {
            System.out.println("Error fetching card details: " + e);
        }
    }

    private void fetchTransactionDetails(String pinnumber) {
        try {
            Conn conn = new Conn();
            int balance = 0;
            ResultSet rs = conn.s.executeQuery("SELECT * FROM bank WHERE pin = '" + pinnumber + "'");

            StringBuilder transactions = new StringBuilder("<html>");
            while (rs.next()) {
                String date = rs.getString("date");
                String type = rs.getString("type");
                String amount = rs.getString("amount");

                transactions.append(date).append("&nbsp;&nbsp;&nbsp;&nbsp;")
                            .append(type).append("&nbsp;&nbsp;&nbsp;&nbsp;Rs ").append(amount)
                            .append("<br><br>");

                if (type.equals("Deposit")) {
                    balance += Integer.parseInt(amount);
                } else {
                    balance -= Integer.parseInt(amount);
                }
            }
            transactions.append("</html>");
            mini.setText(transactions.toString());
            balanceLabel.setText("Current Balance: Rs " + balance);
        } catch (Exception e) {
            System.out.println("Error fetching transactions: " + e);
        }
    }

    public static void main(String[] args) {
        new MiniStatement(""); // Provide pin here for testing
    }
}









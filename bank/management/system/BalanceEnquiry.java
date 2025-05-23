package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BalanceEnquiry extends JFrame implements ActionListener {
    
    JButton back;        // Back button to return to Transactions screen
    String pinnumber;    // User's PIN number

    // Constructor
    BalanceEnquiry(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        // Load and scale background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        // Back button setup
        back = new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        // Initialize database connection
        Conn c = new Conn();
        int balance = 0;  // Variable to hold the calculated balance

        try {
            // Query all transactions for the user's pin
            ResultSet rs = c.s.executeQuery("select * from bank where pin = '" + pinnumber + "'");
            
            // Calculate balance by summing deposits and subtracting withdrawals
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
        } catch (Exception e) {
            System.out.println(e);  // Print any exceptions
        }

        // Display the balance information on the screen
        JLabel text = new JLabel("Your Current Account Balance is Rs " + balance);
        text.setForeground(Color.WHITE);
        text.setBounds(170, 300, 400, 30);
        image.add(text);

        // JFrame settings
        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    // Handle button actions
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transactions(pinnumber).setVisible(true); // Return to Transactions screen
    }

    // Main method to launch BalanceEnquiry window
    public static void main(String args[]) {
        new BalanceEnquiry("");
    }
}

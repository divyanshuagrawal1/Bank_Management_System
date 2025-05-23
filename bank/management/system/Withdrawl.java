package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Withdrawl extends JFrame implements ActionListener {

    JTextField amount;          // Input field for withdrawal amount
    JButton withdraw, back;     // Buttons for withdraw and back
    String pinnumber;           // User's PIN number

    // Constructor
    Withdrawl(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        // Load and scale background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        // Label prompting user to enter withdrawal amount
        JLabel text = new JLabel("ENTER AMOUNT YOU WANT TO WITHDRAW");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(170, 300, 400, 20);
        image.add(text);

        // Text field to input the amount
        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(170, 350, 320, 25);
        image.add(amount);

        // Withdraw button
        withdraw = new JButton("WITHDRAW");
        withdraw.setBounds(355, 485, 150, 30);
        withdraw.addActionListener(this);
        image.add(withdraw);

        // Back button
        back = new JButton("BACK");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        // JFrame settings
        setSize(900, 900);
        setLocation(300, 0);
        // setUndecorated(true);
        setVisible(true);
    }

    // Handle button actions
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == withdraw) {
            String number = amount.getText();  // Get entered amount
            Date date = new Date();             // Current date/time

            if (number.equals("")) {
                // Show message if amount field is empty
                JOptionPane.showMessageDialog(null, "Please enter the Amount you want to Withdraw");
            } else {
                try {
                    // Insert withdrawal record into bank table
                    Conn conn = new Conn();
                    String query = "insert into bank values('" + pinnumber + "', '" + date + "', 'Withdrawl', '" + number + "')";
                    conn.s.executeUpdate(query);

                    // Show success message and go back to Transactions screen
                    JOptionPane.showMessageDialog(null, "Rs. " + number + " Withdraw Successfully");
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);

                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        } else if (ae.getSource() == back) {
            // Return to Transactions screen
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }

    // Main method to launch the Withdrawl window
    public static void main(String args[]) {
        new Withdrawl("");
    }
}


    public static void main(String args[]) {
         new Withdrawl("");
    }
}

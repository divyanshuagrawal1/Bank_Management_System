package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JButton login, signup, clear;
    JTextField cardTextField;
    JPasswordField pinTextField;

    Login() {
        setTitle("Bank of Future - Secure ATM Login");
        setLayout(null);

        // Background panel with color theme
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(230, 240, 255)); // Light banking blue
        mainPanel.setBounds(0, 0, 800, 480);
        add(mainPanel);

        // Logo
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(i2));
        label.setBounds(360, 20, 80, 80);
        mainPanel.add(label);

        // Welcome text
        JLabel text = new JLabel("Welcome to Bank of Future");
        text.setFont(new Font("Segoe UI", Font.BOLD, 28));
        text.setForeground(new Color(0, 51, 102)); // Deep blue
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setBounds(200, 110, 400, 40);
        mainPanel.add(text);

        // Card No Label
        JLabel cardno = new JLabel("Card Number:");
        cardno.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        cardno.setBounds(150, 180, 150, 30);
        mainPanel.add(cardno);

        // Card Text Field
        cardTextField = new JTextField();
        cardTextField.setBounds(310, 180, 300, 30);
        cardTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        cardTextField.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102)));
        cardTextField.setToolTipText("Enter your card number");
        mainPanel.add(cardTextField);

        // PIN Label
        JLabel pin = new JLabel("PIN:");
        pin.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        pin.setBounds(150, 230, 150, 30);
        mainPanel.add(pin);

        // PIN Field
        pinTextField = new JPasswordField();
        pinTextField.setBounds(310, 230, 300, 30);
        pinTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        pinTextField.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102)));
        pinTextField.setToolTipText("Enter your secure PIN");
        mainPanel.add(pinTextField);

        // Sign In Button
        login = new JButton("Sign In");
        login.setBounds(310, 290, 130, 35);
        styleButton(login);
        login.addActionListener(this);
        mainPanel.add(login);

        // Clear Button
        clear = new JButton("Clear");
        clear.setBounds(480, 290, 130, 35);
        styleButton(clear);
        clear.addActionListener(this);
        mainPanel.add(clear);

        // Sign Up Button
        signup = new JButton("Sign Up");
        signup.setBounds(310, 340, 300, 35);
        signup.setBackground(new Color(0, 102, 204));
        signup.setForeground(Color.WHITE);
        signup.setFont(new Font("Segoe UI", Font.BOLD, 16));
        signup.setFocusPainted(false);
        signup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signup.addActionListener(this);
        mainPanel.add(signup);

        // Final Frame Settings
        setSize(800, 480);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Reusable button style
    private void styleButton(JButton button) {
        button.setBackground(new Color(0, 51, 102));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 15));
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder());
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == clear) {
            cardTextField.setText("");
            pinTextField.setText("");
        } else if (ae.getSource() == login) {
            Conn conn = new Conn();
            String cardnumber = cardTextField.getText();
            String pinnumber = new String(pinTextField.getPassword());

            String query = "select * from login where cardnumber = '" + cardnumber + "' and pin = '" + pinnumber + "'";

            try {
                ResultSet rs = conn.s.executeQuery(query);
                if (rs.next()) {
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (ae.getSource() == signup) {
            setVisible(false);
            new SignupOne().setVisible(true);
        }
    }

    public static void main(String args[]) {
        new Login();
    }
}

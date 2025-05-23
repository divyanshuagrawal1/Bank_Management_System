
package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;
import java.util.*;

public class SignupOne extends JFrame implements ActionListener {

    // Unique form number
    long random;

    // Form input fields
    JTextField nameTextField, fnameTextField, emailTextField, addressTextField, cityTextField, stateTextField, pinTextField;
    JButton next;
    JRadioButton male, female, other, married, unmarried;
    JDateChooser dateChooser;

    // Constructor to set up the form UI
    SignupOne() {
        setLayout(null);

        // Header Panel
        JPanel header = new JPanel();
        header.setBackground(new Color(0, 102, 204));
        header.setBounds(0, 0, 850, 90);
        header.setLayout(null);
        add(header);

        // Bank logo
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(i2));
        logo.setBounds(30, 5, 80, 80);
        header.add(logo);

        // Bank title
        JLabel bankLabel = new JLabel("NATIONAL BANK OF INDIA");
        bankLabel.setFont(new Font("Verdana", Font.BOLD, 28));
        bankLabel.setForeground(Color.WHITE);
        bankLabel.setBounds(120, 25, 500, 40);
        header.add(bankLabel);

        // Generate unique application form number
        Random ran = new Random();
        random = Math.abs((ran.nextLong() % 9000L) + 1000L);

        JLabel formno = new JLabel("APPLICATION FORM NO. " + random);
        formno.setFont(new Font("Raleway", Font.BOLD, 22));
        formno.setBounds(260, 100, 400, 30);
        add(formno);

        JLabel personDetails = new JLabel("Page 1: Personal Details");
        personDetails.setFont(new Font("Raleway", Font.BOLD, 18));
        personDetails.setBounds(300, 140, 400, 30);
        add(personDetails);

        // Form labels and fields
        addLabel("Name:", 180);
        nameTextField = addTextField(180);

        addLabel("Father's Name:", 230);
        fnameTextField = addTextField(230);

        addLabel("Date Of Birth:", 280);
        dateChooser = new JDateChooser();
        dateChooser.setBounds(300, 280, 400, 30);
        dateChooser.setFont(new Font("Arial", Font.PLAIN, 14));
        add(dateChooser);

        addLabel("Gender:", 330);
        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        male.setBounds(300, 330, 80, 30);
        female.setBounds(390, 330, 100, 30);
        styleRadio(male);
        styleRadio(female);
        ButtonGroup gendergroup = new ButtonGroup();
        gendergroup.add(male);
        gendergroup.add(female);
        add(male);
        add(female);

        addLabel("E-Mail:", 380);
        emailTextField = addTextField(380);

        addLabel("Marital Status:", 430);
        married = new JRadioButton("Married");
        unmarried = new JRadioButton("Unmarried");
        other = new JRadioButton("Other");
        styleRadio(married);
        styleRadio(unmarried);
        styleRadio(other);
        married.setBounds(300, 430, 100, 30);
        unmarried.setBounds(410, 430, 120, 30);
        other.setBounds(540, 430, 100, 30);
        ButtonGroup maritalgroup = new ButtonGroup();
        maritalgroup.add(married);
        maritalgroup.add(unmarried);
        maritalgroup.add(other);
        add(married);
        add(unmarried);
        add(other);

        addLabel("Address:", 480);
        addressTextField = addTextField(480);

        addLabel("City:", 530);
        cityTextField = addTextField(530);

        addLabel("State:", 580);
        stateTextField = addTextField(580);

        addLabel("Pin Code:", 630);
        pinTextField = addTextField(630);

        // Next Button
        next = new JButton("Next");
        next.setBackground(new Color(0, 102, 204));
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Arial", Font.BOLD, 14));
        next.setBounds(620, 690, 100, 35);
        next.setFocusPainted(false);
        next.addActionListener(this);
        add(next);

        // JFrame properties
        setTitle("New Account Application Form");
        setSize(850, 800);
        setLocation(350, 10);
        setVisible(true);
    }

    // Method to add label
    private void addLabel(String text, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Raleway", Font.BOLD, 18));
        label.setBounds(100, y, 200, 30);
        add(label);
    }

    // Method to add text field
    private JTextField addTextField(int y) {
        JTextField tf = new JTextField();
        tf.setFont(new Font("Arial", Font.PLAIN, 14));
        tf.setBounds(300, y, 400, 30);
        add(tf);
        return tf;
    }

    // Method to style radio buttons
    private void styleRadio(JRadioButton rb) {
        rb.setBackground(new Color(245, 245, 245));
        rb.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    // Action listener for the "Next" button
    public void actionPerformed(ActionEvent ae) {
        String formno = "" + random;
        String name = nameTextField.getText();
        String fname = fnameTextField.getText();
        String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        String gender = male.isSelected() ? "Male" : (female.isSelected() ? "Female" : null);
        String email = emailTextField.getText();
        String marital = married.isSelected() ? "Married" :
                         unmarried.isSelected() ? "Unmarried" :
                         other.isSelected() ? "Other" : null;
        String address = addressTextField.getText();
        String city = cityTextField.getText();
        String state = stateTextField.getText();
        String pin = pinTextField.getText();

        try {
            // Validation for empty fields
            if (name.isEmpty() || fname.isEmpty() || dob.isEmpty() || gender == null || email.isEmpty() ||
                marital == null || address.isEmpty() || city.isEmpty() || state.isEmpty() || pin.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all required fields");
                return;
            }

            // Insert data into database
            Conn c = new Conn();
            String query = "INSERT INTO signup VALUES('" + formno + "','" + name + "','" + fname + "','" + dob + "','" + gender + "','" + email + "','" + marital + "','" + address + "','" + city + "','" + pin + "','" + state + "')";
            c.s.executeUpdate(query);

            // Open next form
            setVisible(false);
            new SignupTwo(formno).setVisible(true);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Main method to launch the form
    public static void main(String args[]) {
        new SignupOne();
    }
}

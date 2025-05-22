package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SignupTwo extends JFrame implements ActionListener {

    JTextField pan, aadhar;
    JButton next;
    JRadioButton syes, sno, eyes, eno;
    JComboBox religion, category, occupation, education, income;
    String formno;

    SignupTwo(String formno) {
        this.formno = formno;
        setLayout(null);
        setTitle("New Account Application Form - Page 2");

        // Header Panel
        JPanel header = new JPanel();
        header.setBackground(new Color(0, 102, 204));
        header.setBounds(0, 0, 850, 90);
        header.setLayout(null);
        add(header);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(i2));
        logo.setBounds(30, 5, 80, 80);
        header.add(logo);

        JLabel bankLabel = new JLabel("NATIONAL BANK OF INDIA");
        bankLabel.setFont(new Font("Verdana", Font.BOLD, 28));
        bankLabel.setForeground(Color.WHITE);
        bankLabel.setBounds(120, 25, 500, 40);
        header.add(bankLabel);

        JLabel additionalDetails = new JLabel("Page 2: Additional Details");
        additionalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        additionalDetails.setBounds(280, 100, 400, 30);
        add(additionalDetails);

        addLabel("Religion:", 160);
        religion = addComboBox(new String[]{"Hindu", "Muslim", "Sikh", "Christian", "Other"}, 160);

        addLabel("Category:", 210);
        category = addComboBox(new String[]{"General", "OBC", "SC", "ST", "Other"}, 210);

        addLabel("Income:", 260);
        income = addComboBox(new String[]{"Null", "<1,50,000", "<2,50,000", "<5,00,000", "Upto 10,00,000"}, 260);

        addLabel("Educational:", 310);
        addLabel("Qualification:", 335);
        education = addComboBox(new String[]{"Non-Graduation", "Graduate", "Post Graduation", "Doctrate", "Others"}, 335);

        addLabel("Occupation:", 410);
        occupation = addComboBox(new String[]{"Salaried", "Self-Employed", "Business", "Student", "Retired", "Others"}, 410);

        addLabel("PAN Number:", 460);
        pan = addTextField(460);

        addLabel("Aadhar Number:", 510);
        aadhar = addTextField(510);

        addLabel("Senior Citizen:", 560);
        syes = createRadio("Yes", 300, 560);
        sno = createRadio("No", 420, 560);
        ButtonGroup seniorGroup = new ButtonGroup();
        seniorGroup.add(syes);
        seniorGroup.add(sno);

        addLabel("Existing Account:", 610);
        eyes = createRadio("Yes", 300, 610);
        eno = createRadio("No", 420, 610);
        ButtonGroup accountGroup = new ButtonGroup();
        accountGroup.add(eyes);
        accountGroup.add(eno);

        next = new JButton("Next");
        next.setBounds(620, 670, 100, 35);
        next.setBackground(new Color(0, 102, 204));
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Arial", Font.BOLD, 14));
        next.setFocusPainted(false);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(new Color(245, 245, 245));
        setSize(850, 800);
        setLocation(350, 10);
        setVisible(true);
    }

    private void addLabel(String text, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Raleway", Font.BOLD, 18));
        label.setBounds(100, y, 200, 30);
        add(label);
    }

    private JTextField addTextField(int y) {
        JTextField tf = new JTextField();
        tf.setFont(new Font("Arial", Font.PLAIN, 14));
        tf.setBounds(300, y, 400, 30);
        add(tf);
        return tf;
    }

    private JComboBox addComboBox(String[] items, int y) {
        JComboBox box = new JComboBox(items);
        box.setBounds(300, y, 400, 30);
        box.setBackground(Color.WHITE);
        box.setFont(new Font("Arial", Font.PLAIN, 14));
        add(box);
        return box;
    }

    private JRadioButton createRadio(String label, int x, int y) {
        JRadioButton rb = new JRadioButton(label);
        rb.setBounds(x, y, 100, 30);
        rb.setBackground(new Color(245, 245, 245));
        rb.setFont(new Font("Arial", Font.PLAIN, 14));
        add(rb);
        return rb;
    }

    public void actionPerformed(ActionEvent ae) {
        String sreligion = (String) religion.getSelectedItem();
        String scategory = (String) category.getSelectedItem();
        String sincome = (String) income.getSelectedItem();
        String seducation = (String) education.getSelectedItem();
        String soccupation = (String) occupation.getSelectedItem();
        String seniorcitizen = syes.isSelected() ? "Yes" : sno.isSelected() ? "No" : null;
        String existingaccount = eyes.isSelected() ? "Yes" : eno.isSelected() ? "No" : null;
        String span = pan.getText();
        String saadhar = aadhar.getText();

        try {
            Conn c = new Conn();
            String query = "INSERT INTO signuptwo VALUES('" + formno + "','" + sreligion + "','" + scategory + "','" + sincome + "','" + seducation + "','" + soccupation + "','" + span + "','" + saadhar + "','" + seniorcitizen + "','" + existingaccount + "')";
            c.s.executeUpdate(query);

            setVisible(false);
            new SignupThree(formno).setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new SignupTwo("");
    }
}

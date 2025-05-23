package bank.management.system;

import java.sql.*;

public class UserDAO {
    private Connection conn;

    // Constructor to establish a database connection
    public UserDAO() throws Exception {
        // Load MySQL JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Connect to the bankmanagementsystem database
        conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/bankmanagementsystem", 
            "root", 
            "Divyan@2006"
        );
    }

    // Method to validate login credentials
    public boolean login(String cardNo, String pin) throws SQLException {
        String query = "SELECT * FROM login WHERE cardnumber = ? AND pin = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cardNo);
            stmt.setString(2, pin);
            ResultSet rs = stmt.executeQuery();

            // Return true if credentials match
            return rs.next();
        }
    }

    // Method to insert a new user into the login table
    public void signup(String formNo, String cardNumber, String pin) throws SQLException {
        String query = "INSERT INTO login(formno, cardnumber, pin) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, formNo);
            stmt.setString(2, cardNumber);
            stmt.setString(3, pin);
            stmt.executeUpdate();
        }
    }

    // Method to change the PIN in both login and bank tables
    public void changePin(String oldPin, String newPin) throws SQLException {
        // Update PIN in login table
        String query = "UPDATE login SET pin = ? WHERE pin = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newPin);
            stmt.setString(2, oldPin);
            stmt.executeUpdate();
        }

        // Update PIN in bank table
        String query2 = "UPDATE bank SET pin = ? WHERE pin = ?";
        try (PreparedStatement stmt2 = conn.prepareStatement(query2)) {
            stmt2.setString(1, newPin);
            stmt2.setString(2, oldPin);
            stmt2.executeUpdate();
        }
    }

    // Method to close the database connection
    public void close() throws SQLException {
        if (conn != null) conn.close();
    }
}

    public void close() throws SQLException {
        if (conn != null) conn.close();
    }
}

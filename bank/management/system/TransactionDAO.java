package bank.management.system;

import java.sql.*;
import java.util.*;

public class TransactionDAO {
    private Connection conn;

    // Constructor: Establish connection to the database
    public TransactionDAO() throws Exception {
        // Load JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Connect to the bankmanagementsystem database
        conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/bankmanagementsystem", 
            "root", 
            "Divyan@2006"
        );
    }

    // Method to add a new transaction to the 'bank' table
    public void addTransaction(String pin, String type, double amount) throws SQLException {
        String query = "INSERT INTO bank(pin, date, type, amount) VALUES (?, NOW(), ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, pin);      // PIN number of the user
            stmt.setString(2, type);     // Type of transaction: Deposit/Withdrawal
            stmt.setDouble(3, amount);   // Transaction amount
            stmt.executeUpdate();
        }
    }

    // Method to get the last 5 transactions (mini statement)
    public List<String> getMiniStatement(String pin) throws SQLException {
        List<String> list = new ArrayList<>();
        String query = "SELECT * FROM bank WHERE pin = ? ORDER BY date DESC LIMIT 5";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, pin);   // Filter by PIN
            ResultSet rs = stmt.executeQuery();

            // Format and add each transaction to the list
            while (rs.next()) {
                String row = rs.getString("date") + " - " 
                           + rs.getString("type") + " - Rs. " 
                           + rs.getString("amount");
                list.add(row);
            }
        }
        return list; // Return list of formatted mini statement entries
    }

    // Close the database connection
    public void close() throws SQLException {
        if (conn != null) conn.close();
    }
}


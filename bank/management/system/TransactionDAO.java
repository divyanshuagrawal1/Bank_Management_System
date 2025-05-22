package bank.management.system;

import java.sql.*;
import java.util.*;

public class TransactionDAO {
    private Connection conn;

    public TransactionDAO() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagementsystem", "root", "Divyan@2006");
    }

    public void addTransaction(String pin, String type, double amount) throws SQLException {
        String query = "INSERT INTO bank(pin, date, type, amount) VALUES (?, NOW(), ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, pin);
            stmt.setString(2, type);
            stmt.setDouble(3, amount);
            stmt.executeUpdate();
        }
    }

    public List<String> getMiniStatement(String pin) throws SQLException {
        List<String> list = new ArrayList<>();
        String query = "SELECT * FROM bank WHERE pin = ? ORDER BY date DESC LIMIT 5";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, pin);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String row = rs.getString("date") + " - " + rs.getString("type") + " - Rs. " + rs.getString("amount");
                list.add(row);
            }
        }
        return list;
    }

    public void close() throws SQLException {
        if (conn != null) conn.close();
    }
}


package bank.management.system;

import java.sql.*;

public class BalanceDAO {
    private Connection conn;

    public BalanceDAO() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagementsystem", "root", "Divyan@2006");
    }

    public double getBalance(String pin) throws SQLException {
        double balance = 0;
        String query = "SELECT * FROM bank WHERE pin = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, pin);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String type = rs.getString("type");
                double amt = rs.getDouble("amount");
                if (type.equalsIgnoreCase("Deposit")) {
                    balance += amt;
                } else {
                    balance -= amt;
                }
            }
        }
        return balance;
    }

    public void close() throws SQLException {
        if (conn != null) conn.close();
    }
}


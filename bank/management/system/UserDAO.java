package bank.management.system;

import java.sql.*;

public class UserDAO {
    private Connection conn;

    public UserDAO() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagementsystem", "root", "Divyan@2006");
    }

    public boolean login(String cardNo, String pin) throws SQLException {
        String query = "SELECT * FROM login WHERE cardnumber = ? AND pin = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cardNo);
            stmt.setString(2, pin);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // true if match found
        }
    }

    public void signup(String formNo, String cardNumber, String pin) throws SQLException {
        String query = "INSERT INTO login(formno, cardnumber, pin) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, formNo);
            stmt.setString(2, cardNumber);
            stmt.setString(3, pin);
            stmt.executeUpdate();
        }
    }

    public void changePin(String oldPin, String newPin) throws SQLException {
        String query = "UPDATE login SET pin = ? WHERE pin = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newPin);
            stmt.setString(2, oldPin);
            stmt.executeUpdate();
        }

        String query2 = "UPDATE bank SET pin = ? WHERE pin = ?";
        try (PreparedStatement stmt2 = conn.prepareStatement(query2)) {
            stmt2.setString(1, newPin);
            stmt2.setString(2, oldPin);
            stmt2.executeUpdate();
        }
    }

    public void close() throws SQLException {
        if (conn != null) conn.close();
    }
}

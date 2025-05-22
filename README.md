# 🏦 Bank Management System (Java Swing + MySQL)

A modern and interactive banking management system developed using Java Swing for the frontend and MySQL for the backend. It simulates essential banking operations like user registration, deposits, withdrawals, balance inquiries, PIN changes, and mini statements.

---

## 🚀 Features

- 🔐 **User Authentication** – Secure login system using card number and PIN.
- 🧾 **Signup (Multi-step)** – Collects personal, additional, and account-specific details.
- 💰 **Deposit & Withdrawal** – Users can deposit or withdraw money.
- ⚡ **Fast Cash** – Quick withdrawal options.
- 📋 **Balance Enquiry** – Displays current account balance.
- 🔁 **PIN Change** – Allows secure PIN update.
- 📄 **Mini Statement** – Shows recent transactions.
- 🖼️ **ATM-like UI** – Simulated ATM interface using Java Swing.

---
## 🛠️ Technologies Used

- 💻 Java (Swing, JDBC)
- 🐬 MySQL
- 🛢️ JDBC Driver (`com.mysql.cj.jdbc.Driver`)
- 🖌️ NetBeans IDE

---

## 📂 Project Structure

bank.management.system/
├── DAO/
│ ├── UserDAO.java # Handles login, signup, pin update
│ ├── BalanceDAO.java # Calculates account balance
│ └── TransactionDAO.java # Adds transactions, fetches mini statements
├── UI Screens
│ ├── Login.java
│ ├── SignupOne.java
│ ├── SignupTwo.java
│ ├── SignupThree.java
│ ├── Transactions.java
│ ├── Deposit.java
│ ├── Withdrawal.java
│ ├── FastCash.java
│ ├── BalanceEnquiry.java
│ ├── PinChange.java
│ └── MiniStatement.java
├── Conn.java # JDBC Connection class

---

## 🖼️ UI Highlights

- **Visual Appeal**: ATM-style UI using custom background images.
- **Component Alignment**: Precise manual positioning using `setBounds()` for all components.
- **Responsiveness**: Simplified layout in `MiniStatement.java` ensures content adjusts gracefully.
- **Accessibility**: Clear fonts, high contrast text, masked card numbers, and structured layout.


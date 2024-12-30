package org.itenas.is.oop.projek.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.itenas.is.oop.projek.model.BaseTransaction;
import org.itenas.is.oop.projek.model.IncomeTransaction;
import org.itenas.is.oop.projek.model.ExpenseTransaction;

/**
 * ControllerTransaction manages CRUD operations for BaseTransaction objects in the database.
 */
public class ControllerTransaction {
    private Connection connection;

    public ControllerTransaction(Connection connection) {
        this.connection = connection;
    }

    public void addTransaction(BaseTransaction transaction) throws SQLException {
        String sql = "INSERT INTO base_transaction (date, category, description, amount, type) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(transaction.getDate().getTime()));
            stmt.setString(2, transaction.getCategory());
            stmt.setString(3, transaction.getDescription());
            stmt.setDouble(4, transaction.getAmount());
            stmt.setString(5, transaction instanceof IncomeTransaction ? "income" : "expense");
            stmt.executeUpdate();
        }
    }

    public List<BaseTransaction> getAllTransactions() throws SQLException {
        List<BaseTransaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM base_transaction";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                BaseTransaction transaction = createTransactionFromResultSet(rs);
                transactions.add(transaction);
            }
        }
        return transactions;
    }

    public List<BaseTransaction> getTransactionsByCategory(String category) throws SQLException {
        List<BaseTransaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM base_transaction WHERE category = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, category);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    BaseTransaction transaction = createTransactionFromResultSet(rs);
                    transactions.add(transaction);
                }
            }
        }
        return transactions;
    }

    public BaseTransaction getTransactionById(int id) throws SQLException {
        String sql = "SELECT * FROM base_transaction WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return createTransactionFromResultSet(rs);
                }
            }
        }
        return null;
    }

    public void updateTransaction(BaseTransaction transaction, int id) throws SQLException {
        String sql = "UPDATE base_transaction SET date = ?, category = ?, description = ?, amount = ?, type = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(transaction.getDate().getTime()));
            stmt.setString(2, transaction.getCategory());
            stmt.setString(3, transaction.getDescription());
            stmt.setDouble(4, transaction.getAmount());
            stmt.setString(5, transaction instanceof IncomeTransaction ? "income" : "expense");
            stmt.setInt(6, id);
            stmt.executeUpdate();
        }
    }

    public void deleteTransaction(int id) throws SQLException {
        String sql = "DELETE FROM base_transaction WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private BaseTransaction createTransactionFromResultSet(ResultSet rs) throws SQLException {
        String type = rs.getString("type");
        if ("income".equals(type)) {
            return new IncomeTransaction(
                rs.getDate("date"),
                rs.getString("category"),
                rs.getString("description"),
                rs.getDouble("amount")
            );
        } else {
            return new ExpenseTransaction(
                rs.getDate("date"),
                rs.getString("category"),
                rs.getString("description"),
                rs.getDouble("amount")
            );
        }
    }
}

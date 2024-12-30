package org.itenas.is.oop.projek.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.itenas.is.oop.projek.model.BaseTransaction;
import org.itenas.is.oop.projek.repository.ControllerTransaction;
/**
 *
 * @author asus
 */
public class TransactionService {
    private ControllerTransaction repository;

    public TransactionService(Connection connection) {
        this.repository = new ControllerTransaction(connection);
    }

    public void addTransaction(BaseTransaction transaction) throws SQLException {
        repository.addTransaction(transaction);
    }

    public List<BaseTransaction> getAllTransactions() throws SQLException {
        return repository.getAllTransactions();
    }

    public void updateTransaction(BaseTransaction transaction, int id) throws SQLException {
        repository.updateTransaction(transaction, id);
    }

    public void deleteTransaction(int id) throws SQLException {
        repository.deleteTransaction(id);
    }
}

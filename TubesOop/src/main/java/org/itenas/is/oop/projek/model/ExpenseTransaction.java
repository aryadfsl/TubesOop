package org.itenas.is.oop.projek.model;

import java.util.Date;
import org.itenas.is.oop.projek.service.TransactionDetails;
/**
 *
 * @author asus
 */
public class ExpenseTransaction extends BaseTransaction implements TransactionDetails {
    public ExpenseTransaction(Date date, String category, String description, double amount) {
        super(date, category, description, amount);
    }

    @Override
    public String getDetails() {
        return "Expense: " + description + " on " + date + " Amount: " + amount;
    }
}

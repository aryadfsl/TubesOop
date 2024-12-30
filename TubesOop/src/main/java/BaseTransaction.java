package org.itenas.is.oop.projek.model;

import java.util.Date;
/**
 *
 * @author asus
 */
public abstract class BaseTransaction {
    protected Date date;
    protected String category;
    protected String description;
    protected double amount;

    public BaseTransaction(Date date, String category, String description, double amount) {
        this.date = date;
        this.category = category;
        this.description = description;
        setAmount(amount);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.amount = amount;
    }
}

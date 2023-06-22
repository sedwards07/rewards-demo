package com.example.domain;

import java.util.Date;

public class Transaction {
    public int customerId;
    public Date date;
    public double amount;
    
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double cost) {
        this.amount = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
 
}

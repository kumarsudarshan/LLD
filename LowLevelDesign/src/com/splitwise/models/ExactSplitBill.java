package com.splitwise.models;

public class ExactSplitBill extends SplitBill {

    public ExactSplitBill(User user, double amount) {
        super(user);
        this.amount = amount;
    }
}
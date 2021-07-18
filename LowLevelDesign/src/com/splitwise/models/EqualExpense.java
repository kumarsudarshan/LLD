package com.splitwise.models;

import java.util.List;

public class EqualExpense extends Expense {
    public EqualExpense(double amount, User paidBy, List<SplitBill> splits, ExpenseMetadata expenseMetadata) {
        super(amount, paidBy, splits, expenseMetadata);
    }

    @Override
    public boolean validate() {
        for (SplitBill split : getSplits()) {
            if (!(split instanceof EqualSplitBill)) {
                return false;
            }
        }
        return true;
    }
}
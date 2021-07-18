package splitwise.models;

import java.util.List;

public class ExactExpense extends Expense {
    public ExactExpense(double amount, User paidBy, List<SplitBill> splits, ExpenseMetadata expenseMetadata) {
        super(amount, paidBy, splits, expenseMetadata);
    }

    @Override
    public boolean validate() {
        for (SplitBill split : getSplits()) {
            if (!(split instanceof ExactSplitBill)) {
                return false;
            }
        }

        double totalAmount = getAmount();
        double sumSplitAmount = 0;
        for (SplitBill split : getSplits()) {
            ExactSplitBill exactSplitBill = (ExactSplitBill) split;
            sumSplitAmount += exactSplitBill.getAmount();
        }

        if (totalAmount != sumSplitAmount) {
            return false;
        }

        return true;
    }
}
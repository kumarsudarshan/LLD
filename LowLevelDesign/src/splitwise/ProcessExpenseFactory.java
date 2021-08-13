package splitwise;

import splitwise.models.*;
import java.util.List;

public class ProcessExpenseFactory {
    public static Expense createExpense(ExpenseType expenseType, double amount, User paidBy, List<SplitBill> splitBills, ExpenseMetadata expenseMetadata) {
        switch (expenseType) {
            case EXACT:
                return new ExactExpense(amount, paidBy, splitBills, expenseMetadata);
            case PERCENT:
                for (SplitBill splitBill : splitBills) {
                    PercentSplitBill percentSplitBill = (PercentSplitBill) splitBill;
                    splitBill.setAmount((amount* percentSplitBill.getPercent())/100.0);
                }
                return new PercentExpense(amount, paidBy, splitBills, expenseMetadata);
            case EQUAL:
                int totalSplits = splitBills.size();
                double splitAmount = ((double) Math.round(amount*100/totalSplits))/100.0;
                for (SplitBill splitBill : splitBills) {
                    splitBill.setAmount(splitAmount);
                }
                splitBills.get(0).setAmount(splitAmount + (amount - splitAmount*totalSplits));
                return new EqualExpense(amount, paidBy, splitBills, expenseMetadata);
            default:
                return null;
        }
    }
}
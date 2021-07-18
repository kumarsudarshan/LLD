package splitwise;

import splitwise.models.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseProcessor {
    List<Expense> expenses;
    Map<Integer, User> userMap;
    Map<Integer, Map<Integer, Double> > balanceSheet;

    public ExpenseProcessor() {
        expenses = new ArrayList<Expense>();
        userMap = new HashMap<Integer, User>();
        balanceSheet = new HashMap<Integer, Map<Integer, Double>>();
    }

    public void addUser(User user) {
        userMap.put(user.getId(), user);
        balanceSheet.put(user.getId(), new HashMap<Integer, Double>());
    }

    public void addExpense(ExpenseType expenseType, double amount, Integer paidBy, List<SplitBill> splits, ExpenseMetadata expenseMetadata) {
        Expense expense = ProcessExpense.createExpense(expenseType, amount, userMap.get(paidBy), splits, expenseMetadata);
        expenses.add(expense);
        for (SplitBill split : expense.getSplits()) {
            Integer paidTo = split.getUser().getId();
            Map<Integer, Double> balances = balanceSheet.get(paidBy);
            if (!balances.containsKey(paidTo)) {
                balances.put(paidTo, 0.0);
            }
            balances.put(paidTo, balances.get(paidTo) + split.getAmount());

            balances = balanceSheet.get(paidTo);
            if (!balances.containsKey(paidBy)) {
                balances.put(paidBy, 0.0);
            }
            balances.put(paidBy, balances.get(paidBy) - split.getAmount());
        }
    }

    public void showBalance(Integer userId) {
        boolean isEmpty = true;
        for (Map.Entry<Integer, Double> userBalance : balanceSheet.get(userId).entrySet()) {
            if (userBalance.getValue() != 0) {
                isEmpty = false;
                printBalance(userId, userBalance.getKey(), userBalance.getValue());
            }
        }

        if (isEmpty) {
            System.out.println("No balances");
        }
    }

    public void showBalances() {
        boolean isEmpty = true;
        for (Map.Entry<Integer, Map<Integer, Double>> allBalances : balanceSheet.entrySet()) {
            for (Map.Entry<Integer, Double> userBalance : allBalances.getValue().entrySet()) {
                if (userBalance.getValue() > 0) {
                    isEmpty = false;
                    printBalance(allBalances.getKey(), userBalance.getKey(), userBalance.getValue());
                }
            }
        }

        if (isEmpty) {
            System.out.println("No balances");
        }
    }

    private void printBalance(Integer user1, Integer user2, double amount) {
        String user1Name = userMap.get(user1).getName();
        String user2Name = userMap.get(user2).getName();
        if (amount < 0) {
            System.out.println(user1Name + " owes " + user2Name + ": " + Math.abs(amount));
        } else if (amount > 0) {
            System.out.println(user2Name + " owes " + user1Name + ": " + Math.abs(amount));
        }
    }
}
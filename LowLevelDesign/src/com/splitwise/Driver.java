package com.splitwise;

import com.splitwise.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Driver {

    public static void main(String[] args) {

        List<User> users = new ArrayList<>();
        users.add(new User(101, "User1", "User1@gmail.com", "9999999999"));
        users.add(new User(102, "User2", "User2@gmail.com", "8888888888"));
        users.add(new User(103, "User3", "User3@gmail.com", "7777777777"));
        users.add(new User(104, "User4", "User4@gmail.com", "6666666666"));

        double totalAmount = 400.00;
        Integer paidBy = 101;
        calculateSplitEqually(users, 400.0, 101);
        calculateSplitPercent(users, 400.0, 101);
        calculateSplitExact(users, 400.0, 101);
    }


    public static void calculateSplitEqually(List<User> users, Double totalAmount, Integer paidBy){
        ExpenseProcessor expenseProcessor = new ExpenseProcessor();
        for(User user : users){
            expenseProcessor.addUser(user);
        }

        // split equally
        System.out.println("--------------- SPLIT EQUALLY ----------------");
        List<SplitBill> splits = new ArrayList<>();
        for (Map.Entry<Integer, User> user : expenseProcessor.userMap.entrySet()) {
            splits.add(new EqualSplitBill(user.getValue()));
        }
        expenseProcessor.addExpense(ExpenseType.EQUAL, totalAmount, paidBy, splits, null);
        expenseProcessor.showBalances();
//        expenseProcessor.showBalance(101);
//        expenseProcessor.showBalance(102);
//        expenseProcessor.showBalance(103);
//        expenseProcessor.showBalance(104);
    }

    public static void calculateSplitPercent(List<User> users, Double totalAmount, Integer paidBy){
        ExpenseProcessor expenseProcessor = new ExpenseProcessor();
        for(User user : users){
            expenseProcessor.addUser(user);
        }

        System.out.println();
        System.out.println("--------------- SPLIT PERCENT ----------------");
        // percent split
        List<SplitBill> splits = new ArrayList<>();
        splits.add(new PercentSplitBill(expenseProcessor.userMap.get(101), 40));
        splits.add(new PercentSplitBill(expenseProcessor.userMap.get(102), 40));
        splits.add(new PercentSplitBill(expenseProcessor.userMap.get(103), 10));
        splits.add(new PercentSplitBill(expenseProcessor.userMap.get(104), 10));

        expenseProcessor.addExpense(ExpenseType.PERCENT, totalAmount, paidBy, splits, null);
        expenseProcessor.showBalances();
//        expenseProcessor.showBalance(101);
    }

    public static void calculateSplitExact(List<User> users, Double totalAmount, Integer paidBy){
        ExpenseProcessor expenseProcessor = new ExpenseProcessor();
        for(User user : users){
            expenseProcessor.addUser(user);
        }

        System.out.println();
        System.out.println("--------------- SPLIT EXACT ----------------");
        // split exact
        List<SplitBill> splits = new ArrayList<>();
        splits.add(new ExactSplitBill(expenseProcessor.userMap.get(101), 100));
        splits.add(new ExactSplitBill(expenseProcessor.userMap.get(102), 150));
        splits.add(new ExactSplitBill(expenseProcessor.userMap.get(103), 80));
        splits.add(new ExactSplitBill(expenseProcessor.userMap.get(104), 70));

        expenseProcessor.addExpense(ExpenseType.EXACT, totalAmount, paidBy, splits, null);
        expenseProcessor.showBalances();
//        expenseProcessor.showBalance(101);
    }
}
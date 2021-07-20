package billsharing.test;

import billsharing.exceptions.ContributionExceededException;
import billsharing.exceptions.ExpenseDoesNotExistsException;
import billsharing.exceptions.ExpenseSettledException;
import billsharing.exceptions.InvalidExpenseState;
import billsharing.model.*;
import billsharing.repository.ExpenseRepository;
import billsharing.service.ExpenseService;
import billsharing.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    static UserService userService;
    static ExpenseService expenseService;

    @Before
    public void initClass() {
        userService = new UserService();
        expenseService = new ExpenseService();
    }

    @Test
    public void createUserTest() {
        User user = userService.createUser("bagesh@gmail.com", "bagesh", "3486199635");
        assertNotNull(user);
        Assert.assertEquals("bagesh@gmail.com", user.getEmailId());
    }

    @Test
    public void createUserNullEmailTest() {
        Assert.assertThrows(NullPointerException.class, () -> {
            User user = userService.createUser(null, "bagesh", "3486199635");
        });
    }

    private static void bifurcateExpense(String expenseId) throws ExpenseDoesNotExistsException {
        expenseService.addUsersToExpense(expenseId, "bagesh@gmail.com");
        expenseService.assignExpenseShare(expenseId, ExpenseRepository.expenseMap.get(expenseId).getUserId(), 2000);
    }

    @Test
    public void contributeToExpense() throws ContributionExceededException, InvalidExpenseState, ExpenseSettledException {
        User user = userService.createUser("bagesh@gmail.com", "bagesh", "3486199635");
        Expense expense = expenseService.createExpense("Team Lunch", "Friday 19Th June Lunch in Briyani zone"
                , LocalDateTime.of(2020, Month.JUNE, 19, 12, 0), 2000.00, user.getEmailId());
        try {
            bifurcateExpense(expense.getId());
        } catch (ExpenseDoesNotExistsException expenseDoesNotExistsException) {
            System.out.println(expenseDoesNotExistsException.getMessage());
        }
        expense.setExpenseStatus(ExpenseStatus.PENDING);
        Set<User> users = expense.getExpenseGroup().getGroupMembers();
        for (User usr : users) {
            Contribution contribution = new Contribution();
            ExpenseGroup expenseGroup = expense.getExpenseGroup();
            UserShare userShare = expenseGroup.getUserContributions().get(usr.getEmailId());
            contribution.setContributionValue(600);
            contribution.setContributionDate(LocalDateTime.now());
            contribution.setTransactionId("T" + Instant.EPOCH);
            contribution.setTransactionDescription("Transferred from UPI");
            userService.contributeToExpense(expense.getId(), usr.getEmailId(), contribution);
        }
        Assert.assertTrue((int) expense.getExpenseGroup().getUserContributions().get(user.getEmailId()).getContributions().get(0).getContributionValue() == 600);
    }
}
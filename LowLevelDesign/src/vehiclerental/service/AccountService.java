package vehiclerental.service;


import vehiclerental.exceptions.AccountDoesNotExistsException;
import vehiclerental.model.account.Account;
import vehiclerental.model.account.AccountType;

public interface AccountService {
    Account createAccount(Account account, AccountType accountType);

    void resetPassword(String userId, String password,
                       AccountType accountType) throws AccountDoesNotExistsException;
}

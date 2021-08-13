package vehiclerental.repository;


import vehiclerental.exceptions.AccountDoesNotExistsException;
import vehiclerental.model.account.Account;

public interface AccountRepository {
    Account createAccount(Account account);

    void resetPassword(String userId, String password) throws AccountDoesNotExistsException;
}

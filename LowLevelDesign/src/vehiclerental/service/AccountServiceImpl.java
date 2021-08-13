package vehiclerental.service;


import vehiclerental.exceptions.AccountDoesNotExistsException;
import vehiclerental.model.account.Account;
import vehiclerental.model.account.AccountType;
import vehiclerental.repository.AccountRepository;
import vehiclerental.repository.AccountRepositoryFactory;

public class AccountServiceImpl implements AccountService {

    @Override
    public Account createAccount(Account account, AccountType accountType) {
        AccountRepository accountRepository =
                AccountRepositoryFactory.getAccountRepository(accountType);
        return accountRepository.createAccount(account);
    }

    public void resetPassword(String userId, String password,
                              AccountType accountType) throws AccountDoesNotExistsException {
        AccountRepository accountRepository =
                AccountRepositoryFactory.getAccountRepository(accountType);
        accountRepository.resetPassword(userId, password);
    }
}

package vehiclerental.repository;


import vehiclerental.model.account.AccountType;

public class AccountRepositoryFactory {
    public static AccountRepository getAccountRepository(AccountType accountType) {
        switch (accountType) {
            case USER:
                return new UserRepository();
            case ADMIN:
                return new AdminRepository();
            case DRIVER:
                return new DriverRepository();
            default:
                return null;
        }
    }
}

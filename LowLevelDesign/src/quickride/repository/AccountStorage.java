package quickride.repository;

import quickride.exceptions.AccountDoesNotExistsException;
import quickride.model.account.Account;
import quickride.model.common.Location;

public interface AccountStorage {

    public Account createAccount(Account account);

    public void resetPassword(String userId, String password) throws AccountDoesNotExistsException;

    public boolean updateLocation(String driverMailId, Location currentLocation);

    public boolean accountExists(String mailId);
}

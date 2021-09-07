package quickride.repository;


import quickride.model.account.Account;
import quickride.model.account.Driver;
import quickride.model.common.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverRepository implements AccountStorage {
    public static Map<String, Driver> driverMap = new HashMap<>();

    private static DriverRepository INSTANCE = null;

    private DriverRepository() {

    }

    public static DriverRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (DriverRepository.class) {
                if (INSTANCE == null) {
                    return new DriverRepository();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Account createAccount(Account account) {
        driverMap.putIfAbsent(account.getEmail(), (Driver) account);
        return account;
    }

    @Override
    public boolean updateLocation(String driverMailId, Location currentLocation) {
        if (driverMap.containsKey(driverMailId)) {
            driverMap.get(driverMailId).setCurrentLocation(currentLocation);
            return true;
        }
        return false;
    }

    @Override
    public boolean accountExists(String mailId) {
        return driverMap.containsKey(mailId) ? true : false;
    }

    @Override
    public void resetPassword(String userId, String password) {

    }
}

package quickride.repository;


import quickride.exceptions.AccountDoesNotExistsException;
import quickride.model.account.Account;
import quickride.model.account.Rider;
import quickride.model.common.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RiderRepository implements AccountStorage {
    public static Map<String, Rider> riderMap = new HashMap<>();
    public static Map<Integer, Rider> riderUserIdMap = new HashMap<>();
    public static List<Rider> users = new ArrayList<>();

    private static RiderRepository INSTANCE = null;

    private RiderRepository() {

    }

    public static RiderRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (RiderRepository.class) {
                if (INSTANCE == null) {
                    return new RiderRepository();
                }
            }
        }
        return INSTANCE;
    }

    public Account createAccount(Account account) {
        riderMap.putIfAbsent(account.getEmail(), (Rider) account);
        riderUserIdMap.putIfAbsent(account.getId(), (Rider) account);
        return account;
    }

    @Override
    public boolean updateLocation(String driverMailId, Location currentLocation) {
        if (riderMap.containsKey(driverMailId)) {
            riderMap.get(driverMailId).setCurrentLocation(currentLocation);
            return true;
        }
        return false;
    }

    @Override
    public boolean accountExists(String mailId) {
        return riderMap.containsKey(mailId) ? true : false;
    }

    public void resetPassword(String userId, String password) throws AccountDoesNotExistsException {
        if (riderMap.get(userId) == null)
            throw new AccountDoesNotExistsException("Account does not exist.");
        riderMap.get(userId).setPassword(password);
    }

}

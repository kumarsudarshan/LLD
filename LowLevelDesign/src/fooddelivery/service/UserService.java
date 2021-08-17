package fooddelivery.service;

import fooddelivery.exceptions.AccountDoesNotExistsException;
import fooddelivery.exceptions.InvalidValueException;
import fooddelivery.model.account.User;
import fooddelivery.model.common.Gender;
import fooddelivery.repository.Storage;
import fooddelivery.repository.StorageFactory;
import fooddelivery.repository.StorageType;

public class UserService {
    private static volatile UserService INSTANCE = null;
    private Storage storage = null;

    private UserService() {
        storage = StorageFactory.getDBStorage(StorageType.IN_MEMORY_STORE);
    }

    public static UserService getInstance() {
        if (INSTANCE == null) {
            synchronized (UserService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserService();
                }
            }
        }
        return INSTANCE;
    }

    public User registerUser(Long phone, String name, Long pinCode, Gender gender) throws InvalidValueException {
        if (phone == null || phone <= 0) {
            throw new InvalidValueException("Invalid phone number");
        } else if (pinCode == null || pinCode <= 0) {
            throw new InvalidValueException("Invalid pincode");
        } else if (name.isEmpty()) {
            throw new InvalidValueException("Name is empty");
        }
        return storage.registerUser(phone, name, pinCode, gender);
    }

    public User login(Long id) throws AccountDoesNotExistsException {
        return storage.login(id);
    }
}
package billsharing.repository;

import billsharing.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    public static Map<String, User> userHashMap = new HashMap<>();
}

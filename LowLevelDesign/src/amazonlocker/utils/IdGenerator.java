package amazonlocker.utils;

import org.apache.commons.lang.RandomStringUtils;

public class IdGenerator {
    public static String generateId(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }
}

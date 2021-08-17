package fooddelivery.model.common;

public class KeyGenerator {
    private static int key = 0;

    public static int getKey() {
        key++;
        return key;
    }
}
package meetingscheduler.repository;

public class StorageFactory {

    public static Storage getStorage(StorageType storageType) {
        switch (storageType) {
            case IN_MEMORY:
                return HashMapBasedStorage.getInstance();
            case MYSQL:
            case MONGODB:
            default:
                return null;
        }
    }
}

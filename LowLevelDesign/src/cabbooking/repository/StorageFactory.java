package cabbooking.repository;

public class StorageFactory {
    public static Storage getDBStorage(StorageType storageType) {
        switch (storageType) {
            case IN_MEMORY_STORE:
                return HashMapBasedStorage.getInstance();
            case MONGODB:
                // call Mongo Db storage
            case MYSQL:
                // call Mongo Db storage
            default:
                return null;
        }
    }
}

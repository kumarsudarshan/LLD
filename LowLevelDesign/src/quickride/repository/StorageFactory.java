package quickride.repository;

public class StorageFactory {
    public static RideStorage getRideStorage(StorageType storageType){
        switch (storageType) {
            case IN_MEMORY:
                return RideRepository.getInstance();
            case MYSQL:
            case MONGO_DB:
            default:
                return null;
        }
    }

    public static AccountStorage getRiderStorage(StorageType storageType){
        switch (storageType) {
            case IN_MEMORY:
                return RiderRepository.getInstance();
            case MYSQL:
            case MONGO_DB:
            default:
                return null;
        }
    }

    public static AccountStorage getDriverStorage(StorageType storageType){
        switch (storageType) {
            case IN_MEMORY:
                return DriverRepository.getInstance();
            case MYSQL:
            case MONGO_DB:
            default:
                return null;
        }
    }
}

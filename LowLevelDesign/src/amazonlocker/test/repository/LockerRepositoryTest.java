package amazonlocker.test.repository;

import amazonlocker.repository.LockerRepository;
import amazonlocker.test.TestData;
import amazonlocker.model.Locker;
import amazonlocker.model.LockerLocation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LockerRepositoryTest {
    @Before
    public void setup() {
        LockerLocation lockerLocation = TestData.setupLockerLocation("RMBGBGKAIN",12.876416, 77.595466);
        LockerRepository.lockers.addAll(lockerLocation.getLockers());
        for (Locker locker : LockerRepository.lockers) {
            LockerRepository.lockerMap.put(locker.getId(), locker);
        }
    }

    @Test
    public void shouldFetchLocker() {
        Locker locker = LockerRepository.lockerMap.get(LockerRepository.lockers.get(0).getId());
        Assert.assertNotNull(locker);
    }
}

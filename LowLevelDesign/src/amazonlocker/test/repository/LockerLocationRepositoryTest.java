package amazonlocker.test.repository;

import amazonlocker.repository.LockerLocationRepository;
import amazonlocker.test.TestData;
import amazonlocker.model.LockerLocation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class LockerLocationRepositoryTest {
    @Before
    public void setup() {
        LockerLocationRepository.lockerLocations.add(
                TestData.setupLockerLocation("RMBGBGKAIN",
                        12.876416, 77.595466));
        LockerLocationRepository.lockerLocations.add(
                TestData.setupLockerLocation("VMBGBGKAIN",
                        12.909953, 77.601866));
    }

    @Test
    public void getLockerByLockerIdTest() {
        LockerLocation lockerLocation = LockerLocationRepository.getLockerLocation("RMBGBGKAIN");
        Assert.assertNotNull(lockerLocation);
    }
}

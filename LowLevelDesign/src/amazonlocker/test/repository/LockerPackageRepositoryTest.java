package amazonlocker.test.repository;

import amazonlocker.repository.LockerPackageRepository;
import amazonlocker.test.TestData;
import amazonlocker.model.LockerPackage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class LockerPackageRepositoryTest {
    @Before
    public void setup() {
        LockerPackageRepository.lockerPackages.add(TestData.getLockerPackage());
    }

    @Test
    public void shouldGetByLockerId() {
        String lockerId = LockerPackageRepository.lockerPackages.get(0).getLockerId();
        Optional<LockerPackage> lockerPackage =
                LockerPackageRepository.getLockerByLockerId(lockerId);
        Assert.assertNotNull(lockerPackage.get());
    }
}

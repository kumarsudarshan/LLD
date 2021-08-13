package vehiclerental.test.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import vehiclerental.exceptions.InvalidVehicleIdException;
import vehiclerental.exceptions.ReservationNotFoundException;
import vehiclerental.exceptions.VehicleBookedException;
import vehiclerental.model.account.User;
import vehiclerental.model.reservation.ReservationStatus;
import vehiclerental.model.reservation.VehicleInventory;
import vehiclerental.model.reservation.VehicleReservation;
import vehiclerental.model.vehicle.HireableVehicle;
import vehiclerental.model.vehicle.VehicleStatus;
import vehiclerental.repository.UserRepository;
import vehiclerental.repository.VehicleInventoryRepository;
import vehiclerental.repository.VehicleRepository;
import vehiclerental.service.UserService;
import vehiclerental.service.UserServiceImpl;
import vehiclerental.test.TestData;

import java.time.LocalDateTime;
import java.util.List;

public class UserServiceTest {
    @After
    public void clean() {
        VehicleRepository.vehicleMap.clear();
        VehicleRepository.vehicles.clear();
        UserRepository.userMap.clear();
        UserRepository.userUserIdMap.clear();
        UserRepository.users.clear();
        VehicleInventoryRepository.vehicleInventoryList.clear();
    }

    @Test
    public void should_ScanToReserve() throws VehicleBookedException, InvalidVehicleIdException {
        VehicleRepository vehicleRepository = new VehicleRepository();
        List<HireableVehicle> vehicleList = TestData.getHireableVehicles();
        for (HireableVehicle hireableVehicle : vehicleList) {
            vehicleRepository.addVehicle(hireableVehicle);
            VehicleInventoryRepository.vehicleInventoryList.add(new VehicleInventory(hireableVehicle));
        }
        User user = TestData.getUser("user@email.com");
        UserRepository.userMap.putIfAbsent("user@email.com", user);
        UserRepository.userUserIdMap.putIfAbsent(user.getId(), user);

        UserService userService = new UserServiceImpl();
        VehicleReservation vehicleReservation =
                userService.scanToReserve(vehicleList.get(1).getQrCode(), user.getId());

        Assert.assertNotNull(vehicleReservation);
    }

    @Test
    public void should_CancelReservation() throws VehicleBookedException, InvalidVehicleIdException {
        VehicleRepository vehicleRepository = new VehicleRepository();
        User user = TestData.getUser("user@email.com");
        UserRepository.userMap.putIfAbsent("user@email.com", user);
        UserRepository.userUserIdMap.putIfAbsent(user.getId(), user);
        List<HireableVehicle> vehicleList = TestData.getHireableVehicles();
        for (HireableVehicle hireableVehicle : vehicleList) {
            vehicleRepository.addVehicle(hireableVehicle);
            VehicleInventoryRepository.vehicleInventoryList.add(new VehicleInventory(hireableVehicle));
        }

        UserService userService = new UserServiceImpl();
        VehicleReservation vehicleReservation =
                userService.scanToReserve(vehicleList.get(1).getQrCode(), user.getId());

        vehicleReservation = userService.cancel(vehicleReservation.getReservationId());

        Assert.assertEquals(vehicleReservation.getStatus(), ReservationStatus.CANCELLED);
    }

    @Test
    public void should_PickupVehicle() throws VehicleBookedException, InvalidVehicleIdException {
        VehicleRepository vehicleRepository = new VehicleRepository();
        User user = TestData.getUser("user@email.com");
        UserRepository.userMap.putIfAbsent("user@email.com", user);
        UserRepository.userUserIdMap.putIfAbsent(user.getId(), user);
        List<HireableVehicle> vehicleList = TestData.getHireableVehicles();
        for (HireableVehicle hireableVehicle : vehicleList) {
            vehicleRepository.addVehicle(hireableVehicle);
            VehicleInventoryRepository.vehicleInventoryList.add(new VehicleInventory(hireableVehicle));
        }
        UserService userService = new UserServiceImpl();
        VehicleReservation vehicleReservation =
                userService.scanToReserve(vehicleList.get(1).getQrCode(), user.getId());
        userService.pickupVehicle(vehicleReservation);

        Assert.assertEquals(vehicleList.get(1).getVehicleStatus(), VehicleStatus.INUSE);
    }

    @Test
    public void should_ReturnVehicle() throws VehicleBookedException, InvalidVehicleIdException, ReservationNotFoundException {
        VehicleRepository vehicleRepository = new VehicleRepository();
        User user = TestData.getUser("user@email.com");
        UserRepository.userMap.putIfAbsent("user@email.com", user);
        UserRepository.userUserIdMap.putIfAbsent(user.getId(), user);
        List<HireableVehicle> vehicleList = TestData.getHireableVehicles();
        for (HireableVehicle hireableVehicle : vehicleList) {
            vehicleRepository.addVehicle(hireableVehicle);
            VehicleInventoryRepository.vehicleInventoryList.add(new VehicleInventory(hireableVehicle));
        }
        UserService userService = new UserServiceImpl();
        VehicleReservation vehicleReservation =
                userService.scanToReserve(vehicleList.get(1).getQrCode(), user.getId());
        userService.pickupVehicle(vehicleReservation);
        userService.returnVehicle(vehicleReservation.getReservationId(),
                vehicleList.get(1).getParkedLocation());
        Assert.assertEquals(vehicleReservation.getStatus(), ReservationStatus.COMPLETED);
        Assert.assertEquals(vehicleList.get(1).getVehicleStatus(), VehicleStatus.AVAILALBE);
    }

    @Test
    public void should_GetHiredVehicles() throws VehicleBookedException, InvalidVehicleIdException {
        VehicleRepository vehicleRepository = new VehicleRepository();
        User user = TestData.getUser("user@email.com");
        UserRepository.userMap.putIfAbsent("user@email.com", user);
        UserRepository.userUserIdMap.putIfAbsent(user.getId(), user);
        List<HireableVehicle> vehicleList = TestData.getHireableVehicles();
        for (HireableVehicle hireableVehicle : vehicleList) {
            vehicleRepository.addVehicle(hireableVehicle);
            VehicleInventoryRepository.vehicleInventoryList.add(new VehicleInventory(hireableVehicle));
        }
        UserService userService = new UserServiceImpl();
        VehicleReservation vehicleReservation =
                userService.scanToReserve(vehicleList.get(1).getQrCode(), user.getId());
        List<HireableVehicle> hiredVehicles = userService.getHiredVehicles(user.getId());
        Assert.assertEquals(hiredVehicles.size(), 1);
    }

    @Test
    public void should_GetHiredVehiclesInDateRange() throws VehicleBookedException, InvalidVehicleIdException {
        VehicleRepository vehicleRepository = new VehicleRepository();
        User user = TestData.getUser("user@email.com");
        UserRepository.userMap.putIfAbsent("user@email.com", user);
        UserRepository.userUserIdMap.putIfAbsent(user.getId(), user);
        List<HireableVehicle> vehicleList = TestData.getHireableVehicles();
        for (HireableVehicle hireableVehicle : vehicleList) {
            vehicleRepository.addVehicle(hireableVehicle);
            VehicleInventoryRepository.vehicleInventoryList.add(new VehicleInventory(hireableVehicle));
        }
        UserService userService = new UserServiceImpl();
        VehicleReservation vehicleReservation =
                userService.scanToReserve(vehicleList.get(1).getQrCode(), user.getId());
        List<HireableVehicle> hiredVehicles = userService.getHiredVehicles(user.getId(),
                LocalDateTime.now().minusDays(3), LocalDateTime.now().plusDays(1));
        Assert.assertEquals(hiredVehicles.size(), 1);

        hiredVehicles = userService.getHiredVehicles(user.getId(),
                LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(4));
        Assert.assertEquals(hiredVehicles.size(), 0);
    }

    @Test
    public void should_RemoteReserve() throws VehicleBookedException, InvalidVehicleIdException {
        VehicleRepository vehicleRepository = new VehicleRepository();
        User user = TestData.getUser("user@email.com");
        UserRepository.userMap.putIfAbsent("user@email.com", user);
        UserRepository.userUserIdMap.putIfAbsent(user.getId(), user);
        List<HireableVehicle> vehicleList = TestData.getHireableVehicles();
        for (HireableVehicle hireableVehicle : vehicleList) {
            vehicleRepository.addVehicle(hireableVehicle);
            VehicleInventoryRepository.vehicleInventoryList.add(new VehicleInventory(hireableVehicle));
        }
        UserService userService = new UserServiceImpl();
        VehicleReservation vehicleReservation = TestData.getVehicleReservation(user);
        vehicleReservation = userService.remoteReserve(vehicleReservation);
        Assert.assertNotNull(vehicleReservation);
        Assert.assertEquals(vehicleReservation.getStatus(), ReservationStatus.CONFIRMED);
    }
}

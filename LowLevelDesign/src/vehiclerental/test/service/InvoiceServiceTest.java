package vehiclerental.test.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import vehiclerental.exceptions.InvalidVehicleIdException;
import vehiclerental.exceptions.ReservationNotFoundException;
import vehiclerental.exceptions.VehicleBookedException;
import vehiclerental.model.account.User;
import vehiclerental.model.reservation.Invoice;
import vehiclerental.model.reservation.VehicleInventory;
import vehiclerental.model.reservation.VehicleReservation;
import vehiclerental.model.vehicle.HireableVehicle;
import vehiclerental.repository.UserRepository;
import vehiclerental.repository.VehicleInventoryRepository;
import vehiclerental.repository.VehicleRepository;
import vehiclerental.service.InvoiceService;
import vehiclerental.service.InvoiceServiceImpl;
import vehiclerental.service.UserService;
import vehiclerental.service.UserServiceImpl;
import vehiclerental.test.TestData;

import java.util.List;

public class InvoiceServiceTest {
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
    public void should_ComputeDailyInvoice() {
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
        InvoiceService invoiceService = new InvoiceServiceImpl();
        Invoice invoice = invoiceService.computeInvoice(vehicleReservation);
        Assert.assertNotNull(invoice);
        Assert.assertEquals(invoice.getUsageCharges(), 1600.0, 0);
        Assert.assertEquals(invoice.getAddonCost(), 500.0, 0);
        Assert.assertEquals(invoice.getTaxes(), 288.0, 0);
        Assert.assertEquals(invoice.getTotal(), 1888.0, 0);
    }

    @Test
    public void should_ComputeHourlyInvoice() throws VehicleBookedException, InvalidVehicleIdException, ReservationNotFoundException {
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
        userService.returnVehicle(vehicleReservation.getReservationId(),
                vehicleList.get(1).getParkedLocation());
        InvoiceService invoiceService = new InvoiceServiceImpl();
        Invoice invoice = invoiceService.computeInvoice(vehicleReservation);
        Assert.assertNotNull(invoice);
        Assert.assertEquals(invoice.getUsageCharges(), 100, 0);
        Assert.assertEquals(invoice.getTaxes(), 18, 0);
        Assert.assertEquals(invoice.getTotal(), 118, 0);
    }
}

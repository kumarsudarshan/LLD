package vehiclerental.test.service;

import org.junit.Assert;
import org.junit.Test;
import vehiclerental.exceptions.VehicleNotExistsException;
import vehiclerental.model.vehicle.HireableVehicle;
import vehiclerental.service.VehicleService;
import vehiclerental.service.VehicleServiceImpl;
import vehiclerental.test.TestData;

import java.util.UUID;

public class VehicleServiceTest {
    @Test
    public void addVehicleTest() {
        VehicleService vehicleService = new VehicleServiceImpl();
        HireableVehicle hireableVehicle =
                vehicleService.addVehicle(TestData.getHatchBack());
        Assert.assertNotNull(hireableVehicle);
    }

    @Test
    public void updateQrCodeTest() throws VehicleNotExistsException {
        VehicleService vehicleService = new VehicleServiceImpl();
        HireableVehicle hireableVehicle =
                vehicleService.addVehicle(TestData.getHatchBack());
        String qrCode = UUID.randomUUID().toString();
        hireableVehicle.setQrCode(qrCode);
        vehicleService.updateQrCode(hireableVehicle.getId(), qrCode);
        HireableVehicle altered = vehicleService.getVehicleById(hireableVehicle.getId());
        Assert.assertEquals(qrCode, altered.getQrCode());
    }

    @Test
    public void getVehicleByIdTest() {
        VehicleService vehicleService = new VehicleServiceImpl();
        HireableVehicle hireableVehicle =
                vehicleService.addVehicle(TestData.getHatchBack());
        HireableVehicle vehicle = vehicleService.getVehicleById(hireableVehicle.getId());
        Assert.assertNotNull(vehicle);
    }

    @Test
    public void getVehicleByQrCodeTest() {
        VehicleService vehicleService = new VehicleServiceImpl();
        HireableVehicle hireableVehicle =
                vehicleService.addVehicle(TestData.getHatchBack());
        HireableVehicle vehicle = vehicleService.getVehicleByQrCode(hireableVehicle.getQrCode());
        Assert.assertNotNull(vehicle);
    }

    @Test
    public void removeVehicleTest() throws VehicleNotExistsException {
        VehicleService vehicleService = new VehicleServiceImpl();
        HireableVehicle hireableVehicle =
                vehicleService.addVehicle(TestData.getHatchBack());
        vehicleService.removeVehicle(hireableVehicle.getId());
        HireableVehicle vehicle = vehicleService.getVehicleById(hireableVehicle.getId());
        Assert.assertNull(vehicle);
    }
}

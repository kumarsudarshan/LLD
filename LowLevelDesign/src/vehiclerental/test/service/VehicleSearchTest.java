package vehiclerental.test.service;

import org.junit.Assert;
import org.junit.Test;
import vehiclerental.model.vehicle.HireableVehicle;
import vehiclerental.model.vehicle.VehicleType;
import vehiclerental.repository.VehicleInventoryRepository;
import vehiclerental.service.VehicleSearchService;
import vehiclerental.service.VehicleSearchServiceImpl;
import vehiclerental.test.TestData;

import java.time.LocalDateTime;
import java.util.List;

public class VehicleSearchTest {
    @Test
    public void ShouldFindVehicleByType() {
        VehicleInventoryRepository.vehicleInventoryList = TestData.buildVehicleInventory();
        VehicleSearchService vehicleSearchService = new VehicleSearchServiceImpl();
        List<HireableVehicle> vehicleList = vehicleSearchService.search(VehicleType.HATCHBACK,
                "Bangalore", LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(4));
        Assert.assertEquals(2, vehicleList.size());
    }

    @Test
    public void ShouldFindOneVehicleByType() {
        VehicleInventoryRepository.vehicleInventoryList = TestData.buildVehicleInventory();
        VehicleInventoryRepository.vehicleInventoryList.get(0)
                .setFromDate(LocalDateTime.now());
        VehicleInventoryRepository.vehicleInventoryList.get(0)
                .setDueDate(LocalDateTime.now().plusDays(4));

        VehicleSearchService vehicleSearchService = new VehicleSearchServiceImpl();
        List<HireableVehicle> vehicleList = vehicleSearchService.search(VehicleType.HATCHBACK,
                "Bangalore", LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(3));
        Assert.assertEquals(1, vehicleList.size());
    }

    @Test
    public void ShouldFindNoVehicleByType() {

        VehicleInventoryRepository.vehicleInventoryList = TestData.buildVehicleInventory();
        VehicleInventoryRepository.vehicleInventoryList.get(0)
                .setFromDate(LocalDateTime.now().minusDays(1));
        VehicleInventoryRepository.vehicleInventoryList.get(0)
                .setDueDate(LocalDateTime.now().plusDays(4));

        VehicleInventoryRepository.vehicleInventoryList = TestData.buildVehicleInventory();
        VehicleInventoryRepository.vehicleInventoryList.get(1)
                .setFromDate(LocalDateTime.now().minusHours(5));
        VehicleInventoryRepository.vehicleInventoryList.get(1)
                .setDueDate(LocalDateTime.now().plusDays(2));

        VehicleSearchService vehicleSearchService = new VehicleSearchServiceImpl();
        List<HireableVehicle> vehicleList = vehicleSearchService.search(VehicleType.HATCHBACK,
                "Bangalore", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3));
        Assert.assertEquals(0, vehicleList.size());
    }

    @Test
    public void ShouldFindNoVehicleBySeats() {
        VehicleInventoryRepository.vehicleInventoryList = TestData.buildVehicleInventory();
        VehicleSearchService vehicleSearchService = new VehicleSearchServiceImpl();
        List<HireableVehicle> vehicleList = vehicleSearchService.search(6,
                "Bangalore", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3));
        Assert.assertEquals(0, vehicleList.size());
    }

    @Test
    public void ShouldFind1VehicleBySeats() {
        VehicleInventoryRepository.vehicleInventoryList = TestData.buildVehicleInventory();
        VehicleSearchService vehicleSearchService = new VehicleSearchServiceImpl();
        List<HireableVehicle> vehicleList = vehicleSearchService.search(5,
                "Bangalore", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3));
        Assert.assertEquals(1, vehicleList.size());
    }

    @Test
    public void ShouldFindNoVehicleByModel() {
        VehicleInventoryRepository.vehicleInventoryList = TestData.buildVehicleInventory();
        VehicleSearchService vehicleSearchService = new VehicleSearchServiceImpl();
        List<HireableVehicle> vehicleList = vehicleSearchService.search("make", "model",
                "Bangalore", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3));
        Assert.assertEquals(0, vehicleList.size());
    }

    @Test
    public void ShouldFind1VehicleByModel() {
        VehicleInventoryRepository.vehicleInventoryList = TestData.buildVehicleInventory();
        VehicleSearchService vehicleSearchService = new VehicleSearchServiceImpl();
        List<HireableVehicle> vehicleList = vehicleSearchService.search("Maruti", "Swift",
                "Bangalore", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3));
        Assert.assertEquals(1, vehicleList.size());
    }
}

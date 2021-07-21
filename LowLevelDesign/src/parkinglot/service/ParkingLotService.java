package parkinglot.service;

import parkinglot.model.account.common.Address;
import parkinglot.model.parking.spot.ParkingSpot;
import parkinglot.model.vehicle.VehicleType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.UUID;

import static parkinglot.service.ParkingFloor.getSpotTypeForVehicle;

@Getter
@Setter
public class ParkingLotService {
    private String parkingLotId;
    private Address address;

    private List<ParkingFloor> parkingFloors;
    private List<EntrancePanel> entrancePanels;
    private List<ExitPanel> exitPanels;

    private static volatile ParkingLotService INSTANCE = null;

    private ParkingLotService() {
        this.parkingLotId = UUID.randomUUID().toString();
        parkingFloors = new ArrayList<>();
        entrancePanels = new ArrayList<>();
        exitPanels = new ArrayList<>();
    }

    public static ParkingLotService getInstance(){
        if(INSTANCE == null){
            synchronized (ParkingLotService.class) {
                if(INSTANCE == null){
                    INSTANCE = new ParkingLotService();
                }
            }
        }
        return INSTANCE;
    }

    public boolean isFull() {
        int index = 0;
        BitSet bitSet = new BitSet();
        for (ParkingFloor parkingFloor : parkingFloors) {
            bitSet.set(index++, parkingFloor.isFloorFull());
        }
        return bitSet.cardinality() == bitSet.size();
    }

    public boolean canPark(VehicleType vehicleType) {
        for (ParkingFloor parkingFloor : parkingFloors) {
            if (parkingFloor.canPark(getSpotTypeForVehicle(vehicleType)))
                return true;
        }
        return false;
    }

    public ParkingSpot getParkingSpot(VehicleType vehicleType) {
        for (ParkingFloor parkingFloor : ParkingLotService.getInstance().getParkingFloors()) {
            ParkingSpot parkingSpot = parkingFloor.getSpot(vehicleType);
            if (parkingSpot != null) {
                return parkingSpot;
            }
        }
        return null;
    }

    public ParkingSpot vacateParkingSpot(String parkingSpotId) {
        for (ParkingFloor parkingFloor : ParkingLotService.getInstance().getParkingFloors()) {
            ParkingSpot parkingSpot = parkingFloor.vacateSpot(parkingSpotId);
            if (parkingSpot != null)
                return parkingSpot;
        }
        return null;
    }
}
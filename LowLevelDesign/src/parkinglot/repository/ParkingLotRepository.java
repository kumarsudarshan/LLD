package parkinglot.repository;

import parkinglot.exceptions.InvalidParkingLotException;
import parkinglot.exceptions.InvlaidParkingFloorException;
import parkinglot.model.parking.spot.ParkingSpot;
import parkinglot.service.EntrancePanel;
import parkinglot.service.ExitPanel;
import parkinglot.service.ParkingFloor;
import parkinglot.service.ParkingLotService;

import java.util.*;

public class ParkingLotRepository {
    public static Map<String, ParkingLotService> parkingLotMap = new HashMap<>();
    public static List<ParkingLotService> parkingLotServices = new ArrayList<>();

    private static volatile ParkingLotRepository INSTANCE = null;

    private ParkingLotRepository(){

    }

    public static ParkingLotRepository getInstance(){
        if(INSTANCE == null){
            synchronized (ParkingLotRepository.class) {
                if(INSTANCE == null){
                    INSTANCE = new ParkingLotRepository();
                }
            }
        }
        return INSTANCE;
    }

    public ParkingLotService addParkingLot(ParkingLotService parkingLotService) {
        parkingLotMap.putIfAbsent(parkingLotService.getParkingLotId(), parkingLotService);
        parkingLotServices.add(parkingLotService);
        return parkingLotService;
    }

    public ParkingLotService getParkingLot(String parkingLotId) {
        return parkingLotMap.get(parkingLotId);
    }

    public ParkingFloor addParkingFloor(String parkingLotId, ParkingFloor parkingFloor)
            throws InvalidParkingLotException {
        ParkingLotService parkingLotService = parkingLotMap.get(parkingLotId);
        if (parkingLotService == null)
            throw new InvalidParkingLotException("Invalid parking lot");

        //Idempotency
        Optional<ParkingFloor> floor = parkingLotService.getParkingFloors().stream()
                .filter(pFloor -> pFloor.getFloorId()
                        .equalsIgnoreCase(parkingFloor.getFloorId())).findFirst();

        if (floor.isPresent())
            return floor.get();

        parkingLotService.getParkingFloors().add(parkingFloor);
        return parkingFloor;
    }

    public ParkingSpot addParkingSpot(String parkingLotId, String parkingFloorId, ParkingSpot parkingSpot)
            throws InvalidParkingLotException, InvlaidParkingFloorException {
        ParkingLotService parkingLotService = parkingLotMap.get(parkingLotId);
        if (parkingLotService == null)
            throw new InvalidParkingLotException("Invalid parking lot");
        Optional<ParkingFloor> floor = parkingLotService.getParkingFloors().stream()
                .filter(pFloor -> pFloor.getFloorId()
                        .equalsIgnoreCase(parkingFloorId)).findFirst();
        if (!floor.isPresent()) {
            throw new InvlaidParkingFloorException("Invalid parking floor");
        }
        Optional<ParkingSpot> spot =
                floor.get().getParkingSpots().get(parkingSpot.getParkingSpotType())
                        .stream().filter(pSpot ->
                        pSpot.getParkingSpotId()
                                .equalsIgnoreCase(parkingSpot.getParkingSpotId())).findFirst();
        if (spot.isPresent())
            return spot.get();

        floor.get().getParkingSpots().get(parkingSpot.getParkingSpotType()).add(parkingSpot);
        return parkingSpot;
    }

    public EntrancePanel addEntryPanel(String parkingLotId, EntrancePanel entrancePanel)
            throws InvalidParkingLotException {
        ParkingLotService parkingLotService = parkingLotMap.get(parkingLotId);
        if (parkingLotService == null)
            throw new InvalidParkingLotException("Invalid parking lot");
        Optional<EntrancePanel> ePanel =
                parkingLotMap.get(parkingLotId)
                        .getEntrancePanels().stream().filter(ep ->
                        ep.getId().equalsIgnoreCase(entrancePanel.getId())).findFirst();
        if (ePanel.isPresent())
            return entrancePanel;
        parkingLotMap.get(parkingLotId)
                .getEntrancePanels().add(entrancePanel);
        return entrancePanel;
    }

    public ExitPanel addExitPanel(String parkingLotId, ExitPanel exitPanel)
            throws InvalidParkingLotException {
        ParkingLotService parkingLotService = parkingLotMap.get(parkingLotId);
        if (parkingLotService == null)
            throw new InvalidParkingLotException("Invalid parking lot");
        Optional<EntrancePanel> ePanel =
                parkingLotMap.get(parkingLotId)
                        .getEntrancePanels().stream().filter(ep ->
                        ep.getId().equalsIgnoreCase(exitPanel.getId())).findFirst();
        if (ePanel.isPresent())
            return exitPanel;
        parkingLotMap.get(parkingLotId)
                .getExitPanels().add(exitPanel);
        return exitPanel;
    }

}

package parkinglot.service;

import parkinglot.exceptions.InvlaidParkingFloorException;
import parkinglot.model.account.Account;
import parkinglot.model.parking.spot.ParkingSpot;
import parkinglot.repository.ParkingLotRepository;

import java.util.Optional;

public class AdminService extends Account {
    ParkingLotRepository parkingLotRepository = ParkingLotRepository.getInstance();

    public void addParkingFloor(ParkingFloor parkingFloor) {
        Optional<ParkingFloor> floor =
                ParkingLotService.getInstance().getParkingFloors().stream()
                        .filter(pF -> pF.getFloorId().equalsIgnoreCase(parkingFloor.getFloorId()))
                        .findFirst();
        if (floor.isPresent())
            return;
        ParkingLotService.getInstance().getParkingFloors().add(parkingFloor);
    }

    public void addParkingSpot(String parkingFloorId, ParkingSpot parkingSpot)
            throws InvlaidParkingFloorException {
        Optional<ParkingFloor> floor =
                ParkingLotService.getInstance().getParkingFloors().stream()
                        .filter(pF -> pF.getFloorId().equalsIgnoreCase(parkingFloorId))
                        .findFirst();
        if (!floor.isPresent())
            throw new InvlaidParkingFloorException("Invalid floor");

        Optional<ParkingSpot> spot =
                floor.get().getParkingSpots().get(parkingSpot.getParkingSpotType())
                        .stream()
                        .filter(pS -> pS.getParkingSpotId().equalsIgnoreCase(parkingSpot.getParkingSpotId()))
                        .findFirst();
        if (spot.isPresent())
            return;

        floor.get().getParkingSpots().get(parkingSpot.getParkingSpotType())
                .addLast(parkingSpot);
    }

    public void addEntrancePanel(EntrancePanel entrancePanel) {
        Optional<EntrancePanel> panel =
                ParkingLotService.getInstance().getEntrancePanels().stream()
                        .filter(eP -> eP.getId().equalsIgnoreCase(entrancePanel.getId())).findFirst();
        if (panel.isPresent())
            return;
        ParkingLotService.getInstance().getEntrancePanels().add(entrancePanel);
    }

    public void addExitPanel(ExitPanel exitPanel) {
        Optional<ExitPanel> panel =
                ParkingLotService.getInstance().getExitPanels().stream()
                        .filter(eP -> eP.getId().equalsIgnoreCase(exitPanel.getId())).findFirst();
        if (panel.isPresent())
            return;
        ParkingLotService.getInstance().getExitPanels().add(exitPanel);
    }
}

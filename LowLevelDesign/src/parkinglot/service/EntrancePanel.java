package parkinglot.service;

import parkinglot.model.parking.ParkingTicket;
import parkinglot.model.parking.TicketStatus;
import parkinglot.model.parking.spot.ParkingSpot;
import parkinglot.model.vehicle.Vehicle;
import lombok.Getter;
import parkinglot.service.ParkingLotService;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class EntrancePanel {
    private String id;

    public EntrancePanel(String id) {
        this.id = id;
    }

    public ParkingTicket getParkingTicket(Vehicle vehicle) {
        if (!ParkingLotService.getInstance().canPark(vehicle.getType()))
            return null;
        ParkingSpot parkingSpot = ParkingLotService.getInstance().getParkingSpot(vehicle.getType());
        if (parkingSpot == null)
            return null;
        return buildTicket(vehicle.getLicenseNumber(), parkingSpot.getParkingSpotId());
    }

    private ParkingTicket buildTicket(String vehicleLicenseNumber, String parkingSpotId) {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicket.setIssuedAt(LocalDateTime.now());
        parkingTicket.setAllocatedSpotId(parkingSpotId);
        parkingTicket.setLicensePlateNumber(vehicleLicenseNumber);
        parkingTicket.setTicketNumber(UUID.randomUUID().toString());
        parkingTicket.setTicketStatus(TicketStatus.ACTIVE);
        return parkingTicket;
    }

    public void printParkingTicket(ParkingTicket parkingTicket) {
        System.out.println("Ticket info: Ticket number: " + parkingTicket.getTicketNumber() + ", Licence number: "
                + parkingTicket.getLicensePlateNumber() + ", Issued at: " + parkingTicket.getIssuedAt());
    }
}

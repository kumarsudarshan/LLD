package parkinglot.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import parkinglot.model.parking.HourlyCost;
import parkinglot.model.parking.ParkingTicket;
import parkinglot.model.parking.spot.ParkingSpot;
import parkinglot.model.parking.spot.ParkingSpotType;
import parkinglot.service.ParkingLotService;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ExitPanel {
    private String id;

    public ParkingTicket scanAndVacate(ParkingTicket parkingTicket) {
        ParkingSpot parkingSpot =
                ParkingLotService.getInstance().vacateParkingSpot(parkingTicket.getAllocatedSpotId());
        parkingTicket.setCharges(calculateCost(parkingTicket, parkingSpot.getParkingSpotType()));
        return parkingTicket;
    }

    private double calculateCost(ParkingTicket parkingTicket, ParkingSpotType parkingSpotType) {
        Duration duration = Duration.between(parkingTicket.getIssuedAt(), LocalDateTime.now());
        long hours = duration.toHours();
        if (hours == 0)
            hours = 1;
        double amount = hours * new HourlyCost().getCost(parkingSpotType);
        return amount;
    }
}

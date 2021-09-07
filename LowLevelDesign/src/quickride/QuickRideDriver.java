package quickride;

import quickride.exceptions.DriverNotExistsException;
import quickride.exceptions.RideDoesNotExistsException;
import quickride.model.Ride;
import quickride.model.account.Driver;
import quickride.model.account.Rider;
import quickride.model.common.Location;
import quickride.service.QuickRideService;

import java.util.List;

public class QuickRideDriver {
    public static void main(String[] args) throws DriverNotExistsException, RideDoesNotExistsException {
        QuickRideService quickRideService = QuickRideService.getInstance();

        Driver driver1 = new Driver("Driver1", "MALE", 9900112234l, new Location(23, 46), "driver1@gmail.com", "password");
        Driver driver2 = new Driver("Driver2", "FEMALE", 9900112235l, new Location(21, 45), "driver2@gmail.com", "password");
        Driver driver3 = new Driver("Driver3", "MALE", 9900112236l, new Location(19, 43), "driver3@gmail.com", "password");
        quickRideService.createDriverAccount(driver1);
        quickRideService.createDriverAccount(driver2);
        quickRideService.createDriverAccount(driver3);

        Rider rider1 = new Rider("Rider1", "MALE", 9900112239l, new Location(21, 41), "rider1@gmail.com", "password");
        Rider rider2 = new Rider("Rider2", "FEMALE", 9900112238l, new Location(20, 41), "rider2@gmail.com", "password");
        Rider rider3 = new Rider("Rider3", "MALE", 9900112237l, new Location(21, 43), "rider3@gmail.com", "password");
        quickRideService.createRiderAccount(rider1);
        quickRideService.createRiderAccount(rider2);
        quickRideService.createRiderAccount(rider3);

        Ride ride1 = new Ride(4, new Location(20, 46), new Location(19, 41), driver1.getEmail());
        Ride ride2 = new Ride(1, new Location(18, 41), new Location(22, 45), driver2.getEmail());
        Ride ride3 = new Ride(2, new Location(21, 49), new Location(25, 47), driver3.getEmail());
        quickRideService.createRide(ride1);
        quickRideService.createRide(ride2);
        quickRideService.createRide(ride3);

        List<Ride> availableRideList = quickRideService.searchRides(new Location(20, 43), new Location(25, 49), 5);

        Ride ride = availableRideList.get(0);
        quickRideService.requestRide(rider1, ride.getId());

        quickRideService.finishRide(ride.getId(), ride.getCreatedBy(), rider1.getEmail(), 10);

        quickRideService.totalFareDetails(rider1.getEmail(), ride.getId());
        quickRideService.totalFareDetails(ride.getCreatedBy(), ride.getId());


    }
}

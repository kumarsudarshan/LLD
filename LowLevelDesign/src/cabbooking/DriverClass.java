package cabbooking;

import cabbooking.exceptions.CreateException;
import cabbooking.exceptions.DriverNotAvailableException;
import cabbooking.model.*;
import cabbooking.service.CabBookingService;

public class DriverClass {

    public static void main(String[] args) {
        CabBookingService cabService = CabBookingService.getInstance();

        Vehicle vehicle1 = new Car("Swift", "1242", new Location(1, 1), VehicleType.HATCHBACK);
        Driver driver1 = null;
        try {
            driver1 = cabService.registerDriver(1, "Driver1", 20, "male", vehicle1);
			System.out.println("Driver registered, Id: " + driver1.getId());
        } catch (CreateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Vehicle vehicle2 = new Car("Innova", "1234", new Location(2, 5), VehicleType.SUV);
		try {
			Driver driver2 = cabService.registerDriver(2,"Driver2", 20, "female", vehicle2);
			System.out.println("Driver registered, Id: " + driver2.getId());
		} catch (CreateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        Rider rider1 = null;
        Rider rider2 = null;
        try {
            rider1 = cabService.registerRider(11, "Rider1", 20, "male", new Location(0, 0));
			System.out.println("Rider registered, Id: " + rider1.getId());
        } catch (CreateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

		try {
			rider2 = cabService.registerRider(12,"Rider2", 25, "male", new Location(5,2));
			System.out.println("Rider registered, Id: " + rider2.getId());
		} catch (CreateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(cabService.bookRide(11, new Location(0,0), new Location(10,10)));

        cabService.switchAvailability(1, Boolean.TRUE);

        System.out.println(cabService.bookRide(11, new Location(0, 0), new Location(10, 10)));


    }


}

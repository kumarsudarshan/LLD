package cabbooking.model;

public enum VehicleType {
    CAR(4), SEDAN(4), HATCHBACK(4), SUV(6);

    int seats = 0;

    VehicleType(int seats) {
        this.seats = seats;
    }
}

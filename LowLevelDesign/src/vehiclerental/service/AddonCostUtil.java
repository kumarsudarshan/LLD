package vehiclerental.service;

import vehiclerental.model.reservation.AddonService;
import vehiclerental.model.reservation.VehicleAddon;
import vehiclerental.model.reservation.VehicleReservation;

public class AddonCostUtil {

    public static double computeEquipmentCost(VehicleReservation vehicleReservation) {
        double vehicleAddonCost = 0;
        if (vehicleReservation.getVehicleAddons() != null &&
                vehicleReservation.getVehicleAddons().size() > 0) {
            for (VehicleAddon vehicleAddOn : vehicleReservation.getVehicleAddons()) {
                vehicleAddonCost += vehicleAddOn.getCost();
            }
        }
        return vehicleAddonCost;
    }

    public static double computeServiceCost(VehicleReservation vehicleReservation) {
        double addonServiceCost = 0;
        if (vehicleReservation.getAddonServices() != null &&
                vehicleReservation.getAddonServices().size() > 0) {
            for (AddonService addonService : vehicleReservation.getAddonServices()) {
                addonServiceCost += addonService.getCost();
            }
        }
        return addonServiceCost;
    }
}

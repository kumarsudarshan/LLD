package vehiclerental.model.vehicle;

import lombok.Getter;
import lombok.Setter;
import vehiclerental.model.common.Address;
import vehiclerental.model.common.Coordinates;

@Getter
@Setter
public class VehicleLocation {
    private String locationId;
    private Address address;
    private Coordinates coordinates;
}

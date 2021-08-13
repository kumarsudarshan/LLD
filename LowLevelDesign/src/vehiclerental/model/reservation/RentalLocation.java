package vehiclerental.model.reservation;

import lombok.Getter;
import lombok.Setter;
import vehiclerental.model.common.Address;
import vehiclerental.model.common.Coordinates;

@Getter
@Setter
public class RentalLocation {
    private String id;
    private Address address;
    private Coordinates coordinates;
}

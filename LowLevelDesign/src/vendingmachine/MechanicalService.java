package vendingmachine;

import java.math.BigDecimal;
import java.util.List;

interface MechanicalService {
    /*interact with hardware*/
    Payment takePayment();

    void returnChanges(BigDecimal bigDecimal);

    void releaseItem(ItemLocation itemLocation);

    void displayAvailableItems(List<ItemLocation> itemLocations);
}

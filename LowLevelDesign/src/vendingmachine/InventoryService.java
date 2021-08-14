package vendingmachine;

import java.math.BigDecimal;
import java.util.List;

/*Controllers
 * To be implemented by proxy classes. All service classes are created as singleton
 * */
interface InventoryService {
    List<ItemLocation> initializeItems();

    BigDecimal getSelectedItemPrice(ItemLocation itemLocation);
}

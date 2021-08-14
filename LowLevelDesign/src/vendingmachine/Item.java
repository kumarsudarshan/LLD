package vendingmachine;

import java.math.BigDecimal;

/*Model Objects */
class Item {
    private String name;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}

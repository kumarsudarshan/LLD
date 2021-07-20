package amazonlocker.model;

import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class Package {
    private String id;
    private double packageSize;
    private String orderId;
    private List<Item> items;

    public Package(String orderId, List<Item> items) {
        this.id = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.items = items;
        pack();
    }

    private void pack() {
        packageSize = Math.random() * 10;
    }
}

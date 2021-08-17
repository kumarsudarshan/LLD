package fooddelivery.model.common;

public class OrderItem {
    private String orderItem;
    private int quantity;

    public OrderItem(String orderItem, int quantity) {
        this.orderItem = orderItem;
        this.quantity = quantity;
    }

    public String getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(String orderItem) {
        this.orderItem = orderItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

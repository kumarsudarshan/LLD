package fooddelivery.model.common;

public class Notification {

    private int notifyTo;
    private OrderStatus orderStatus;
    private String message;

    public Notification(int notifyTo, OrderStatus orderStatus, String message) {
        this.notifyTo = notifyTo;
        this.orderStatus = orderStatus;
        this.message = message;
    }

    public int getNotifyTo() {
        return notifyTo;
    }

    public void setNotifyTo(int notifyTo) {
        this.notifyTo = notifyTo;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

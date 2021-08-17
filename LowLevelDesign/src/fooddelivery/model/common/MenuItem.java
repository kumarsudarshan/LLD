package fooddelivery.model.common;

public class MenuItem {
    private String ItemName;
    private int price;
    private int quantity;

    public MenuItem(String itemName, int price, int quantity) {
        ItemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

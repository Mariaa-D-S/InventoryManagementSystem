package com.inventory.system.InventoryItems;

public class InventoryItem extends AbstractItem {
    private int itemID;
    private int quantity;

    public InventoryItem(String name, String description, double price, int itemID, int quantity) {
        super(name, description, price);
        this.itemID = itemID;
        this.quantity = quantity;
        this.setValue(price * quantity);
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

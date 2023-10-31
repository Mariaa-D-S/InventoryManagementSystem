package com.inventory.system.InventoryItems;

import com.inventory.system.Interface.Breakable;

public class ElectronicsItem extends InventoryItem implements Breakable {
    private String brand;
    private int warrantyYears;

    public ElectronicsItem(String name, String description, double price, int itemID, int quantity, String brand, int warrantyYears) {
        super(name, description, price, itemID, quantity);
        this.brand = brand;
        this.warrantyYears = warrantyYears;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyYears() {
        return warrantyYears;
    }

    public void setWarrantyYears(int warrantyYears) {
        this.warrantyYears = warrantyYears;
    }

    @Override
    public double getValue() {
        // Calculate value for electronics with warranty(by adding 20 lv for each year)
        return super.getValue() + (warrantyYears * 20);
    }

    @Override
    public boolean isBroken() {
        return false;
    }

}

package com.inventory.system.InventoryItems;

import com.inventory.system.Interface.Breakable;

public class FragileItem extends InventoryItem implements Breakable {
    private double weight;

    public FragileItem(String name, String description, double price, int itemID, int quantity, double weight) {
        super(name, description, price, itemID, quantity);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public double getValue() {
        // Calculate value based on weight
        return super.getValue() + (weight / 2);
    }

    @Override
    public boolean isBroken() {
        if (this.weight == 0) {
            return true;
        }
        return false;
    }
}

package com.inventory.system.InventoryItems;

import com.inventory.system.Interface.Categorizable;
import com.inventory.system.Interface.Item;
import com.inventory.system.Interface.Sellable;

public abstract class AbstractItem implements Item, Categorizable, Sellable {
    private String name;
    private String description;


    private double value;
    private String category;
    //private boolean breakable;
    //private boolean perishable;
    private double price;

    public AbstractItem(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.value = price;
        //this.category = "Uncategorized";
        //this.breakable = false;
        //this.perishable = false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    @Override
    public void display() {
        System.out.println("Name: " + name +
                " Description: " + description +
                " Value: " + value +
                " Category: " + category +
                //" Is com.inventory.system.ConsoleSystem.Interface.Breakable: " + breakable +
                //" Is com.inventory.system.ConsoleSystem.Interface.Perishable: " + perishable +
                " Price: " + price);
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }
}

package com.inventory.system.InventoryItems;

import com.inventory.system.Interface.Perishable;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class GroceryItem extends InventoryItem implements Perishable {
    private LocalDate expirationDate;

    public GroceryItem(String name, String description, double price, int itemID, int quantity, LocalDate expirationDate) {
        super(name, description, price, itemID, quantity);
        this.expirationDate = expirationDate.plusDays(15);
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public double getValue() {
        LocalDate now = LocalDate.now();
        long daysLeft = now.until(expirationDate, ChronoUnit.DAYS);

        // Calculate value based on expiration date
        return super.getValue() + (daysLeft * 0.5);
    }

    @Override
    public boolean inExpiration() {
        LocalDate date = LocalDate.now();
        if(date.isAfter(expirationDate)){
            System.out.println("This product has expired");
            return false;
        }
        return true;
    }
}

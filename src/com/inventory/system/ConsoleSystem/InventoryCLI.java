package com.inventory.system.ConsoleSystem;

import com.inventory.system.InventoryItems.*;
import com.inventory.system.Orders.Order;

import java.time.LocalDate;
import java.util.*;

public class InventoryCLI {
    //public List<InventoryItem> inventory = new ArrayList<>();
//com.inventory.system.ConsoleSystem.SaveAndLoadData.loadInventoryData("src/resources/items.csv");
    private final String itemsFilePath = "src/resources/item.csv";
    //Order order = new Order();

    public void printMenu() {
        System.out.println("Inventory System Menu:");
        System.out.println("1. Add Item");
        System.out.println("2. Remove Item");
        System.out.println("3. Display Items");
        System.out.println("4. Place Order");
        System.out.println("5. Exit");
    }

    public void addItem(List<InventoryItem> inventory) {
        Scanner scanner = new Scanner(System.in);
        int nextItemID = inventory.size() + 1;
        try {
            System.out.println("Add New Item to Inventory:");
            System.out.print("Enter item name: ");
            String name = scanner.nextLine();

            System.out.print("Enter item description: ");
            String description = scanner.nextLine();

            System.out.print("Enter item price: ");
            double price = scanner.nextDouble();

            System.out.print("Enter item quantity: ");
            int quantity = scanner.nextInt();

            System.out.println("Select Item Type:");
            System.out.println("1. Electronics");
            System.out.println("2. Grocery");
            System.out.println("3. Fragile");
            System.out.print("Enter the item type (1/2/3): ");
            int itemTypeChoice = scanner.nextInt();
            InventoryItem item;

            switch (itemTypeChoice) {
                case 1 -> {
                    System.out.println("Enter years of warranty: ");
                    int warranty = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter brand: ");
                    String brand = scanner.nextLine();
                    item = new ElectronicsItem(name, description, price, nextItemID, quantity, brand, warranty);
                    item.setCategory(Categories.Electronic.name());
                }
                case 2 -> {
                    System.out.print("Enter expiration date in format yyyy mm dd: ");
                    int year = scanner.nextInt();
                    int month = scanner.nextInt();
                    int day = scanner.nextInt();
                    LocalDate date = LocalDate.of(year, month, day);
                    item = new GroceryItem(name, description, price, nextItemID, quantity, date);
                    item.setCategory(Categories.Grocery.name());
                }
                case 3 -> {
                    System.out.print("Enter item weight: ");
                    double weight = scanner.nextDouble();
                    item = new FragileItem(name, description, price, nextItemID, quantity, weight);
                    item.setCategory(Categories.Fragile.name());
                }
                case 4 -> {
                    item = new InventoryItem(name, description, price, nextItemID, quantity);
                    item.setCategory("Uncategorized");
                }
                default -> {
                    System.out.println("Invalid item type choice.");
                    return;
                }
            }

            inventory.add(item);
            System.out.println("Item added to inventory.");
            SaveAndLoadData.saveInventoryData(inventory, itemsFilePath);
        } catch (InputMismatchException e) {
            System.err.println("Invalid input. Please enter valid values.");
        }
    }

    public void removeItem(List<InventoryItem> inventory) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter item ID to remove: ");
        int itemID = scanner.nextInt();
        InventoryItem itemToRemove = null;

        for (InventoryItem item : inventory) {
            if (item.getItemID() == itemID) {
                itemToRemove = item;
                break;
            }
        }

        if (itemToRemove != null) {
            inventory.remove(itemToRemove);
            SaveAndLoadData.saveInventoryData(inventory, itemsFilePath);
            System.out.println("Item removed from inventory.");
        } else {
            System.out.println("Item with ID " + itemID + " not found in inventory.");
        }
    }

    public void displayItems(List<InventoryItem> inventory) {
        System.out.println("Inventory Items:");
        for (InventoryItem item : inventory) {

            System.out.println("ID:" + item.getItemID() +
                    " ~Name:" + item.getName() +
                    " ~Description:" + item.getDescription() +
                    " ~Price:" + item.getPrice() +
                    " ~Category:" + item.getCategory() +
                    " ~Quantity:" + item.getQuantity() +
                    " ~Value:" + item.getValue());

            if (item instanceof ElectronicsItem) {
                System.out.println(" ~Brand:" + ((ElectronicsItem) item).getBrand() +
                        " ~Years of Warranty:" + ((ElectronicsItem) item).getWarrantyYears());
            } else if (item instanceof FragileItem) {
                System.out.println(" ~Weight:" + ((FragileItem) item).getWeight());
            } else if (item instanceof GroceryItem) {
                System.out.println(" ~In Expiration:" + ((GroceryItem) item).inExpiration());
            }
        }
    }

    public void placeOrder(List<InventoryItem> inventory, Order order) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 'finish' when you are done with the order.");
        while (true) {
            System.out.print("Enter item name to order: ");
            String itemName = scanner.nextLine();
            if(itemName.equals("finish")){
                break;
            }
            for (InventoryItem item : inventory) {
                if (Objects.equals(item.getName(), itemName)) {
                    System.out.print("Enter quantity to order: ");
                    int quantity = scanner.nextInt();
                    if (item.getQuantity() < quantity) {
                        System.out.println("Sorry, not enough stock.");
                    } else
                        order.addItemToOrder(item, quantity);
                    System.out.println("item is added to cart");
                    item.setQuantity(item.getQuantity() - quantity);
                    return;
                }
            }
            System.out.println("The item " + itemName + " not found in inventory.");
        }

        order.orderInfo();
        System.out.println("Total Cost: " + order.calculateOrderTotal());
        System.out.println("Enter a payment method -> card or cash");
        String payment = scanner.nextLine();
        order.processPayment(payment);
        SaveAndLoadData.saveInventoryData(inventory, itemsFilePath);
    }
}

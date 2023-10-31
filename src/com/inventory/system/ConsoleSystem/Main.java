package com.inventory.system.ConsoleSystem;

import com.inventory.system.ConsoleSystem.InventoryCLI;
import com.inventory.system.InventoryItems.InventoryItem;
import com.inventory.system.Orders.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        InventoryCLI client = new InventoryCLI();
        Order order = new Order();
        List<InventoryItem> inventory = new ArrayList<>();
        SaveAndLoadData.loadInventoryData(inventory, "src/resources/item.csv");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            client.printMenu();
            System.out.print("Enter the number of your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> client.addItem(inventory);
                case 2 -> client.removeItem(inventory);
                case 3 -> client.displayItems(inventory);
                case 4 -> client.placeOrder(inventory, order);
                case 5 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
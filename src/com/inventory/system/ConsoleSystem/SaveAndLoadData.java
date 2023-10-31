package com.inventory.system.ConsoleSystem;
import com.inventory.system.InventoryItems.*;

import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SaveAndLoadData {
    public static void saveInventoryData(List<InventoryItem> inventory, String filename) {
        DecimalFormat df = new DecimalFormat("0.00");
        DecimalFormatSymbols symbols = df.getDecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(symbols);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("ItemID,Name,Description,Price,Value,Quantity,Type,Brand,WarrantyYears,ExpirationDate,Weight\n");
            for (InventoryItem item : inventory) {
                String line;
                if (item instanceof ElectronicsItem electronicItem) {
                    line = String.format("%d,%s,%s,%s,%s,%d,Electronic,%s,%d,,\n",
                            item.getItemID(), item.getName(), item.getDescription(),df.format(item.getPrice()), df.format(item.getValue()), item.getQuantity(),
                            electronicItem.getBrand(), electronicItem.getWarrantyYears());
                } else if (item instanceof GroceryItem groceryItem) {
                    line = String.format("%d,%s,%s,%s,%s,%d,Grocery,,,%s\n",
                            item.getItemID(), item.getName(), item.getDescription(),df.format(item.getPrice()), df.format(item.getValue()), item.getQuantity(),
                            groceryItem.getExpirationDate());
                } else if (item instanceof FragileItem fragileItem) {
                    line = String.format("%d,%s,%s,%s,%s,%d,Fragile,,,,%s\n",
                            item.getItemID(), item.getName(), item.getDescription(),df.format(item.getPrice()), df.format(item.getValue()), item.getQuantity(),
                            df.format(fragileItem.getWeight()));
                } else {
                    line = String.format("%d,%s,%s,%s,%s,%d,,,,,\n",
                            item.getItemID(), item.getName(), item.getDescription(),df.format(item.getPrice()), df.format(item.getValue()), item.getQuantity());
                }
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadInventoryData(List<InventoryItem> inventory, String filename) {
        //List<InventoryItem> inventory = new ArrayList<>();
        InventoryItem item;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 9) {
                    int itemID = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String description = parts[2];
                    double price = Double.parseDouble(parts[3]);
                    double value = Double.parseDouble(parts[4]);
                    int quantity = Integer.parseInt(parts[5]);
                    String category = parts[6];

                    switch (category) {
                        case "Electronic" -> {
                            String brand = parts[7];
                            int warranty = Integer.parseInt(parts[8]);
                            item = new ElectronicsItem(name, description, price, itemID, quantity, brand, warranty);
                            item.setCategory(Categories.Electronic.name());
                        }
                        case "Grocery" -> {
                            String dateString = parts[9];
                            if (dateString.endsWith(",")) {
                                dateString = dateString.substring(0, dateString.length() - 1);
                            }
                            LocalDate date = LocalDate.parse(dateString,dateFormatter);
                            item = new GroceryItem(name, description, price, itemID, quantity, date);
                            item.setCategory(Categories.Grocery.name());
                        }
                        case "Fragile" -> {
                            double weight = Double.parseDouble(parts[10]);
                            item = new FragileItem(name, description, price, itemID, quantity, weight);
                            item.setCategory(Categories.Fragile.name());
                        }
                        default -> {item = new InventoryItem(name, description, price, itemID, quantity);
                            item.setCategory("Uncategorized");
                        }
                    }

                    inventory.add(item);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

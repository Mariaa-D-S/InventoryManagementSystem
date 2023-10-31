package com.inventory.system.Orders;

import com.inventory.system.InventoryItems.InventoryItem;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderItem> orderItems = new ArrayList<>();
    private double orderTotal;

    public void addItemToOrder(InventoryItem item, int quantity) {
        OrderItem orderItem = new OrderItem(item, quantity);
        orderItems.add(orderItem);
    }

    public double calculateOrderTotal() {
        double total = 0.0;
        for (OrderItem orderItem : orderItems) {
            total += orderItem.getItem().getPrice() * orderItem.getQuantity();
        }
        orderTotal = total;
        return total;
    }

    public void orderInfo() {
        System.out.println("Order Info:");
        for (OrderItem item : orderItems) {
            System.out.println("Item Name: " + item.getItem().getName());
            System.out.println("Ordered Quantity: " + item.getQuantity());
        }
    }

    public void processPayment(String paymentMethod) {
        Payment payment = new Payment(orderTotal, paymentMethod);
        if (paymentMethod.equals("card") || paymentMethod.equals("cash")) {
            payment.processPayment();
        }else
            System.out.println("Please enter valid payment method");
        System.out.println("Order processed successfully.");
    }
}

package com.inventory.system.Orders;

public class Payment {
    private double amount;
    private String paymentMethod;

    public Payment(double amount, String paymentMethod) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void processPayment() {
        System.out.println("Payment processed successfully.");
    }
}

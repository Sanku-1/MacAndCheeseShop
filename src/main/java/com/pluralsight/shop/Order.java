package com.pluralsight.shop;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order{
    double taxRate;
    double tipPercentage;
    double tipAmount;
    LocalDateTime timePlaced;
    Cart newOrder;
    boolean isTipPercentage;

    public Order(Cart newOrder, LocalDateTime timePlaced, double tipAmount, double taxRate) {
        this.newOrder = newOrder;
        this.timePlaced = LocalDateTime.now();
        this.tipAmount = tipAmount;
        this.taxRate = taxRate;
        isTipPercentage = false;
    }

    public Order(Cart newOrder, double tipPercentage, LocalDateTime timePlaced, double taxRate) {
        this.newOrder = newOrder;
        this.tipPercentage = tipPercentage;
        this.timePlaced = LocalDateTime.now();
        this.taxRate = taxRate;
        isTipPercentage = true;
    }

    public double totalCost() {
        double cartCostAfterTipTax = newOrder.getBaseCost();
        if (isTipPercentage) {
            cartCostAfterTipTax = (newOrder.getBaseCost() * (1 + this.tipPercentage + this.taxRate));
        } else {
            cartCostAfterTipTax = (newOrder.getBaseCost() * (1 + this.taxRate) + this.tipAmount);
        }

        return cartCostAfterTipTax;
    }

    public void createReceipt() {

    }
}

package com.pluralsight.shop;

public class Topping {
    String name;
    double baseCost;
    String productType;

    public Topping(String name, boolean isPremium, double baseCost, String productType) {
        this.name = name;
        this.baseCost = baseCost;
        this.productType = productType;
    }


    public double getValue() {
        return 1;
    }
}

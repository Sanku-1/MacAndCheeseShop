package com.pluralsight.shop;

public class Topping {
    String name;
    boolean isPremium;
    double baseCost;
    String productType;

    public Topping(String name, boolean isPremium, double baseCost, String productType) {
        this.name = name;
        this.isPremium = isPremium;
        this.baseCost = baseCost;
        this.productType = productType;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public double getValue() {
        return null;
    }
}

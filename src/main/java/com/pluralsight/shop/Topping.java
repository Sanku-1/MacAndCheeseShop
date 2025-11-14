package com.pluralsight.shop;

public abstract class Topping {
    String name;
    double baseCost;
    String productType;

    public Topping(String name, double baseCost, String productType) {
        this.name = name;
        this.baseCost = baseCost;
        this.productType = productType;
    }


    public double getValue() {
        return 0;
    }
}

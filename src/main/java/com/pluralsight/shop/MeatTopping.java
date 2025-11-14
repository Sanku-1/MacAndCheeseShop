package com.pluralsight.shop;

public class MeatTopping extends Topping {
    public MeatTopping(String name, double baseCost, String productType) {
        super(name, baseCost, productType);
    }

    public double getValue() {
        return 1;
    }
}

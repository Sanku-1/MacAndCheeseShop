package com.pluralsight.shop;

public class PremiumTopping extends Topping {


    public PremiumTopping(String name, double baseCost, String productType) {
        super(name, baseCost, productType);
    }

    public double getValue() {
        return 1;
    }
}

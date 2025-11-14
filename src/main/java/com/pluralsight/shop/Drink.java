package com.pluralsight.shop;

public class Drink extends Product{
    String name;

    public Drink(String productType, String size, String name) {
        super(productType, size);
        this.name = name;
    }

    @Override
    public double getCost() {
        if (size.equalsIgnoreCase("small")) {
            return 2;
        } else if (size.equalsIgnoreCase("medium")) {
            return 2.5;
        } else {
            return 3;
        }
    }
}

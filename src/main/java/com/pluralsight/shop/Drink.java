package com.pluralsight.shop;

public class Drink extends Product{
    String name;

    public Drink(String productType, String size, int productId, String name) {
        super(productType, size, productId);
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

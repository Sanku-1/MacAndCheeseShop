package com.pluralsight.shop;

public class Drink extends Product{
    String name;

    public Drink(String productType, String size, int productId, String name) {
        super(productType, size, productId);
        this.name = name;
    }

    @Override
    public double getCost() {
        return 0;
    }
}

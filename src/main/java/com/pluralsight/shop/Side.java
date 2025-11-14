package com.pluralsight.shop;

public class Side extends Product{
    String name;

    public Side(String productType, String size, String name) {
        super(productType, size);
        this.name = name;
    }

    @Override
    public double getCost() {
        return 1.5;
    }
}

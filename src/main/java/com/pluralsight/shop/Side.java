package com.pluralsight.shop;

public class Side extends Product{
    String name;

    public Side(String productType, String size, int productId, String name) {
        super(productType, size, productId);
        this.name = name;
    }

    @Override
    public double getCost() {
        return 1.5;
    }
}

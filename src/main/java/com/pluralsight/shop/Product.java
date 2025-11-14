package com.pluralsight.shop;

public abstract class Product {
    String productType;
    String size;

    public Product(String productType, String size) {
        this.productType = productType;
        this.size = size;
    }

    public abstract double getCost();







}

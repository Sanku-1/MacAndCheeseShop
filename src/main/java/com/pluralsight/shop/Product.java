package com.pluralsight.shop;

public abstract class Product {
    String productType;
    String size;
    int productId;

    public Product(String productType, String size, int productId) {
        this.productType = productType;
        this.size = size;
        this.productId = productId;
    }

    public abstract double getCost();







}

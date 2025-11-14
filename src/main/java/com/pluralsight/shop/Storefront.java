package com.pluralsight.shop;

import java.util.ArrayList;

public class Storefront {
    String name;
    String address;
    String phone;
    ArrayList<Product> storeOfferings;
    ArrayList<Person> customerList;

    public Storefront(String name, String address, String phone, ArrayList<Product> storeOfferings, ArrayList<Person> customerList) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.storeOfferings = new ArrayList<>();
        this.customerList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addSignatureProduct (Product product) {
        storeOfferings.add(product);
    }
}


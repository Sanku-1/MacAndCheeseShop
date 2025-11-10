package com.pluralsight.shop;

import java.util.ArrayList;

public class Storefront {
    String name;
    String address;
    String phone;
    ArrayList<Product> storeOfferings;
    ArrayList<Topping> toppingOfferings;

    public Storefront(String name, String address, String phone, ArrayList<Product> storeOfferings, ArrayList<Topping> toppingOfferings) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.storeOfferings = new ArrayList<>();
        this.toppingOfferings = new ArrayList<>();
    }
}

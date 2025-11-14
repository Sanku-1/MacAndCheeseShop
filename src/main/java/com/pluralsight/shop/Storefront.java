package com.pluralsight.shop;

import java.util.ArrayList;

public class Storefront {
    String name;
    String address;
    String phone;
    ArrayList<Product> signatureOfferings;
    ArrayList<Person> customerList;
    ArrayList<Topping> toppingOfferings;

    public Storefront(String name, String address, String phone, ArrayList<Topping> toppingOfferings, ArrayList<Person> customerList) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.signatureOfferings = new ArrayList<>();
        this.customerList = customerList;
        this.toppingOfferings = toppingOfferings;
    }

    public String getName() {
        return name;
    }

    public void addSignatureProduct (Product product) {
        signatureOfferings.add(product);
    }
}


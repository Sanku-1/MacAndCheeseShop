package com.pluralsight.shop;
import java.io.*;
import java.util.ArrayList;

public class StorefrontManager {
    public Storefront getStorefront() throws IOException {
        FileReader fileReader = new FileReader("storefront.csv");
        BufferedReader bufReader = new BufferedReader(fileReader);
        String firstLine = bufReader.readLine();
        String[] storefrontTokens = firstLine.split("\\|");
        String name = storefrontTokens[0];
        String address = storefrontTokens[1];
        String phone = storefrontTokens[2];
        String line;
        ArrayList<Product> productOfferings = new ArrayList<>();
        ArrayList<Topping> toppingOfferings = new ArrayList<>();
        return new Storefront(name, address, phone, productOfferings, toppingOfferings);
    }
}
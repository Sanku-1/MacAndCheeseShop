package com.pluralsight.shop;

import java.util.ArrayList;

public class CustomerCart extends Cart{
    public CustomerCart(ArrayList<Product> cartList, Person customer) {
        super(cartList, customer);
    }
}

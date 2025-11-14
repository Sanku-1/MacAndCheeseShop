package com.pluralsight.shop;
import java.util.ArrayList;
import java.util.List;

public abstract class Cart {
    ArrayList<Product> cartList;
    Person customer;

    public Cart(ArrayList<Product> cartList, Person customer) {
        this.cartList = new ArrayList<>();
        this.customer = customer;
    }

    public double getBaseCost() {
        List<Double> productCost = cartList.stream().map(Product::getCost).toList();

        return productCost.stream().reduce(
                //Set Initial Value
                0.0,
                (temp, productValue) -> temp += productValue);
    }

    public void addToCart(Product product) {
        cartList.add(product);
    }

    public double getRewardPoints() {
        return getBaseCost()*4.0;
    }

    public void checkOut() {
        customer.addRewardPoints(getRewardPoints());
        cartList.clear();
    }
}

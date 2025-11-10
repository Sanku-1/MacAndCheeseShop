package com.pluralsight.shop;
import java.util.ArrayList;

public class MacAndCheese extends Product{
    String flavor; //(Buffalo, BBQ, Plain, Four Cheese)
    ArrayList<Topping> toppingsList;
    boolean hasExtraCheese; //(work on it)

    public MacAndCheese(String productType, String size, int productId, String flavor) {
        super(productType, size, productId);
        toppingsList = new ArrayList<>();
        this.flavor = flavor;
    }

    @Override
    public double getCost() {
        return 0;
    }

    public void addTopping(Topping topping) {
        toppingsList.add(topping);
    }

    public void removeTopping(Topping topping) {
        if (toppingsList.contains(topping)) {
            toppingsList.remove(topping);
        } else {
            System.out.println("This item does not have that topping on it");
        }
    }


}

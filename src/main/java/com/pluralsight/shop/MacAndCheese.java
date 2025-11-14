package com.pluralsight.shop;
import java.util.ArrayList;
import java.util.List;

public class MacAndCheese extends Product {
    String flavor;
    ArrayList<Topping> toppingsList;
    boolean isExtraCheese;//(work on it)
    boolean isExtraMeat;
    boolean isBreadBowl;
    String side;

    public MacAndCheese(String productType, String size, String flavor, boolean isExtraCheese, boolean isExtraMeat, boolean isBreadBowl, String side) {
        super(productType, size);
        toppingsList = new ArrayList<>();
        this.flavor = flavor;
        this.isExtraCheese = isExtraCheese;
        this.isExtraMeat = isExtraMeat;
        this.isBreadBowl = isBreadBowl;
        this.side = side;
    }

    @Override
    public double getCost() {
        double baseCost = 0;
        double extrasCost = 0;
        if (size.equalsIgnoreCase("small")) {
            baseCost = 7;
            if (isExtraCheese) {
                extrasCost += 0.3;
            }
            if (isExtraMeat) {
                extrasCost += 0.5;
            }
        } else if (size.equalsIgnoreCase("medium")) {
            baseCost = 10;
            if (isExtraCheese) {
                extrasCost += 0.6;
            }
            if (isExtraMeat) {
                extrasCost += 1;
            }
        } else {
            baseCost = 15;
            if (isExtraCheese) {
                extrasCost += 0.9;
            }
            if (isExtraMeat) {
                extrasCost += 1.5;
            }
        }
        return baseCost + extrasCost + getToppingCost();
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

    public double getToppingCost() {
        if (size.equalsIgnoreCase("small")) {
            List<Double> toppingCost = toppingsList.stream().map(Topping -> {
                        if (Topping instanceof MeatTopping) {
                            return Topping.getValue();
                        } else if (Topping instanceof PremiumTopping) {
                            return Topping.getValue();
                        } else {
                            //Standard or Condiment Toppings
                            return Topping.getValue();
                        }
                    }
            ).toList();

            return toppingCost.stream().reduce(
                    //Set Initial Value
                    0.0,
                    (temp, toppingValue) -> temp += toppingValue);
        } else if (size.equalsIgnoreCase("medium")) {
            List<Double> toppingCost = toppingsList.stream().map(Topping -> {
                        if (Topping instanceof MeatTopping) {
                            return Topping.getValue() * 2;
                        } else if (Topping instanceof PremiumTopping) {
                            return Topping.getValue() * 2;
                        } else {
                            //Standard or Condiment Toppings
                            return Topping.getValue();
                        }
                    }
            ).toList();

            return toppingCost.stream().reduce(
                    //Set Initial Value
                    0.0,
                    (temp, toppingValue) -> temp += toppingValue);
        }
        else {
            //Large Size
            List<Double> toppingCost = toppingsList.stream().map(Topping -> {
                        if (Topping instanceof MeatTopping) {
                            return Topping.getValue() * 3;
                        } else if (Topping instanceof PremiumTopping) {
                            return Topping.getValue() * 3;
                        } else {
                            //Standard or Condiment Toppings
                            return Topping.getValue();
                        }
                    }
            ).toList();

            return toppingCost.stream().reduce(
                    //Set Initial Value
                    0.0,
                    (temp, toppingValue) -> temp += toppingValue);
        }
    }
}

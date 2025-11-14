package com.pluralsight.shop;
import java.io.*;
import java.util.ArrayList;

public class StorefrontManager {

    public static Storefront getStorefront() throws IOException {
        FileReader fileReader = new FileReader("storefront.csv");
        BufferedReader bufReader = new BufferedReader(fileReader);
        String firstLine = bufReader.readLine();
        String[] storefrontTokens = firstLine.split("\\|");
        String name = storefrontTokens[0];
        String address = storefrontTokens[1];
        String phone = storefrontTokens[2];
        bufReader.close();

        ArrayList<Topping> toppingOfferings = new ArrayList<>();
        toppingOfferings.addAll(loadMeatToppings());
        toppingOfferings.addAll(loadPremiumToppings());
        toppingOfferings.addAll(loadStandardToppings());
        toppingOfferings.addAll(loadCondimentToppings());

        ArrayList<Person> customerList = loadCustomers();

        return new Storefront(name, address, phone, toppingOfferings, customerList);
    }

    public static ArrayList<Topping> loadMeatToppings() throws IOException {
        ArrayList<Topping> meatToppings = new ArrayList<>();
        FileReader fileReader = new FileReader("meattoppings.csv");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            meatToppings.add(new MeatTopping(line, 1.0, "Mac and Cheese"));
        }
        bufferedReader.close();
        return meatToppings;
    }

    public static ArrayList<Topping> loadPremiumToppings() throws IOException {
        ArrayList<Topping> premiumToppings = new ArrayList<>();
        FileReader fileReader = new FileReader("premiumtoppings.csv");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            premiumToppings.add(new PremiumTopping(line, 0.75, "Mac and Cheese"));
        }
        bufferedReader.close();
        return premiumToppings;
    }

    public static ArrayList<Topping> loadStandardToppings() throws IOException {
        ArrayList<Topping> standardToppings = new ArrayList<>();
        FileReader fileReader = new FileReader("standardtoppings.csv");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            standardToppings.add(new StandardTopping(line, 0.0, "Mac and Cheese"));
        }
        bufferedReader.close();
        return standardToppings;
    }

    public static ArrayList<Topping> loadCondimentToppings() throws IOException {
        ArrayList<Topping> condimentToppings = new ArrayList<>();
        FileReader fileReader = new FileReader("condimenttoppings.csv");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            condimentToppings.add(new CondimentTopping(line, 0.0, "Mac and Cheese"));
        }
        bufferedReader.close();
        return condimentToppings;
    }

    public static ArrayList<Person> loadCustomers() throws IOException {
        ArrayList<Person> customers = new ArrayList<>();
        FileReader fileReader = new FileReader("users.csv");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            String[] tokens = line.split("\\|");
            String name = tokens[0];
            String email = tokens[1];

            if (tokens.length >= 3 && !tokens[2].isEmpty()) {
                double phone = Double.parseDouble(tokens[2]);
                Person person = new Person(name, email, phone);

                if (tokens.length >= 4 && !tokens[3].isEmpty()) {
                    double rewardPoints = Double.parseDouble(tokens[3]);
                    person.rewardPoints = rewardPoints;
                }

                if (tokens.length >= 5 && !tokens[4].isEmpty()) {
                    person.setPassword(tokens[4]);
                }

                customers.add(person);
            } else {
                Person person = new Person(name, email);
                customers.add(person);
            }
        }
        bufferedReader.close();
        return customers;
    }

    public static ArrayList<Side> loadSides() throws IOException {
        ArrayList<Side> sides = new ArrayList<>();
        FileReader fileReader = new FileReader("sides.csv");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sides.add(new Side("Side", "Regular", line));
        }
        bufferedReader.close();
        return sides;
    }

}
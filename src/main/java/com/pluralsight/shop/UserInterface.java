package com.pluralsight.shop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    StorefrontManager storefrontManager = new StorefrontManager();
    private Storefront storefront;
    Scanner scanner = new Scanner(System.in);
    Person currentUser;

    public void display() throws IOException {
        init();

        boolean isDone = false;
        while (!isDone) {
            displayMenu();
            System.out.print("Enter command: ");
            String command = scanner.nextLine().toUpperCase();

            switch (command) {
                case "A":
                    System.out.println("Placeholder");
                case "X":
                    System.out.println("Thank you for using this application");
                    isDone = true;
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    public void init() throws IOException {
        storefront = StorefrontManager.getStorefront();
        System.out.println(storefront.getName() + " Application");
        System.out.println();
    }

    public void displayMenu() throws IOException {
        System.out.println("Welcome to Smac' N Cheese!");
    }

    public void login(ArrayList<Person> userList) {
        System.out.println("Please enter your email");
        String emailInput = scanner.nextLine();
        System.out.println("Please enter your password");
        String passwordInput = scanner.nextLine();

        for (Person person : userList) {
            int matchCounter = 0;

            //Iterates through list to find User with matching email and password
            if (emailInput.equalsIgnoreCase(person.getEmail()) && passwordInput.equals(person.getPassword())) {
                currentUser = person;
                matchCounter++;
            } else if (emailInput.equalsIgnoreCase(person.getEmail())) {
                System.out.println("Incorrect password entered, please try again");
                matchCounter++;
            }
            if (matchCounter < 1) {
                System.out.println("No user with email " + emailInput + " found");
            }
        }
    }

    public void logout(ArrayList<Person> userList){
        currentUser = new Person("Guest", "");
    }

}

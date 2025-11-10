package com.pluralsight.shop;

public class Person {
    String name;
    double rewardPoints;
    double phoneNumber;
    String email;
    String password;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Person(String name, String email, double phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void addRewardPoints(double newPoints) {
        this.rewardPoints += newPoints;
        System.out.println("You now have " + rewardPoints + " reward points!");
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }


}

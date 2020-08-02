package com.example.mealrecipes;

import java.io.Serializable;

public class Food implements Serializable {
    private int foodImage;
    private String foodName;
    private String foodDescription;
    private String temperature;
    private String cost;
    private int coffeeIcon;
    private int coffeeIngredients;
    private int coffeeTechnology;

    public Food(int foodImage, String foodName, String foodDescription, String temperature, String cost, int coffeeIngredients, int coffeeTechnology) {
        this.foodImage = foodImage;
        this.foodName = foodName;
        this.foodDescription = foodDescription;
        this.temperature = temperature;
        this.cost = cost;
        this.coffeeIngredients = coffeeIngredients;
        this.coffeeTechnology = coffeeTechnology;
    }

    public Food(){}


    public int getCoffeeIngredients() {
        return coffeeIngredients;
    }

    public int getCoffeeTechnology() {
        return coffeeTechnology;
    }

    public int getFoodImage() {
        return foodImage;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getCost() {
        return cost;
    }

    public int getCoffeeIcon() {
        return coffeeIcon;
    }
}

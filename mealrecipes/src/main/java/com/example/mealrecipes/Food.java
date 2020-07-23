package com.example.mealrecipes;

public class Food {
    private int foodImage;
    private String foodName;
    private String foodDescription;
    private String temperature;
    private int coffeeIcon;
    private String cost;

    public Food(int foodImage, String foodName, String foodDescription, String temperature, int coffeeIcon, String cost) {
        this.foodImage = foodImage;
        this.foodName = foodName;
        this.foodDescription = foodDescription;
        this.temperature = temperature;
        this.coffeeIcon = coffeeIcon;
        this.cost = cost;
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

    public int getCoffeeIcon() {
        return coffeeIcon;
    }

    public String getCost() {
        return cost;
    }
}

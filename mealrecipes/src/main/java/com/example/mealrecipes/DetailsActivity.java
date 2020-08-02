package com.example.mealrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

public class DetailsActivity extends AppCompatActivity {

    Food coffee = new Food();
    ImageView coffeeImage, coffeeIcon;
    TextView coffeeName, description, temperature, ingredients, technology, cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        VideoView coffeeVideo = findViewById(R.id.coffeeVideo);
        coffeeVideo.setVideoPath("android.resource://" + getPackageName()+"/"+R.raw.coffee);
        coffeeVideo.start();

        Intent intent = getIntent();
        coffee = (Food) intent.getSerializableExtra("CoffeeDetails");

        coffeeImage = findViewById(R.id.coffeeImage);
        coffeeIcon = findViewById(R.id.coffeeIcon);
        coffeeName = findViewById(R.id.foodName);
        description = findViewById(R.id.description);
        temperature = findViewById(R.id.temperature);
        ingredients = findViewById(R.id.ingredients);
        technology = findViewById(R.id.technology);
        cost = findViewById(R.id.cost);

        Log.i("CoffeeImage", String.valueOf(coffee.getFoodImage()) );
        if (coffee!=null) {
            coffeeImage.setImageResource(coffee.getFoodImage());
            coffeeName.setText(coffee.getFoodName());
            description.setText(coffee.getFoodDescription());
            temperature.setText(coffee.getTemperature());
            ingredients.setText(getResources().getString(coffee.getCoffeeIngredients()));
            technology.setText(getResources().getString(coffee.getCoffeeTechnology()));
            cost.setText(coffee.getCost());
            if (temperature.getText().toString().equals("Hot")){
                coffeeIcon.setImageResource(R.drawable.coffee_hot);
            }
            else {
                coffeeIcon.setImageResource(R.drawable.coffee_cold);
            }
        }
    }
}
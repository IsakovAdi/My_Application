package com.example.mealrecipes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Food> foodList;
    List<Food> favoriteFoodList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foodList = new ArrayList<>();
        foodList.add(new Food(R.drawable.americano, "Американо", "Черный кофе на основе эспрессо с добавлением горячей воды.", "Hot", "180 сом", R.string.americano_ingred, R.string.americano_tech));
        foodList.add(new Food(R.drawable.frapuchino, "Карамельный Фраппуччино", "Кофе Старбакс, молоко и карамель, взбитые в блендере со льдом.", "Cold", "170 сом", R.string.frappuchino_ingred, R.string.frappuchino_tech));
        foodList.add(new Food(R.drawable.mocca_frapuchino, "Мокка Фраппуччино", "Кофе Старбакс, молоко, сироп из темного шоколада, взбитые в блендере со льдом.", "Cold", "170 сом", R.string.mokka_ingred, R.string.mokka_tech));
        foodList.add(new Food(R.drawable.mocha, "Мокка", "Эспрессо, соус из темного шоколада, пропаренное молоко.", "Hot", "180 сом", R.string.mocha_ingred, R.string.mocha_tech ));
        foodList.add(new Food(R.drawable.con_pana, "Эспрессо Кон Пана", "Эспрессо, украшенный взбитыми сливками.", "Hot", "175 сом", R.string.con_panna_ingred, R.string.con_panna_tech));
        foodList.add(new Food(R.drawable.cappuccino, "Капучино", "Это классика в кофейном мире. Эспрессо, пропаренное молоко, большое количество шелковистой воздушной молочной пены.", "Hot", "175 сом", R.string.cappuchino_ingred, R.string.cappuchino_tech));
        foodList.add(new Food(R.drawable.flat_white, "Флэт Уайт", "Напиток на основе эспрессо и молока с более насыщенным кофейным вкусом, чем в Латте.", "Hot", "195 сом",R.string.flat_white_ingred, R.string.flat_white_tech));
        foodList.add(new Food(R.drawable.espresso_frapuchino, "Эспрессо Фраппуччино", "Фирменный кофейный напиток Старбакс с добавлением молока, молотого льда и шота эспрессо, придающего напитку более насыщенный кофейный вкус.", "Cold", "185 сом", R.string.espresso_frappuchino_ingred, R.string.espresso_frappuchino_tech));
        foodList.add(new Food(R.drawable.hot_chocolate, "Горячий Шоколад", "Фирменный шоколадный напиток. Украшается взбитыми сливками и шоколадной пудрой", "Hot", "165 сом", R.string.chocolate_ingred, R.string.chocolate_tech));
        foodList.add(new Food(R.drawable.coffee_press,"Кофе-Пресс", "Кофе для компании, заваренный одним из лучших способов, раскрывающих вкус кофе.", "Hot", "155 сом", R.string.coffee_press_ingred, R.string.coffee_press_tech));


        final FoodAdapter adapter = new FoodAdapter(MainActivity.this, foodList);
        RecyclerView recyclerView = findViewById(R.id.mealView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(new FoodAdapter.RecyclerOnClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("CoffeeDetails", (Serializable) foodList.get(position));
                startActivity(intent);
            }

            @Override
            public void onFavoriteClick(int position) {
                favoriteFoodList.add(foodList.get(position));
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.meal_recipes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
//        if (favoriteFoodList.isEmpty()){
            intent.putExtra("favorites", (Serializable) favoriteFoodList);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}

package com.example.mealrecipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Food> foodList = new ArrayList<>();
        foodList.add(new Food(R.drawable.americano, "Американо", "Черный кофе на основе эспрессо с добавлением горячей воды.", "Hot", R.drawable.coffee_hot, "180 сом"));
        foodList.add(new Food(R.drawable.frapuchino, "Карамельный Фраппуччино", "Кофе Старбакс, молоко и карамель, взбитые в блендере со льдом.", "Cold", R.drawable.coffee_cold, "170 сом"));
        foodList.add(new Food(R.drawable.mocca_frapuchino, "Мокка Фраппуччино", "Кофе Старбакс, молоко, сироп из темного шоколада, взбитые в блендере со льдом.", "Cold", R.drawable.coffee_cold, "170 сом"));
        foodList.add(new Food(R.drawable.mocha, "Мокка", "Эспрессо, соус из темного шоколада, пропаренное молоко.", "Hot",R.drawable.coffee_hot, "180 сом" ));
        foodList.add(new Food(R.drawable.con_pana, "Эспрессо Кон Пана", "Эспрессо, украшенный взбитыми сливками.", "Hot", R.drawable.coffee_hot, "175 сом"));
        foodList.add(new Food(R.drawable.cappuccino, "Капучино", "Это классика в кофейном мире. Эспрессо, пропаренное молоко, большое количество шелковистой воздушной молочной пены.", "Hot", R.drawable.coffee_hot, "175 сом"));
        foodList.add(new Food(R.drawable.flat_white, "Флэт Уайт", "Напиток на основе эспрессо и молока с более насыщенным кофейным вкусом, чем в Латте.", "Hot", R.drawable.coffee_hot, "195 сом"));
        foodList.add(new Food(R.drawable.espresso_frapuchino, "Эспрессо Фраппуччино", "Фирменный кофейный напиток Старбакс с добавлением молока, молотого льда и шота эспрессо, придающего напитку более насыщенный кофейный вкус.", "Cold", R.drawable.coffee_cold, "185 сом"));
        foodList.add(new Food(R.drawable.hot_chocolate, "Горячий Шоколад", "Фирменный шоколадный напиток. Украшается взбитыми сливками и шоколадной пудрой", "Hot", R.drawable.coffee_hot, "165 сом"));
        foodList.add(new Food(R.drawable.coffee_press,"Кофе-Пресс", "Кофе для компании, заваренный одним из лучших способов, раскрывающих вкус кофе.", "Hot", R.drawable.coffee_hot, "155 сом"));

        FoodAdapter adapter = new FoodAdapter(MainActivity.this, foodList);
        RecyclerView recyclerView = findViewById(R.id.mealView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


}

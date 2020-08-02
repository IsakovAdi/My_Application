package com.example.mealrecipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    List<Food> favoriteFoodList = new ArrayList<>();
    RecyclerView recyclerView;
    FavoriteFoodAdapter favoriteFoodAdapter;
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        layout = findViewById(R.id.empty);


            Intent intent = getIntent();
            favoriteFoodList = (List<Food>) intent.getSerializableExtra("favorites");
            favoriteFoodAdapter = new FavoriteFoodAdapter(FavoriteActivity.this, favoriteFoodList);
            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(favoriteFoodAdapter);

            if (favoriteFoodList.isEmpty()){
            layout.setVisibility(View.VISIBLE);
            }
            else {
                layout.setVisibility(View.INVISIBLE);
            }

        favoriteFoodAdapter.setOnItemClickListener(new FavoriteFoodAdapter.OnClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(FavoriteActivity.this, DetailsActivity.class);
                intent.putExtra("CoffeeDetails", (Serializable) favoriteFoodList.get(position));
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(int position) {
                remove(position);
            }
        });
    }

    private void remove(int position){
        favoriteFoodList.remove(position);
        favoriteFoodAdapter.notifyItemRemoved(position);
    }
}
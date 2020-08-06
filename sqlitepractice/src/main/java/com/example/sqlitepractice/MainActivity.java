package com.example.sqlitepractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sqlitepractice.data.DatabaseHandler;
import com.example.sqlitepractice.models.Car;
import com.example.sqlitepractice.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText carName, carPrice;
    Button save, insert;
    SqlAdapter adapter;
    DatabaseHandler handler;


    List<Car> cars;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new DatabaseHandler(this);

        carName =findViewById(R.id.carName);
        carPrice = findViewById(R.id.carPrice);
        save = findViewById(R.id.save);
        insert = findViewById(R.id.insert);
        loadData();

        buildRecyclerView();

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleInsertButton();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDate();
            }
        });
    }

    public void loadData(){
        handler = new DatabaseHandler(this);
        cars = handler.getAllCars();

    }

    private void saveDate() {
        SQLiteDatabase db = handler.getWritableDatabase();

        for (Car c: cars) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Util.KEY_NAME, c.getName());
            contentValues.put(Util.KEY_PRICE, c.getPrice());
            db.insert(Util.TABLE_NAME, null, contentValues);
        }
        db.close();
    }

    public void buildRecyclerView(){
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SqlAdapter(cars);
        recyclerView.setAdapter(adapter);
    }

    private void handleInsertButton(){
        String nameText = carName.getText().toString();
        String priceText = carPrice.getText().toString();
        cars.add(new Car(nameText, priceText));
        adapter.notifyItemInserted(cars.size());
    }
}

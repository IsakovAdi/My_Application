package com.example.sqlitecars;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlitecars.data.DatabaseHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText carName, carPrice;
    FloatingActionButton floatingActionButton;
    CarAdapter adapter;
    DatabaseHandler handler;
    MediaPlayer player;

    List<Car> cars;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new DatabaseHandler(this);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        loadData();
        buildRecyclerView();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click();
                insertItemWithDialog();
            }
        });

        adapter.setOnItemClickListener(new CarAdapter.RecyclerOnClickListener() {
            @Override
            public void onClick(final int position) {
                click();
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Edit car");

                View layout = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_add, null);
                TextInputLayout til = layout.findViewById(R.id.textInputLayoutCarName);
                carName = til.findViewById(R.id.eCarName);
                til.setHint("Car name");
                TextInputLayout til1 = layout.findViewById(R.id.textInputLayoutCarPrice);
                carPrice = til1.findViewById(R.id.eCarPrice);
                til1.setHint("Car price");
                carName.setText(cars.get(position).getName());
                carPrice.setText(cars.get(position).getPrice());

                dialog.setView(layout);

                dialog.setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        click();
                        if (TextUtils.isEmpty(carName.getText().toString())) {
                            Toast.makeText(MainActivity.this, "Please enter the car name", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (TextUtils.isEmpty(carPrice.getText().toString())) {
                            Toast.makeText(MainActivity.this, "Please enter the car price", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        String nameText = carName.getText().toString();
                        String priceText = carPrice.getText().toString();

                        cars.get(position).setName(nameText);
                        cars.get(position).setPrice(priceText);
                        handler.updateCar(cars.get(position));
                        adapter.notifyDataSetChanged();
                    }
                });

                dialog.setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        click();
                        Car car = cars.get(position);
                        cars.remove(cars.get(position));
                        handler.deleteCar(car);
                        adapter.notifyItemRemoved(position);
                    }
                });

                dialog.show();

            }
        });

    }

    public void loadData() {
        handler = new DatabaseHandler(this);
        cars = handler.getAllCars();
    }

    public void buildRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CarAdapter(cars);
        recyclerView.setAdapter(adapter);
    }

    private void saveDate() {
        SQLiteDatabase db = handler.getWritableDatabase();

        for (Car c : cars) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Util.KEY_NAME, c.getName());
            contentValues.put(Util.KEY_PRICE, c.getPrice());
            db.insert(Util.TABLE_NAME, null, contentValues);
        }
        db.close();
    }

    public void insertItemWithDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Add new car");
        dialog.setMessage("Enter the cer details");

        View layout = LayoutInflater.from(this).inflate(R.layout.layout_add, null);
        TextInputLayout til = layout.findViewById(R.id.textInputLayoutCarName);
        carName = til.findViewById(R.id.eCarName);
        til.setHint("Car name");
        TextInputLayout til1 = layout.findViewById(R.id.textInputLayoutCarPrice);
        carPrice = til1.findViewById(R.id.eCarPrice);
        til1.setHint("Car price");

        dialog.setView(layout);

        dialog.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                click();
                if (TextUtils.isEmpty(carName.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Please enter the car name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(carPrice.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Please enter the car price", Toast.LENGTH_SHORT).show();
                    return;
                }

                String nameText = carName.getText().toString();
                String priceText = carPrice.getText().toString();

                cars.add(new Car(nameText, priceText));
                adapter.notifyItemInserted(cars.size());
                saveDate();
            }
        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                click();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void click(){
         player = MediaPlayer.create(this, R.raw.four);
         player.start();
    }
}

package com.example.sqlitecars;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;


import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // SQL Room Library
    // @Database - точка для получения доступа к базе данных
    // @Entity - Таблица которая должна содержать классы
    // @Dao - Методы для управления данными таблицы

    RecyclerView recyclerView;
    EditText carName, carPrice;
    FloatingActionButton floatingActionButton;
    CarAdapter adapter;
    MediaPlayer player;

    List<Car> carArrayList = new ArrayList<>();
    CarsDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        floatingActionButton = findViewById(R.id.floatingActionButton);
        database = Room.databaseBuilder(getApplicationContext(), CarsDatabase.class, "CarDB").build();

        new GetAllCarsAsyncTask().execute(); // если в методе передаются какие то данные, мы их передаем в параметрах, а если просто void просто пустыи оставляем и закрываем

        buildRecyclerView();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickVoice();
                insertItemWithDialog();
            }
        });

        adapter.setOnItemClickListener(new CarAdapter.RecyclerOnClickListener() {
            @Override
            public void onClick(final int position) {
                clickVoice();
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Edit car");

                View layout = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_add, null);
                TextInputLayout til = layout.findViewById(R.id.textInputLayoutCarName);
                carName = til.findViewById(R.id.eCarName);
                til.setHint("Car name");
                TextInputLayout til1 = layout.findViewById(R.id.textInputLayoutCarPrice);
                carPrice = til1.findViewById(R.id.eCarPrice);
                til1.setHint("Car price");
                carName.setText(carArrayList.get(position).getName());
                carPrice.setText(carArrayList.get(position).getPrice());

                dialog.setView(layout);

                dialog.setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        clickVoice();
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

                        Car car = carArrayList.get(position);///

                        carArrayList.get(position).setName(nameText);
                        carArrayList.get(position).setPrice(priceText);
                        new UpdateCarAsyncTask().execute(car);

                    }
                });

                dialog.setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        clickVoice();
                        Car car = carArrayList.get(position);
                        carArrayList.remove(car);
                        new DeleteCarAsyncTask().execute(car);

                    }
                });
                dialog.show();
            }
        });

    }

//    public void loadData(DatabaseHandler handler) {
////        handler = new DatabaseHandler(this);
//        cars = handler.getAllCars();
//    }

    public void buildRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CarAdapter(carArrayList);
        recyclerView.setAdapter(adapter);
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
                clickVoice();
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

                new CreateCarAsyncTask().execute(new Car(0, nameText, priceText));

            }
        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clickVoice();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void clickVoice() {
        player = MediaPlayer.create(this, R.raw.four);
        player.start();
    }

    private class DeleteCarAsyncTask extends AsyncTask<Car,  Void, Void>{

        @Override
        protected Void doInBackground(Car... cars) {
            database.getCarDao().deleteCar(cars[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
        }
    }

    private class UpdateCarAsyncTask extends AsyncTask<Car, Void, Void>{

        @Override
        protected Void doInBackground(Car... cars) {
            database.getCarDao().UpdateCar(cars[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
        }
    }

    private class CreateCarAsyncTask extends AsyncTask<Car, Void, Void>{

        @Override
        protected Void doInBackground(Car... cars) {

            long id = database.getCarDao().addCar(cars[0]);
            Car car  = database.getCarDao().getCar(id);
            if (car!=null){
                carArrayList.add(0, car);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
        }
    }

    private class GetAllCarsAsyncTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {  //то чтод должно быть сделано на заднем фоне, здесь загружаются все объекты с базы данных
            // делает работу ассинхронно на вторичном потоке

            carArrayList.addAll(database.getCarDao().getAllCars());

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) { // после загрузки данных с базы нужно уведомить адаптер об изменениях
            super.onPostExecute(aVoid);
            // метод срабатывает как только метод doInBackground заканчивает свою работу
            adapter.notifyDataSetChanged();
        }
    }
}

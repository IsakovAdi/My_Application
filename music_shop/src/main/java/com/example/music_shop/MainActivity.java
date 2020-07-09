package com.example.music_shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.music_shop.models.Order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    Button buttonMinus, buttonPlus, addToCart;
    TextView quantity, priceView;
    Spinner spinner;
    ArrayList<String> spinnerArrayList = new ArrayList<>();
    ArrayAdapter<String> spinnerAdapter;
    HashMap<String, Double> database;
    String goodName;
    ImageView goodImage;
    double price;
    EditText userName;
    List<Order> orderList = new ArrayList<>();

    int amount = 0;
    public static final String TAG = "MyTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Variable initialization
        quantity = findViewById(R.id.quantity);
        buttonMinus = findViewById(R.id.button_minus);
        buttonPlus = findViewById(R.id.button_plus);
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        priceView = findViewById(R.id.price);
        goodImage = findViewById(R.id.imageView2);
        addToCart = findViewById(R.id.addToCart);
        userName = findViewById(R.id.userName);

        // ArrayList for Spinner
        spinnerArrayList.add("Guitar");
        spinnerArrayList.add("Keyboard");
        spinnerArrayList.add("Drums");
        spinnerArrayList.add("Rock");

        //Adapter sor Spinner
        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        // Database for Spinner
        database = new HashMap<>();
        database.put("Guitar", 500.0);
        database.put("Keyboard", 1000.0);
        database.put("Drums", 700.0);
        database.put("Rock", 1500.0);

        buttonPlus.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);
        addToCart.setOnClickListener(this);

        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount--;
                if (amount < 0) {
                    amount = 0;
                }
                quantity.setText(String.valueOf(amount));
                priceView.setText(String.valueOf(price * amount));
            }
        });
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount++;
                quantity.setText(String.valueOf(amount));
                priceView.setText(String.valueOf(price * amount));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.music_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.cart) {
            Intent intent = new Intent(MainActivity.this, OrderActivity.class);
            if (!orderList.isEmpty()) {
                intent.putExtra("orders", (Serializable) orderList);
                intent.putExtra("status", 1);
            } else {
                intent.putExtra("status", 0);
            }
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodName = spinner.getSelectedItem().toString();            // guitar
        price = database.get(goodName);             // возвращает цену
        priceView.setText(String.valueOf(price));
        amount = 1;
        quantity.setText("1");

        switch (goodName) {
            case "Guitar":
                goodImage.setImageResource(R.drawable.guitar);
                break;
            case "Keyboard":
                goodImage.setImageResource(R.drawable.keyboard);
                break;
            case "Drums":
                goodImage.setImageResource(R.drawable.drums);
                break;
            case "Rock":
                goodImage.setImageResource(R.drawable.rock);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_plus:
                amount++;
                quantity.setText(String.valueOf(amount));
                priceView.setText(String.valueOf(price * amount));
                break;
            case R.id.button_minus:
                amount--;
                if (amount < 0) {
                    amount = 0;
                }
                quantity.setText(String.valueOf(amount));
                priceView.setText(String.valueOf(price * amount));
                break;
            case R.id.addToCart:
                orderProcess();
                break;
        }
    }

    public void orderProcess() {
        Order order = new Order();

        if (!TextUtils.isEmpty(userName.getText().toString())) {
            order.setUserName(userName.getText().toString());
            order.setGoodName(spinner.getSelectedItem().toString());
            order.setGoodPrice(Double.parseDouble(priceView.getText().toString()));
            order.setGoodQuantity(Integer.parseInt(quantity.getText().toString()));


            if (orderList.size()>0) {
                Order answer = isHas(order.getGoodName(), orderList);
                if (answer!=null){
                    answer.setGoodQuantity(answer.getGoodQuantity()+order.getGoodQuantity());
                    answer.setGoodPrice(answer.getGoodPrice()+order.getGoodPrice());
                }
                else {
                    orderList.add(order);
                }
            }
            else {
                orderList.add(order);
            }


            Log.i(TAG, order.toString());

            Toast.makeText(this, "Вы успешно добавили товар в корзину!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Пожалуйста заполните все поля", Toast.LENGTH_SHORT).show();
        }
    }

    public Order isHas (String goodName, List<Order> orderList){
        Order result = null;
        for (Order order: orderList) {
            if (order.getGoodName().equals(goodName)){
                result=order;
            }
        }
        return result;
    }
}

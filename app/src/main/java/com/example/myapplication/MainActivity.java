package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public void clickFunction(View view){

        EditText input = findViewById(R.id.editText); // 150
        TextView currency = findViewById(R.id.text);

        String text = input.getText().toString();
        double number = Double.parseDouble(text);
        double res = number * 73;
        String push = "$" + number + " is " + res + " KGS";
        currency.setText(push);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
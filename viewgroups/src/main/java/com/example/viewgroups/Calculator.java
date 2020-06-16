package com.example.viewgroups;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Calculator extends AppCompatActivity implements View.OnClickListener {
    TextView textResult;
    EditText firstText;
    EditText secondText;
    Button btnPlus, btnMinus, btnMulty,btnDivision;
    Double number1;
    Double number2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        firstText = findViewById(R.id.text_first);

        secondText = findViewById(R.id.text_second);

        textResult = findViewById(R.id.result_number);
        btnPlus = findViewById(R.id.btn_plus);
        btnMinus = findViewById(R.id.btn_minus);
        btnMulty = findViewById(R.id.btn_multy);
        btnDivision = findViewById(R.id.btn_division);

        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnMulty.setOnClickListener(this);
        btnDivision.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        try {
            number1 = Double.parseDouble(firstText.getText().toString());
            number2 = Double.parseDouble(secondText.getText().toString());
        } catch (NumberFormatException e){
            Toast.makeText(Calculator.this,"Enter the number", Toast.LENGTH_SHORT).show();
        }
        switch (v.getId()){

            case R.id.btn_plus:
                double sum = number1+number2;
                textResult.setText(String.valueOf(sum));
                break;
            case R.id.btn_minus:
                sum = number1-number2;
                textResult.setText(String.valueOf(sum));
                break;
            case R.id.btn_multy:
                sum = number1*number2;
                textResult.setText(String.valueOf(sum));
                break;
            case R.id.btn_division:
                sum = number1/number2;
                textResult.setText(String.valueOf(sum));
                break;
        }
    }
}
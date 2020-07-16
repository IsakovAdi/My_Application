package com.example.viewgroups;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Calculator extends AppCompatActivity {
    TextView textResult;
    EditText firstText;
    Button btnCalculate;
    char symbol = ' ';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        firstText = findViewById(R.id.text_first);
        textResult = findViewById(R.id.result_number);
        btnCalculate = findViewById(R.id.btn_calculate);

        btnCalculate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    int length = firstText.getText().toString().length();
                    String text = firstText.getText().toString();
                    StringBuilder a = new StringBuilder();
                    StringBuilder b = new StringBuilder();
                    boolean isOperatorDone = false;
                    for (int i = 0; i < length; i++) {
                        if (text.charAt(i) == '+' || text.charAt(i) == '-' || text.charAt(i) == '*' || text.charAt(i) == '/') {
                            symbol = text.charAt(i);
                            isOperatorDone = true;
                            continue;
                        }
                        if (isOperatorDone){
                            b.append(text.charAt(i));
                        }
                        else {
                            a.append(text.charAt(i));
                        }
                    }

//                    Toast.makeText(Calculator.this, "a " + a + " b: " + b+ " operator" + symbol, Toast.LENGTH_LONG ).show();
            }
        });
    }
}
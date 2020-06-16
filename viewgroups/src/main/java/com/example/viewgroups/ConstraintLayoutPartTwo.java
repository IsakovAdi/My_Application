package com.example.viewgroups;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ConstraintLayoutPartTwo extends AppCompatActivity implements View.OnClickListener {

    TextView text;
    Button btn1;
    Button btn2;
    Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout_part_two);

        text =findViewById(R.id.text);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);



        // 1 - метод
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("Была нажата кнопка 1");
            }
        });

        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

    }


    // 2 - метод
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn2:
                text.setText("Была нажата кнопка 2");
                break;
            case R.id.btn3:
                text.setText("Была нажата кнопка 3");
                break;
        }

    }


    // 3 - метод
    public void onClickBtn4(View view){
        text.setText("Была нажата кнопка 4");
    }
}
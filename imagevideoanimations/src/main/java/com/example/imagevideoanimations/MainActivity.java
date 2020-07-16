package com.example.imagevideoanimations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView bart, homer;
    boolean isBartVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bart = findViewById(R.id.bart);
        homer = findViewById(R.id.homer);

        // 0-360
//        bart.animate().scaleX(0.5f).scaleY(0.5f).rotation(360).setDuration(3000);
        bart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBartVisible) {
                    bart.animate().alpha(0).rotation(bart.getRotation()+3600).scaleY(0).scaleX(0).setDuration(3000);
                    homer.animate().alpha(1).rotation(homer.getRotation()+3600).scaleY(1).scaleX(1).setDuration(3000);
                    isBartVisible = false;
                }
                else {
                    bart.animate().alpha(1).rotation(bart.getRotation()+3600).scaleY(1).scaleX(1).setDuration(3000);
                    homer.animate().alpha(0).rotation(homer.getRotation()+3600).scaleY(0).scaleX(0).setDuration(3000);
                    isBartVisible = true;

                }

            }
        });
    }
}

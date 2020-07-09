package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7},{2,5,8}, {0,4,8}, {2,4,6}};

    public void dropIn (View view) {
        ImageView imageView = (ImageView) view;



        int tappedImage = Integer.parseInt(imageView.getTag().toString());

        if (gameState[tappedImage] == 2) {
            imageView.setTranslationY(-1500);
            gameState[tappedImage] = activePlayer;

            Log.i("Game", imageView.getTag().toString());
            if (activePlayer == 1) {
                imageView.setImageResource(R.drawable.red);
                activePlayer = 0;
            } else {
                imageView.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            }

            imageView.animate().translationYBy(1500).setDuration(300);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

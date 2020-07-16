package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    boolean gamActive = true;

    TextView winnerText;
    Button playAgain;

    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7},{2,5,8}, {0,4,8}, {2,4,6}};

    public void dropIn (View view) {
        ImageView imageView = (ImageView) view;



        int tappedImage = Integer.parseInt(imageView.getTag().toString());

        if (gameState[tappedImage] == 2&& gamActive) {
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

        for (int [] position: winningPositions) {
            if (gameState[position[0]]==gameState[position[1]]
            && gameState[position[1]]==gameState[position[2]]
            && gameState[position[0]]!=2){
                String winner = "";

                if (activePlayer == 1){
                    winner = "Yellow";
                }
                else {
                    winner = "Red";
                }

                winnerText.setText(winner + " won");
                winnerText.setVisibility(View.VISIBLE);

                playAgain.setVisibility(View.VISIBLE);

                gamActive = false;

                Toast.makeText(this, winner + " won", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        winnerText = findViewById(R.id.winnerText);
        playAgain = findViewById(R.id.playAgain);


        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConstraintLayout layout = findViewById(R.id.parentLayout);

                for (int i = 0; i < layout.getChildCount(); i++ ){
                    ImageView imageView = (ImageView) layout.getChildAt(i);

                    imageView.setImageDrawable(null);
                    gameState[i] = 2;
                }
                gamActive = true;
                activePlayer = 0;

                playAgain.setVisibility(View.INVISIBLE);
                winnerText.setVisibility(View.INVISIBLE);

            }
        });
    }
}

package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.IllegalFormatCodePointException;

public class MainActivity extends AppCompatActivity {
    MediaPlayer player;
    ImageView lamp;
    Button start;
    TextView time;


    private String format(int sec) {
        StringBuilder builder = new StringBuilder();

        int minutes = (sec / 60);
        int seconds = (sec%60);

        builder.append(String.format("%02d", minutes))
                .append(":")
                .append(String.format("%02d", seconds));


        return builder.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lamp = findViewById(R.id.lampOff);
        start = findViewById(R.id.start);
        time = findViewById(R.id.time);
        player = MediaPlayer.create(this, R.raw.alarm_bell);


        final SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(600);
        seekBar.setProgress(30);
        time.setText(format(seekBar.getProgress()));


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                time.setText(format(progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lamp.setImageResource(R.drawable.bulb_off);

                new CountDownTimer(seekBar.getProgress()*1000,1000) {
                    @Override
                    public void onTick(long l) {
                        seekBar.setProgress((int)l/1000);
                        time.setText(format((int)(l/1000)));
                        start.setEnabled(false);
                        start.setBackground(getDrawable(R.drawable.button_back));
                    }

                    @Override
                    public void onFinish() {
                        lamp.setImageResource(R.drawable.bulb_on);
                        player.start();
                        start.setEnabled(true);
                        start.setBackground(getDrawable(R.drawable.button_background));

                    }
                }.start();
            }
        });

    }
}

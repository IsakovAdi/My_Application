package com.example.androidaudio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer player;
    AudioManager audioManager;
    TextView start, finish;
    ImageButton play, pause;



    public void play(View view){

        player.start();
        play.setVisibility(View.INVISIBLE);
        pause.setVisibility(View.VISIBLE);

    }

    public void pause(View view){
        player.pause();
        play.setVisibility(View.VISIBLE);
        pause.setVisibility(View.INVISIBLE);
    }

    public void next(View view){
        player.seekTo(player.getDuration());
    }

    public void prev(View view){
        player.seekTo(0);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        player = MediaPlayer.create(this, R.raw.jasperforks);

        SeekBar volumeControl = findViewById(R.id.volumeSeekBar);
        start = findViewById(R.id.start);
        finish = findViewById(R.id.finish);
        play = findViewById(R.id.play);
        pause = findViewById(R.id.pause);

        volumeControl.setMax(maxVolume);
        volumeControl.setProgress(currentVolume);

        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        final SeekBar musicProgress = findViewById(R.id.musicProgress);

        musicProgress.setMax(player.getDuration());
        musicProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    player.seekTo(progress);
                }
                start.setText(timeFormat(progress));
                finish.setText(timeFormat(musicProgress.getMax()-progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                musicProgress.setProgress(player.getCurrentPosition());

            }
        }, 0, 1000);
    }

    private final String timeFormat (long millis) {
        StringBuffer buf = new StringBuffer();

        int minutes = (int) ((millis % (1000 * 60 * 60)) / (1000 * 60));
        int seconds = (int) (((millis % (1000 * 60 * 60)) % (1000 * 60)) / 1000);

        buf.append(String.format("%02d", minutes))
                .append(":")
                .append(String.format("%02d", seconds));

        return buf.toString();
    }
    }

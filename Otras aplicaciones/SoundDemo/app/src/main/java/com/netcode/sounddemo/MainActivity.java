package com.netcode.sounddemo;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.VideoView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    MediaPlayer mediaPlayer;
    AudioManager audioManager;

    public void inicio (View view){
        mediaPlayer.start();
    }
    public void pausa (View view) {
        mediaPlayer.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer= MediaPlayer.create(this,R.raw.boing);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        int maxVolume =audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        SeekBar volumControl=(SeekBar) findViewById(R.id.seekBar);

        volumControl.setMax(maxVolume);
        volumControl.setProgress(currentVolume);

        volumControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

       final SeekBar bar2 = (SeekBar) findViewById(R.id.seekBar2);
        bar2.setMax(mediaPlayer.getDuration());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                bar2.setProgress(mediaPlayer.getCurrentPosition());
            }
        },0,10);

        bar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }



}

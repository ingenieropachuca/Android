package com.netcode.learn;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    public void these(View view){
        mediaPlayer= MediaPlayer.create(this,R.raw.boing);
        mediaPlayer.start();
        Toast.makeText(this, "estas, estos", Toast.LENGTH_SHORT).show();
    }

    public void throw1(View view){
        mediaPlayer= MediaPlayer.create(this,R.raw.boing);
        mediaPlayer.start();
        Toast.makeText(this, "lanzar", Toast.LENGTH_SHORT).show();
    }

    public void though(View view){
        mediaPlayer= MediaPlayer.create(this,R.raw.boing);
        mediaPlayer.start();
        Toast.makeText(this, "aunque", Toast.LENGTH_SHORT).show();
    }

    public void although(View view){
        mediaPlayer= MediaPlayer.create(this,R.raw.boing);
        mediaPlayer.start();
        Toast.makeText(this, "A pesar de que", Toast.LENGTH_SHORT).show();
    }

    public void tough(View view){
        mediaPlayer= MediaPlayer.create(this,R.raw.boing);
        mediaPlayer.start();
        Toast.makeText(this, "Dificil", Toast.LENGTH_SHORT).show();
    }

    public void thought(View view){
        mediaPlayer= MediaPlayer.create(this,R.raw.boing);
        mediaPlayer.start();
        Toast.makeText(this, "Pensamiento", Toast.LENGTH_SHORT).show();
    }

    public void through(View view){
        mediaPlayer= MediaPlayer.create(this,R.raw.boing);
        mediaPlayer.start();
        Toast.makeText(this, "Mediante", Toast.LENGTH_SHORT).show();
    }

    public void those(View view){
        mediaPlayer= MediaPlayer.create(this,R.raw.boing);
        mediaPlayer.start();
        Toast.makeText(this, "Aquellos", Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }
}

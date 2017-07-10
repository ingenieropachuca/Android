package com.netcode.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void anime(View view) {


        ImageView cat1 = (ImageView) findViewById(R.id.Cat1);
        cat1.animate().alpha(0f).rotation(1800f).translationXBy(500f).translationY(500f).translationXBy(-300f).translationYBy(-300f).scaleXBy(-.5f).scaleYBy(-.5f).setDuration(2000);

       ImageView cat2 = (ImageView) findViewById(R.id.ca2);
        cat2.animate().alpha(1f).rotation(1800f).setDuration(2000);
    }
}
package com.netcode.imagenes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cambio(View view){

        ImageView imagen=(ImageView) findViewById(R.id.idFacebook);
        imagen.setImageResource(R.drawable.insta2);

        Log.i("Test","botton test");

    }
}

package com.netcode.adivinaelnumero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int numVariable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random rand= new Random();
        numVariable = rand.nextInt((10 - 0) + 1) + 0;
    }

    public void makeToast(String string)
    {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    public void prueba(View view)
    {

        int numIngresado;
        EditText numberText= (EditText) findViewById(R.id.numberText);
        String textIngresado=numberText.getText().toString();
        numIngresado=Integer.parseInt(textIngresado);

        if(numIngresado==numVariable)
        {
            makeToast("great you gessed");
            Random rand= new Random();
            numVariable = rand.nextInt((10 - 0) + 1) + 0;

        }
        if(numIngresado>numVariable)
        {
            makeToast("Lower");
        }
        if(numIngresado<numVariable)
        {
            makeToast("Higher");
        }



    }




}

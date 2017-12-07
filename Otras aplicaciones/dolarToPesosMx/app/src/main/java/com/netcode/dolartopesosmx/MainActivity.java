package com.netcode.dolartopesosmx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void cambio(View view)
    {
        EditText dolarText=(EditText)findViewById(R.id.dolarsText);
        Double dolardouble= Double.parseDouble(dolarText.getText().toString());

        double cambio;
        cambio=dolardouble*(18.1917409);
        Toast.makeText(this,dolarText.getText().toString()+" Dolars "+cambio+" Pesos"  , Toast.LENGTH_SHORT).show();

    }

}

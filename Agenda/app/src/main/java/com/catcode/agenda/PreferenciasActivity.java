package com.catcode.agenda;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

public class PreferenciasActivity extends AppCompatActivity {


    RadioButton africa,americas,asia,europe,oceania;
    EditText editText;
    ImageButton guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);

        africa = (RadioButton) findViewById(R.id.africa);
        americas = (RadioButton) findViewById(R.id.americas);
        asia = (RadioButton) findViewById(R.id.asia);
        europe = (RadioButton) findViewById(R.id.europe);
        oceania = (RadioButton) findViewById(R.id.oceania);
        editText= (EditText)findViewById(R.id.editText);
        guardar = (ImageButton) findViewById(R.id.imageButton2);
        editText.setFocusable(false);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PreferenciasActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        SharedPreferences prefencias = getSharedPreferences("paices", Context.MODE_PRIVATE);
        SharedPreferences.Editor elescriba = prefencias.edit();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.africa:
                if (checked) {
                    String pais= "Africa";
                    elescriba.putString("pais", pais);
                    elescriba.commit();
                }
                    break;
            case R.id.americas:
                if (checked){
                    String pais= "Americas";
                    elescriba.putString("pais", pais);
                    elescriba.commit();
                }
                    break;
            case R.id.asia:
                if (checked){
                    String pais= "Asia";
                    elescriba.putString("pais", pais);
                    elescriba.commit();
                }
                    break;
            case R.id.europe:
                if (checked){
                    String pais= "Europe";
                    elescriba.putString("pais", pais);
                    elescriba.commit();
                }
                    break;

            case R.id.oceania:
                if (checked){
                    String pais= "Oceania";
                    elescriba.putString("pais", pais);
                    elescriba.commit();
                }
                    break;
        }
                String nombre= prefencias.getString("pais","");
                Toast.makeText(this, "Haz Seleccionado: "+ nombre, Toast.LENGTH_SHORT).show();
    }
}
package com.catcode.ejercicio2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
// DECLARACION DE VARIABLES
    TextView texto;
    Button boton1,boton2;
    ImageButton botonimg;
    SeekBar seekbar;
    RatingBar ratingBar;
    ListView  listView;
    RadioButton radioSi, radioNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
// ENLACE DE VARIABLE CON EL id DEL XML
        texto=(TextView) findViewById(R.id.txt1);
        boton1=(Button) findViewById(R.id.button);
        boton2=(Button) findViewById(R.id.button2);
        botonimg=(ImageButton)findViewById(R.id.imageButton);
        seekbar=(SeekBar) findViewById(R.id.seekBar);
        ratingBar=(RatingBar) findViewById(R.id.ratingBar);
        listView=(ListView) findViewById(R.id.listView);
        radioSi=(RadioButton) findViewById(R.id.radioSi);
        radioNo=(RadioButton) findViewById(R.id.radioNo);

// ESTE ES EL BOTON 1
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             texto.setText("haz pulsado el boton 1");
                texto.setTextSize(28);
                texto.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
//LLAMANDO FUNCION DE RADIO BUTTON
        radioButtonWork();
//LLAMANDO FUNCION DE RADIO BUTTON
        seekBarWork();

// FLOATING BOTTON DE LA ACTIVIDAD BASICA (DEFAULT)
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
//CIERRA EL ONCREATE

//BOTTON 2 SE MANDA A LLAMAR CON ONCLICK EN XML
     public void boton2(View v){
         Toast.makeText(getApplicationContext(), "haz pulsado el boton 2", Toast.LENGTH_SHORT).show();
         texto.setTextSize(20);
         texto.setTextColor(getResources().getColor(R.color.colorAccent));
         texto.setText("haz pulsado el boton 2");
     }

//SE CREA LA FUNCION DE RADIOBUTTONWORK
     public void radioButtonWork(){
//SE CREA LA FUNCION RADIOBUTTONWORK PARA radioSi
         radioSi.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 texto.setText("Guardar");
                 botonimg.setImageResource(android.R.drawable.ic_menu_save);
             }
         });
//SE CREA LA FUNCION RADIOBUTTONWORK PARA radioNo
         radioNo.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 texto.setText("salir");
                 botonimg.setImageResource(android.R.drawable.ic_menu_delete);
             }
         });
     }

//FUNCION PARA seekBarWork
     public void seekBarWork(){
//SE DEFINE EL NUMERO MAXIMO
         seekbar.setMax(10);
//OJO QUE NO USA ONCLICK SOLO CAMBIAMOS EN onProgressChanged
         seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
             int p;
             @Override
             public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                 texto.setTextSize(progress+10);
                 texto.setText("Avance: "+progress+"/"+seekBar.getMax());
                 p=progress;
             }

             @Override
             public void onStartTrackingTouch(SeekBar seekBar) {

             }

             @Override
             public void onStopTrackingTouch(SeekBar seekBar) {
                 texto.setText("Avance: "+p+"/"+seekBar.getMax());
             }
         });
     }
// AQUI LA FUNCION imageButtonWork USA EL ratingBar PARA OBTENER LA CALIFICACION
     public void imageButtonWork(View v){
         String rv=String.valueOf(ratingBar.getRating());
         texto.setText("Rating: "+ rv);
         Toast.makeText(getApplicationContext(),"pulsaste el boton de imagen!",Toast.LENGTH_SHORT).show();
     }

//ESTAS SON POR DEFAULT DEL COMPILADOR
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

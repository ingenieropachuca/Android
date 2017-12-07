package com.catcode.ejercicio4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private static final String ARCHIVO_SP = "practica03SP";
    private static final String TEXTARCH = "notas.txt";
    boolean leeSD = false;
    //PARA QUE NOS DIGA DONDE ESTA LA RUTA PARA GUARDAR
    File ruta_sd_global = Environment.getExternalStorageDirectory();

    EditText et;
    TextView tv;
    Button b;

    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        et = (EditText) findViewById(R.id.cajaTexto);
        tv=(TextView) findViewById(R.id.fuenteArchivo);
        b= (Button) findViewById(R.id.ButtonLeer);


        // PONER ESTO EN UN onResume() PARA QUE LO ACTUALICE SIN CERRAR APLICACION
        preferences = getSharedPreferences(ARCHIVO_SP, MODE_PRIVATE);
        leeSD = preferences.getBoolean("SD", false);
        if(leeSD){
            b.setText("Leer de SD");
        }else{
            b.setText("Leer de MI");
        }
        //HASTA AQUI

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(leeSD){
                    et.setText(leerSD());
                }else{
                    et.setText(leerMI());
                }
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean escrito;
                String msg="";
                if(leeSD){
                    escrito =escribirSD(et.getText().toString());
                    msg = ruta_sd_global.getAbsolutePath()+"/"+ TEXTARCH;
                }else {
                    escrito = escribirMI(et.getText().toString());
                    msg = "Memoria Interna" + "/" + TEXTARCH;
                }
                if (escrito){
                    Snackbar.make(view, "Archivo guardado con exito en "+ msg, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    Snackbar.make(view, "Error al guardar el archivo en "+ msg, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    public String leerMI(){
        String[] archivos= fileList();
        String texto = " ";
        if(existe(archivos, TEXTARCH)){
            try{
                InputStreamReader archivo =
                        new InputStreamReader(openFileInput(TEXTARCH));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                while (linea!= null){
                    texto +=  linea+ "\n";
                    linea = br.readLine();
                }
                br.close();
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return texto;
    }


    private String leerSD() {
        String texto = "";
        try {
            File file =
                    new File(getExternalFilesDir(ruta_sd_global.getAbsolutePath()), TEXTARCH);
            BufferedReader br = new BufferedReader((new InputStreamReader(new FileInputStream(file))));
            texto = br.readLine();
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return texto;
    }







    public boolean escribirMI(String datos){
        boolean escrito =false;
        try{
            FileOutputStream file= openFileOutput(TEXTARCH,2);
            file.write(datos.getBytes());
            file.close();
            escrito =true;
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return escrito;
    }


    public boolean escribirSD(String datos){
        boolean escrito =false;
        boolean sdDisponible =false;
        boolean sdEscritura = false;
        String estadoSD = Environment.getExternalStorageState();
        Toast.makeText(getApplicationContext(), estadoSD, Toast.LENGTH_SHORT).show();
        if(estadoSD.equals(Environment.MEDIA_MOUNTED)){
            sdDisponible = true;
            sdEscritura = true;
        }else if (estadoSD.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            sdDisponible =true;
            sdDisponible= false;
        }else{
            sdDisponible=false;
            sdDisponible=true;
        }
        if(sdDisponible && sdEscritura){
            try {
                File file =new File(getExternalFilesDir(
                        ruta_sd_global.getAbsolutePath()),TEXTARCH
                );
                OutputStreamWriter fo = new OutputStreamWriter(
                        new FileOutputStream(file)
                );
                fo.write(datos);
                fo.close();
                escrito=true;
            }catch (Exception e){
                e.printStackTrace();
                Log.v("Memoria SD", "Error de Memoria SD", e);
            }
        }
        return escrito;
    }

    private  boolean existe (String[] archivos, String archivo){
        for (int f =0; f<archivos.length; f++)
            if(archivo.equals(archivos[f]))
                return true;
            return false;
    }


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

            Intent i = new Intent(this, SharePreferenceActivity.class);
            startActivity(i);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

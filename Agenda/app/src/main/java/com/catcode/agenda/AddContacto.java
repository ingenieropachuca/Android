package com.catcode.agenda;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class AddContacto extends AppCompatActivity implements View.OnClickListener{

    EditText et_nombre1, et_apellido1, et_ciudad1, et_telefono1, et_email1;
    Button boton;
    SQLControlador controlador;
    Spinner mySpinner;
    public static final String WS_URL = "https://www.restcountries.eu/rest/v2/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_personal);

        et_nombre1 = (EditText)findViewById(R.id.et_nombre);
        et_apellido1 = (EditText)findViewById(R.id.et_apellido);
        et_ciudad1 = (EditText)findViewById(R.id.et_ciudad);
        et_telefono1 = (EditText)findViewById(R.id.et_telefono);
        et_email1 = (EditText)findViewById(R.id.et_email);
        mySpinner = (Spinner)findViewById(R.id.mySpinner);
        SharedPreferences mPrefs = getSharedPreferences("paices",0);
        String str = mPrefs.getString("pais", "");

     DownloadTask task = new DownloadTask();
     task.execute("https://restcountries.eu/rest/v2/region/"+str);

        boton = (Button)findViewById(R.id.btnAgregaPersonal);
        controlador = new SQLControlador(this);
        controlador.abriBD();
        boton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAgregaPersonal:
                controlador.insertarDatos(et_nombre1.getText().toString(), et_apellido1.getText().toString(),et_ciudad1.getText().toString(),et_telefono1.getText().toString(),et_email1.getText().toString());
                Intent i = new Intent(AddContacto.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;
            default:
                break;
        }
    }

    public class DownloadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String stream;
            String urlString = urls [0];
            //INICIALIZAR UN OBJETO DE NUESTRA CLASE CREADA
            HttpDataHandler hh = new HttpDataHandler();
            //IR POR LA CONEXION CADA VEZ QUE LE PASAMOS UNA CADENA NUEVA
            stream = hh.getHTTPData(urlString);
            return stream;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            ArrayList<String> cap = new ArrayList<String>();
            try {
                JSONArray jsonArray = new JSONArray(result);

                for (int i=0; i< jsonArray.length(); i++){

                    JSONObject j0 = jsonArray.getJSONObject(i);
                    cap.add(j0.getString("capital"));
                    //PARA VER COMO SE VAN A VER PARA CUANDO ABRA LA LISTA
                    // PONER EL ADAPTADOR llamado adapter
                    Log.i("La capital es: ", j0.getString("capital"));

                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,cap);
                adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
                mySpinner.setAdapter(adapter);

                mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String resulCapital = parent.getItemAtPosition(position).toString();
                        SharedPreferences prefencias = getSharedPreferences("puntuacion", Context.MODE_PRIVATE);
                        SharedPreferences.Editor elescriba = prefencias.edit();
                        elescriba.putString("capital", resulCapital);
                        elescriba.commit();

                        String capital= prefencias.getString("capital","");
                        et_ciudad1.setText(capital, TextView.BufferType.EDITABLE);
                        Toast.makeText(AddContacto.this, capital, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }// TERMINA ASYNC TASK

}

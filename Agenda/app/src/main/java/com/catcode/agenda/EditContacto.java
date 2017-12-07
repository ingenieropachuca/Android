package com.catcode.agenda;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class EditContacto extends AppCompatActivity implements View.OnClickListener{

    EditText ed_nombre, ed_apellido, ed_ciudad, ed_telefono,ed_email;
    ImageButton ba, be;
    long persona_id;
    SQLControlador dbcon;
    Spinner mySpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal);

        dbcon = new SQLControlador(this);
        dbcon.abriBD();

        ed_nombre = (EditText) findViewById(R.id.ed_nombre);
        ed_apellido = (EditText) findViewById(R.id.ed_apellido);
        ed_ciudad = (EditText) findViewById(R.id.ed_ciudad);
        ed_telefono = (EditText) findViewById(R.id.ed_telefono);
        ed_email = (EditText) findViewById(R.id.ed_email);
        mySpinner = (Spinner)findViewById(R.id.mySpinner);


        ba = (ImageButton) findViewById(R.id.btnActualizar);
        be = (ImageButton) findViewById(R.id.btnEliminar);

        Intent i = getIntent();
        String contactoId = i.getStringExtra("ContactoId");
        String ContactoNombre = i.getStringExtra("ContactoNombre");
        String contactoApellido = i.getStringExtra("ContactoApellido");
        String contactoCiudad = i.getStringExtra("ContactoCiudad");
        String contactoTelefono = i.getStringExtra("ContactoTelefono");
        String contactoEmail = i.getStringExtra("ContactoEmail");

        persona_id = Long.parseLong(contactoId);
        ed_nombre.setText(ContactoNombre);
        ed_apellido.setText(contactoApellido);
        ed_ciudad.setText(contactoCiudad);
        ed_telefono.setText(contactoTelefono);
        ed_email.setText(contactoEmail);
        ba.setOnClickListener(this);
        be.setOnClickListener(this);
        SharedPreferences mPrefs = getSharedPreferences("paices",0);
        String str = mPrefs.getString("pais", "");

     DownloadTask task = new DownloadTask();
     task.execute("https://restcountries.eu/rest/v2/region/"+str);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnActualizar:

                AlertDialog.Builder dialog =new AlertDialog.Builder(this);
                dialog.setMessage("¿Estas seguro que desea guardar los cambios?");
                dialog.setTitle(getResources().getString(R.string.app_name));
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbcon.actualizarDatos(persona_id, ed_nombre.getText().toString(), ed_apellido.getText().toString(),ed_ciudad.getText().toString(),ed_telefono.getText().toString(),ed_email.getText().toString());
                        returnHome();
                    }
                });
                dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialog.show();
                break;
            case R.id.btnEliminar:
                AlertDialog.Builder dialog1 =new AlertDialog.Builder(this);
                dialog1.setMessage("¿Estas seguro que deseas eliminar este contacto?");
                dialog1.setTitle(getResources().getString(R.string.app_name));
                dialog1.setCancelable(false);
                dialog1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbcon.borrarDatos(persona_id);
                        returnHome();
                    }
                });
                dialog1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialog1.show();
                break;
        }

    }

    public void returnHome(){
        Intent i = new Intent(getApplicationContext(),
                MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
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
                        ed_ciudad.setText(capital, TextView.BufferType.EDITABLE);
                        Toast.makeText(EditContacto.this, capital, Toast.LENGTH_SHORT).show();
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

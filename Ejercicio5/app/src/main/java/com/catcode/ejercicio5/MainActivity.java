package com.catcode.ejercicio5;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //DECLARANDO VARIABLES
    ListView myList;
    Spinner mySpinner;
    String urlWs;
    static ArrayList<String> data = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // CAST VISTA CON VARIABLES
        myList = (ListView) findViewById(R.id.listView);
        mySpinner = (Spinner) findViewById(R.id.spinner);
        // MANDA A LLAMAR LA FUNCION
        spinnerWork();

        // PARTE DEL FLOATING BUTTON
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void spinnerWork(){
        final String[] datos = {"Africa","Americas","Asia","Europe","Oceania"};
        //LENAR EL SPINNER
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, datos);
        //PARA VER COMO SE VAN A VER PARA CUANDO ABRA LA LISTA
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        // PONER EL ADAPTADOR llamado adapter
        mySpinner.setAdapter(adapter);
        //CUANDO LE DE CLICK A UN SPINER QUE CARGUE LOS PAISES
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(), "Cargando los paises de la Region: "
                        + parent.getItemAtPosition(position).toString()
                        , Toast.LENGTH_SHORT).show();

                // TRAE LOS PAISES DE LA REGION SELECCIONADA
                urlWs = Ejercicio5.WS_URL_REGION + Uri.encode(parent.getItemAtPosition(position).toString());
                new ProcessJson().execute(urlWs);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    //SI EXTIENDE DE ALGO REQUIERE METODOS SOBRESCRITOS
    private class ProcessJson extends AsyncTask<String, Void, String> {

    //METODOS SOBRE ESCRITOS
        @Override
        protected void onPostExecute(String s) {
            if(s!= null){
                try{
                    //LIMPIAR LA LISTA
                    data.clear();
                    //VAMOS A TOMAR LOS ELEMENTOS DEL STREAM
                    JSONArray jsonArray = new JSONArray(s);
                    for (int i =0 ; i<jsonArray.length();i++){
                        //CADA ELEMENTO ES UN JASON OBJET Y JSON ARRAY ES TODA LA LISTA
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        //PARA AGREGAR A LA LISTA LLENARLA CON TODOS LOS NOMBRES
                        data.add(jsonObject.getString("name"));
                    }
                    //
                ArrayAdapter<String> adapter= new ArrayAdapter<String>(MainActivity.this,
                        R.layout.pais,R.id.paistxt,data);
                //PARA REFRESCAR LOS DATOS
                    adapter.notifyDataSetChanged();
                    myList.setAdapter(adapter);

                    myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        //PASA UNA VISTA Y LA POSICION DE LA LISTA
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent i = new Intent(getApplicationContext(), PaisActivity.class);
                            i.putExtra(Ejercicio5.EXTRA_PAIS, parent.getItemAtPosition(position).toString());
                            startActivity(i);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }

        //CORRER EN LA PARTE DE ATRAS
        @Override
        protected String doInBackground(String... params) {
            String stream;
            String urlString = params [0];
            //INICIALIZAR UN OBJETO DE NUESTRA CLASE CREADA
            HttpDataHandler hh = new HttpDataHandler();
            //IR POR LA CONEXION CADA VEZ QUE LE PASAMOS UNA CADENA NUEVA
            stream = hh.getHTTPData(urlString);
            return stream;
        }
    }

    /*
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
    }*/
}

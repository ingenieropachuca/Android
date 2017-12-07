package com.catcode.practica5;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ListView list;
    SQLControlador dbcon;
    TextView tv_personaId,tv_personaNom, tv_personaCom, tv_personaImg;
    LazyAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list = (ListView) findViewById(R.id.lvPersona);
        dbcon = new SQLControlador(this);
        dbcon.abrirDB();
        Cursor cursor= dbcon.leerDatos();
        ArrayList<HashMap <String,String>> personas = new ArrayList<HashMap<String, String>>();

        for (cursor.moveToFirst(); cursor.isAfterLast();cursor.moveToNext()){
            HashMap<String, String> map = new HashMap<String, String>();

            map.put(DBhelper.PERSONAL_ID,cursor.getString(0));
            map.put(DBhelper.PERSONAL_NOMBRE,cursor.getString(1));
            map.put(DBhelper.PERSONAL_COMENTARIOS,cursor.getString(2));
            map.put(DBhelper.PERSONAL_IMG,cursor.getString(3));

            personas.add(map);
        }
        adapter = new LazyAdapter(this, personas);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv_personaId = (TextView) view.findViewById(R.id.personal_id);
                tv_personaNom = (TextView) view.findViewById(R.id.personal_nombre);
                tv_personaCom = (TextView) view.findViewById(R.id.personal_comentarios);
                tv_personaImg = (TextView) view.findViewById(R.id.personal_img);


                Intent i = new Intent(getApplicationContext(), AddPersona.class);
                i.putExtra("PersonaID", tv_personaId.getText().toString());
                i.putExtra("personaNombre", tv_personaNom.getText().toString());
                i.putExtra("personaComentario", tv_personaCom.getText().toString());
                i.putExtra("personaImg", tv_personaImg.getText().toString());
                i.putExtra("accion", "EDITAR");
                startActivity(i);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent i = new Intent(getApplicationContext(),AddPersona.class);
                i.putExtra("accion", "INSERTAR");
                startActivity(i);
            }
        });
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

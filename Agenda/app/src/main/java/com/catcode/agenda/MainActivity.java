package com.catcode.agenda;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView myListView;
    SQLControlador controlador;
    TextView tv_id, tv_nombre , tv_ciudad ,tv_apellido, tv_telefono, tv_email;
    ImageButton btnAdd;
    private static final int REQUEST_CALL = 1;
    Intent intent1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnAdd = (ImageButton) findViewById(R.id.btnAdd);
        controlador = new SQLControlador(this);
        controlador.abriBD();
        myListView = (ListView)findViewById(R.id.myListView);
        registerForContextMenu(myListView);

        Cursor cursor = controlador.leerDatos();
        String[] from = new String[]{
                DBhelper.CONTACTOS_ID,
                DBhelper.CONTACTOS_NOMBRE,
                DBhelper.CONTACTOS_APELLIDO,
                DBhelper.CONTACTOS_CIUDAD,
                DBhelper.CONTACTOS_TELEFONO,
                DBhelper.CONTACTOS_EMAIL
        };
        int[] to = new int[]{
                R.id.contactos_id,
                R.id.contactos_nombre,
                R.id.contactos_apellido,
                R.id.contactos_ciudad,
                R.id.contactos_telefono,
                R.id.contactos_email

        };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getApplicationContext(), R.layout.formato_fila,
                cursor, from, to, 0
        );

        adapter.notifyDataSetChanged();
        myListView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddContacto.class);
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
            Intent i = new Intent(this, PreferenciasActivity.class);
            startActivity(i);
            return true;
        }

        if(id== R.id.action_exit){
            System.exit(0);
        }

        return super.onOptionsItemSelected(item);
    }

    //AQUI SE INFLA EL MENU
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater =getMenuInflater();
        AdapterView.AdapterContextMenuInfo info
                = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle("Menu de opciones");
        inflater.inflate(R.menu.menu_contextual, menu);
    }


    // AQUI SE SELECCIONAN LOS ELEMENTOS DEL MENU
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case  R.id.op1:
                tv_telefono = (TextView) findViewById(R.id.contactos_telefono);
                String tel =tv_telefono.getText().toString();
                intent1=new Intent(Intent.ACTION_CALL, Uri.parse("tel: "+tel));
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
                }else {
                    startActivity(intent1);
                }
                return true;

            case  R.id.op2:
                tv_email  = (TextView) findViewById(R.id.contactos_email);
                String mail = tv_email.getText().toString();
                Intent intent2 =new Intent(Intent.ACTION_SEND);
                intent2.setType("text/plain");
                intent2.putExtra(Intent.EXTRA_SUBJECT, "Jose Carlos Pachuca Alvarez");
                intent2.putExtra(Intent.EXTRA_TEXT, "Email: ");
                intent2.putExtra(Intent.EXTRA_EMAIL,new String []{mail});
                //ESCOGER TODAS LAS APLICACIONES DE MAIL QUE TIENE TU CEL
                try {
                    startActivity(Intent.createChooser(intent2,"Aplicacion de Email"));
                }catch (android.content.ActivityNotFoundException e){
                    e.printStackTrace();
                }
                return true;
            case  R.id.op3:
                tv_id = (TextView) findViewById(R.id.contactos_id);
                tv_nombre = (TextView) findViewById(R.id.contactos_nombre);
                tv_apellido = (TextView) findViewById(R.id.contactos_apellido);
                tv_ciudad = (TextView) findViewById(R.id.contactos_ciudad);
                tv_telefono = (TextView) findViewById(R.id.contactos_telefono);
                tv_email  = (TextView) findViewById(R.id.contactos_email);

                Intent intent3 = new Intent(getApplicationContext(), EditContacto.class);
                intent3.putExtra("ContactoId", tv_id.getText().toString());
                intent3.putExtra("ContactoNombre", tv_nombre.getText().toString());
                intent3.putExtra("ContactoApellido", tv_apellido.getText().toString());
                intent3.putExtra("ContactoCiudad", tv_ciudad.getText().toString());
                intent3.putExtra("ContactoTelefono", tv_telefono.getText().toString());
                intent3.putExtra("ContactoEmail", tv_email.getText().toString());
                startActivity(intent3);

                return true;

            case  R.id.op4:
                tv_telefono = (TextView) findViewById(R.id.contactos_telefono);
                String phone =tv_telefono.getText().toString();
                Intent intent4 = new Intent(Intent.ACTION_VIEW);
                intent4.putExtra("sms_body","Hola soy Carlos Pachuca");
                intent4.putExtra("address", phone);
                intent4.setType("vnd.android-dir/mms-sms");
                startActivity(intent4);
            return true;
        }
        return super.onContextItemSelected(item);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case REQUEST_CALL:
            {
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    startActivity(intent1);
                }else{
                }
            }
        }
    }// TERMINA onRequestPermissionsResult

}

package com.catcode.ejercicio3;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // DECLARACION DE VARIABLES
    ListView listView;
    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // LIGAR VARIABLES CON VISTAS
        texto= (TextView)findViewById(R.id.textView3);
        listView=(ListView)findViewById(R.id.listView);
        //MANDAR A LLAMAR LA FUNCION
        listViewWork();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

// FUNCION PARA HACER FUNCIONAR LA LISTA
    public void listViewWork(){
        //ELEMENTOS DE LA LISTA
        String[] datos = new String[]{
                "Toast", "Toast Personalizado", "Snak Bar", "Cuadro de Dialogo", "Notificacion", "Progress Dialog"
        };
        //ADAPTAR LOS ELEMENTOS STRING EN LA VISTA DE LA LISTA
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,datos);
        listView.setAdapter(adapter);
        //PARA EL POP MENU
        registerForContextMenu(listView);

        //PARA CADA ELEMENTO DE LA LISTA UNA ACCION DIFERENTE
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    //EN EL PRIMER ELEMENTO DE LA LISTA USANMOS UN TOAST
                    case 0:
                        Toast.makeText(MainActivity.this, "Toast de ejemplo", Toast.LENGTH_SHORT).show();
                    break;
                    //EN EL SEGUNDO ELEMENTO DE LA LISTA USAMOS UN TOAST PERSONALIZADO
                    case 1:
                        //PARA USAR UN LAYOUT COMO TOAST EN NUESTRO CASO toast_layout
                        LayoutInflater li = getLayoutInflater();
                        View layout = li.inflate(R.layout.toast_layout,
                                (ViewGroup) findViewById(R.id.toastLinearLayout)
                        );
                        Toast toast = new Toast(getApplicationContext());
                        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(layout);
                        toast.show();
                    break;
                    // PARA EL TERCER ELEMENTO USAMOS UN snak bar
                    case 2:
                       Snackbar.make(view,"Ejemplo de snak bar", Snackbar.LENGTH_LONG).setAction("Action",null).show();
                    break;
                    // MANDA A LLAMAR UNA FUNCION ALERT
                    case 3:
                        mostrarAlertDialog();
                    break;
                    //MANDA A LLAMAR LA FUNCION DE NOTIFICACION
                    case 4:
                        mostrarNotificacion();
                    break;
                    //LLAMA LA FUNCION PARA UN  progressDialog
                    case 5:
                        mostrarProgressDialog();
                     break;
                }

            }
        });// PARA CADA UNO DE LOS ELEMENTOS
    }

    public void mostrarAlertDialog(){

        AlertDialog.Builder dialog =new AlertDialog.Builder(this);
        dialog.setMessage("¿Deseas Salir?");
        dialog.setTitle(getResources().getString(R.string.app_name));
        dialog.setCancelable(false);
        dialog.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    public void mostrarNotificacion(){
        //ICONO DE LA NOTIFICACION
        int icono =R.mipmap.marca;
        // TITULO
        CharSequence titulo = "Notificacion practica 02";
        CharSequence titulobar= "Notificacion barra ";
        // TEXTO EN LA NOTIFICACION
        CharSequence texto= "Ejemplo de lanzamiento de notificaciones"+"Practica 02";
// PARA ABRIR UNA ACTVIDAD CON LA NOTIFICAION
        // SE MANDA A LLAMAR A LA CLASE SecondActivity
        Intent ni =new Intent(this, SecondActivity.class);
        ni.putExtra("notificaion",texto);
        ni.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
// ESPERANDO A QUE REACCIONEMOS
        PendingIntent pi = PendingIntent.getActivity(
                this, 0, ni, 0
        );

        //CONSTRUIR LA NOTIFICACION CON LOS ANTERIORES PARAMETROS
        Notification notification = new Notification.Builder(this)
        .setTicker(titulo)
        .setContentTitle(titulobar)
        .setContentText(texto)
        .setSmallIcon(icono)
        .setAutoCancel(false)
        .setContentIntent(pi)
        .build();

        // QUE LA NOTIFICACION SE MUESTRE CON LOS AJUSTED POR DEFAULT DEL TELEFONO
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notification.defaults |= Notification.DEFAULT_LIGHTS;

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(0,notification);
    }

    // EL PROGRES DIALOG NO LO LLENAMOS
    public void mostrarProgressDialog(){
    }
    //AQUI TERMINA LA LISTA


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

        if(id == R.id.action_two){
            Intent i = new Intent(this, SecondActivity.class);
            i.putExtra("Notificacion", "http://www.google.com");
            startActivity(i);
            return true;
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
        menu.setHeaderTitle(listView.getAdapter().getItem(info.position).toString());
        inflater.inflate(R.menu.menu_contextual, menu);
    }
// AQUI SE SELECCIONAN LOS ELEMENTOS DEL MENU
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
          case  R.id.op1:
            texto.setText("Lista["+ info.position + "]"
            + "Opecion 1 Elegida");
            return true;

            case  R.id.op2:
                texto.setText("Lista["+ info.position + "]"
                        + "Opecion 2 Elegida");
                return true;
            case  R.id.op3:
                texto.setText("Lista["+ info.position + "]"
                        + "Opecion 3 Elegida");
                return true;
        }
        return super.onContextItemSelected(item);
    }
}

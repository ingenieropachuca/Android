package com.catcode.contacto;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class EditContacto extends AppCompatActivity implements View.OnClickListener{

    EditText ed_nombre, ed_apellido, ed_ciudad, ed_telefono,ed_email;
    ImageButton ba, be;
    long persona_id;
    SQLControlador dbcon;

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
}

package com.catcode.contacto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddContacto extends AppCompatActivity implements View.OnClickListener{

    EditText et_nombre1, et_apellido1, et_ciudad1, et_telefono1, et_email1;
    Button boton;
    SQLControlador controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_personal);

        et_nombre1 = (EditText)findViewById(R.id.et_nombre);
        et_apellido1 = (EditText)findViewById(R.id.et_apellido);
        et_ciudad1 = (EditText)findViewById(R.id.et_ciudad);
        et_telefono1 = (EditText)findViewById(R.id.et_telefono);
        et_email1 = (EditText)findViewById(R.id.et_email);

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
}

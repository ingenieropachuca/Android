package com.catcode.practica5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPersona extends AppCompatActivity implements View.OnClickListener {

    EditText etNombre, etComentario, etRutaImg;
    Button be, ba, bi;
    long persona_id;
    SQLControlador dbcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_persona);

        etNombre = (EditText) findViewById(R.id.et_persona_nombre);
        etComentario = (EditText) findViewById(R.id.et_persona_comentario);
        etRutaImg= (EditText) findViewById(R.id.et_persona_img);

        be= (Button) findViewById(R.id.btnEliminar);
        ba= (Button) findViewById(R.id.btnActualizar);
        bi= (Button) findViewById(R.id.btnInsertar);

        be.setOnClickListener(this);
        ba.setOnClickListener(this);
        bi.setOnClickListener(this);

        //OJO AQUI
        Intent i = getIntent();


        if(i.getStringExtra("accion").equals("INSERTAR")){

            etNombre.setText(" ");
            etComentario.setText(" ");
            etRutaImg.setText(" ");
            bi.setVisibility(View.VISIBLE);
            ba.setVisibility(View.GONE);
            be.setVisibility(View.GONE);


        } else{
            persona_id =Long.parseLong(i.getStringExtra("personaID"));
            etNombre.setText(i.getStringExtra("personaNombre"));
            etComentario.setText(i.getStringExtra("personaComentario"));
            etRutaImg.setText(i.getStringExtra("personaImg"));
            bi.setVisibility(View.GONE);

        }

        dbcon = new SQLControlador(this);
        dbcon.abrirDB();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnInsertar:
                dbcon.insertarDatos(etNombre.getText().toString(),etComentario.getText().toString(),etRutaImg.getText().toString());
                returnHome();
                break;
            case R.id.btnActualizar:
                dbcon.actualizarDatos(persona_id,
                        etNombre.getText().toString(),
                        etComentario.getText().toString(),
                        etRutaImg.getText().toString());
                returnHome();
                break;
            case R.id.btnEliminar:
                dbcon.borrarDatos(persona_id);
                break;
        }
    }
    private void returnHome(){
        Intent i = new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}

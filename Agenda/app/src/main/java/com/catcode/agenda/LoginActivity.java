package com.catcode.agenda;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText editEmail;
    EditText editPass;
    Button btn_ingresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail = (EditText) findViewById(R.id.editEmail);
        editPass = (EditText) findViewById(R.id.editPass);
        btn_ingresar = (Button) findViewById(R.id.btn_ingresar);



        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmail.getText().toString();
                String pass = editPass.getText().toString();
                SharedPreferences prefencias = getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor elescriba = prefencias.edit();
                elescriba.putString("email", email);
                elescriba.putString("contrasenia",pass);
                elescriba.commit();
                
                if(logIn(email,pass)){
                    ingresar();
                }
            }
        });

    }

    public boolean logIn (String email, String pass){
        if(!emailValido(email)){
            Toast.makeText(this, "Este no es un Email valido", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!passValido(pass)){
            Toast.makeText(this, "Password debe tener mas de 4 caracteres", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }


    public boolean emailValido(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean passValido(String pass){
        return pass.length()>4;
    }

    public void ingresar(){
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}
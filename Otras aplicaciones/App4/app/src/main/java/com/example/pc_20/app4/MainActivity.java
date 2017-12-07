package com.example.pc_20.app4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnSecond;
    ImageView btnImage;
    EditText texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSecond =(Button) findViewById(R.id.btnSecond);
        btnImage = (ImageView) findViewById(R.id.btnImage);
        texto = (EditText) findViewById(R.id.texto);


        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), nintendoActivity.class);

                String datos = texto.getText().toString();
                i.putExtra("texto",datos);

                startActivity(i);
            }
        });

        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), secondActivity.class);
                startActivity(i);
            }
        });

    }
}

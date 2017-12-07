package com.example.pc_20.app4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class nintendoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nintendo);

        String datos2 = getIntent().getStringExtra("texto");
        Toast.makeText(this, "Escribiste: "+ datos2, Toast.LENGTH_SHORT).show();
    }
}

package com.catcode.ejercicio4;

import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

public class SharePreferenceActivity extends AppCompatActivity {

    //DECLARANDO VARIABLES
    EditText et1;
    CheckBox chTerminos;
    RadioButton r1, r2, r3;
    SeekBar seekBar;
    Switch aSwitch;
    ImageButton boton;

    //CONSTANTE
    private static final String ARCHIVO_SP = "practica03SP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preference);

        et1=(EditText) findViewById(R.id.editText1);
        chTerminos = (CheckBox) findViewById(R.id.checkBox);
        r1=(RadioButton) findViewById(R.id.radio1);
        r2=(RadioButton) findViewById(R.id.radio2);
        r3=(RadioButton) findViewById(R.id.radio3);
        seekBar= (SeekBar) findViewById(R.id.seekBar);
        boton=(ImageButton) findViewById(R.id.btnGuardar);
        aSwitch=(Switch) findViewById(R.id.switch1);

        SharedPreferences preferences = getSharedPreferences(ARCHIVO_SP, MODE_PRIVATE);

        et1.setText(preferences.getString ("EMAIL", ""));

        chTerminos.setChecked(preferences.getBoolean("TERMINOS", false));

        int op =preferences.getInt("OPCIONES", -1);
        switch (op){
            case 1:
                r1.setChecked(true);
                break;
            case 2:
                r2.setChecked(true);
                break;
            case 3:
                r3.setChecked(true);
                break;
        }

        seekBar.setProgress(preferences.getInt("VOLUMEN",5));
        aSwitch.setChecked(preferences.getBoolean("SD", false));

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar_sp();
            }
        });
    }

    public void guardar_sp(){
        int op = -1;
        if(r1.isChecked()){
            op=1;
        }else if (r2.isChecked()){
            op=2;
        }else if(r3.isChecked()){
            op=3;
        }
        SharedPreferences preferences
                =getSharedPreferences(ARCHIVO_SP, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("EMAIL", et1.getText().toString());
        editor.putBoolean("TERMINOS",chTerminos.isChecked());
        editor.putInt("OPCION", op);
        editor.putInt("VOLUMEN",seekBar.getProgress());
        editor.putBoolean("SD",aSwitch.isChecked());
        editor.commit();
        Toast.makeText(this, "Se guardaron los datos en "+ ARCHIVO_SP, Toast.LENGTH_SHORT).show();
        finish();

    }
}

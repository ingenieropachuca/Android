package com.catcode.ejercicio3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    // DECLARANDO VARIABLES
    TextView tw;
    EditText valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tw = (TextView) findViewById(R.id.textView2);
        Intent gi = getIntent();
        tw.setText(gi.getStringExtra("notification"));

        // OBTENER EL VALOR DEL EditText
        valor=(EditText)findViewById(R.id.editText);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void bntWeb(){
        String url = valor.getText().toString();
        if(url.isEmpty()){
            url = "https://www.unam.mx";
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void btnTel(){
        Intent i =new Intent(Intent.ACTION_DIAL);
        startActivity(i);
    }

    public void bntCall(){
        String tel =valor.getText().toString();
        if(tel.isEmpty()){
            tel="5527031148";
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(tel));
        startActivity(i);
    }

    public void btnMap(){
        Uri carl= Uri.parse("geo:19.692229,-98.84569220?z=17");
        Intent i =new Intent(Intent.ACTION_VIEW, carl);
        startActivity(i);
    }

    public void btnMail(){
        Intent i =new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "Pueba de correo");
        i.putExtra(Intent.EXTRA_TEXT, valor.getText().toString());
        i.putExtra(Intent.EXTRA_EMAIL,
                new String[]{"ingenieropachuca@gmail.com","megomar@gmail.com"});
        //ESCOGER TODAS LAS APLICACIONES DE MAIL QUE TIENE TU CEL
        try {
            startActivity(Intent.createChooser(i,"¿Quien puede enviar un email?"));
        }catch (android.content.ActivityNotFoundException e){
            e.printStackTrace();
        }
    }

    public void bntSms(){
        String tel =valor.getText().toString();
        if(tel.isEmpty()){
            tel="5527031148";
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.putExtra("sms_body","Hola Mundo TEP");
        i.putExtra("address", tel);
        i.setType("vnd.android-dir/mms-sms");
        startActivity(i);
    }
    public void bntAlarm(){
        Intent i =new Intent(AlarmClock.ACTION_SET_ALARM)
        .putExtra(AlarmClock.EXTRA_MESSAGE, valor.getText().toString())
        .putExtra(AlarmClock.EXTRA_HOUR,12)
        .putExtra(AlarmClock.EXTRA_MINUTES,40);
        if(i.resolveActivity(getPackageManager())!= null){
            startActivity(i);
        }
    }

    public  void bntCamera(){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(i.resolveActivity(getPackageManager())!= null){
            startActivity(i);
        }
    }

    public void btnAction(View v){
        switch (v.getId()){
            case R.id.button:
                bntWeb();
                break;

            case R.id.button2:
                btnTel();
                break;
            case R.id.button3:
                bntCall();
                break;
            case R.id.button4:
                btnMap();
                break;
            case R.id.button5:
                btnMail();
                break;
            case R.id.button6:
                bntSms();
                break;
            case R.id.button7:
                bntAlarm();
                break;
            case R.id.button8:
                bntCamera();
                break;
        }
    }

}

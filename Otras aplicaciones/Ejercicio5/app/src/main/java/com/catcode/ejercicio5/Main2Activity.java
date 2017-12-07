package com.catcode.ejercicio5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmadrosid.svgloader.SvgLoader;

public class Main2Activity extends AppCompatActivity {

    ImageView imgBandera;
    TextView tv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imgBandera= (ImageView) findViewById(R.id.imgBandera);
        tv3 = (TextView) findViewById(R.id.tv3);

        String datos2 = getIntent().getStringExtra("texto");
        String datos3 = getIntent().getStringExtra("nombre");

        SvgLoader.pluck()
                .with(this)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .load(datos2, imgBandera);
        tv3.setText(datos3);
    }
    @Override protected void onDestroy() {
        super.onDestroy();
        SvgLoader.pluck().close();
    }
}

package com.netcode.timetable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    public void metodo(int tiempo)
    {
        ListView myListView = (ListView)findViewById(R.id.myListView);
        ArrayList<String> myTimeList= new ArrayList<String>();

        for (int i=1;i<=1000;i++) {
            myTimeList.add(Integer.toString(i*tiempo));
        }
        ArrayAdapter<String> myArrayAdapter =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myTimeList);
        myListView.setAdapter(myArrayAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekBar=(SeekBar) findViewById(R.id.seekBar);
        //INICIALIZANDO EL SEEKBAR
        seekBar.setMax(20);
        seekBar.setProgress(10);

        // OBRENER EL VALOR DEL SLIDER CON LA VARIABLE TIEMPO
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int min=1;
                int tiempo;
                if (progress<min)
                {
                    tiempo=min;
                    seekBar.setProgress(min);
                }
                else {
                    tiempo=progress;
                }
                metodo(tiempo);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        metodo(10);
    }
}

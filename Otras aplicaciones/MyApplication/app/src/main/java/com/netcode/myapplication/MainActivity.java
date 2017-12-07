package com.netcode.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Console;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     /*   final ListView myListView = (ListView) findViewById(R.id.myListView);
        ArrayList<String> myfamily=new ArrayList<String>();
        myfamily.add("Carlos");
        myfamily.add("Jersa");
        myfamily.add("Janet");
        myfamily.add("Julio");

        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myfamily);
        myListView.setAdapter(arrayAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String value =myListView.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();

            }
        });*/
        final ListView myListView= (ListView)findViewById(R.id.myListView);
        ArrayList<String> myfriends=new ArrayList<String>();
        myfriends.add("Rosalia");
        myfriends.add("Carlos");

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myfriends);

        myListView.setAdapter(arrayAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String friends = myListView.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, friends, Toast.LENGTH_SHORT).show();
            }
        });


    }





}

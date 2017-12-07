package com.catcode.ejercicio5;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmadrosid.svgloader.SvgDecoder;
import com.ahmadrosid.svgloader.SvgDrawableTranscoder;
import com.ahmadrosid.svgloader.SvgLoader;
import com.ahmadrosid.svgloader.SvgSoftwareLayerSetter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;

public class PaisActivity extends AppCompatActivity {

    String imageUrl;
    String urlWS;
    TextView tv;
    TextView txtBandera;
    String nombre;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pais);

        tv= (TextView) findViewById(R.id.tv);
        txtBandera = (TextView) findViewById(R.id.txtBandera);

        //PONER TITULO DEL PAIS EN EL TITULO DE LA ACTIVIDAD
        Intent i = getIntent();
        String pais = i.getStringExtra(Ejercicio5.EXTRA_PAIS);
        setTitle(pais);

        urlWS = Ejercicio5.WS_URL_PAIS + Uri.encode(pais);
        tv.setText(urlWS);
        new ProcessJson().execute(urlWS);
    }
    private class ProcessJson extends AsyncTask<String, Void,String>  {
        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(s);
            String data = tv.getText().toString();
            String nl = "\n";
            String tb = "\t";
            if(s != null){
               try {
                   data = data + nl+ "Informacion General"+ nl;
                   JSONArray jsonArray = new JSONArray(s);

                   for (int i =0; i<jsonArray.length(); i++){
                       JSONObject j0 = jsonArray.getJSONObject(i);

                       data = data + tb + tb + "CAPITAL:" + j0.getString("capital")+ nl
                               + tb + tb + "Region: " + j0.getString("region")+ nl
                               + tb + tb + "SubRegion: " + j0.getString("subregion")+ nl
                               + tb + tb + "Poblacion: " + j0.getString("population")+ nl
                               + tb + tb + "Denominacion: " + j0.getString("demonym")+ nl
                               + tb + tb + "Abreviatura: " + j0.getString("alpha2Code")+ nl
                               + tb + tb + "Code: " + j0.getString("alpha3Code")+ nl;

                       JSONArray jA1 = j0.getJSONArray("borders");
                       data = data + tb + tb + "Fronteras:" + nl;
                       for (int j =0 ; j<jA1.length();j++){
                           data = data + tb + tb+ tb + tb + jA1.getString(j)+ nl;
                       }

                       JSONObject j01 = j0.getJSONObject("translations");
                       data = data + tb + tb + "Traducciones: " + nl
                               +tb + tb +tb + tb + "Danes: " + j01.getString("de")+ nl
                               +tb + tb +tb + tb + "Frances: " + j01.getString("fr")+ nl
                               +tb + tb +tb + tb + "Italiano: " + j01.getString("it")+ nl
                               +tb + tb +tb + tb + "Japones: " + j01.getString("ja")+ nl
                               +tb + tb +tb + tb + "Español: " + j01.getString("es")+ nl;
                       imageUrl = j0.getString("flag");
                       nombre = j0.getString("name");
                   }
                   //PASA UNA VISTA Y LA POSICION DE LA LISTA
                   tv.setText(data);
                   txtBandera.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           Intent i = new Intent(getBaseContext(), Main2Activity.class);
                           String datos = imageUrl.toString();
                           String name = nombre.toString();
                           i.putExtra("nombre",nombre);
                           i.putExtra("texto",datos);
                           startActivity(i);
                       }
                   });
               } catch (JSONException e) {
                   e.printStackTrace();
               }
            }else {
                Toast.makeText(getApplicationContext(),"Sin datos para mostrar", Toast.LENGTH_SHORT).show();
            }

        }
        @Override
        protected String doInBackground(String... params) {
            String stream =null;
            String urlString = params[0];
            HttpDataHandler hh = new HttpDataHandler();
            stream = hh.getHTTPData(urlString);
            return stream;
        }
    }
}
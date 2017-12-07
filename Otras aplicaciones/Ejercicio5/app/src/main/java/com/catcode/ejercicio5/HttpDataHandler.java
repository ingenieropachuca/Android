package com.catcode.ejercicio5;

import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Carlos on 23/10/2017.
 */

public class HttpDataHandler {
    static String stream = null;
    private String DEBUG_MESSAGE = "Practica 05 JSON";

    public HttpDataHandler(){

    }

    public String getHTTPData(String urlString){
        try {
            // CONEXION CON LA URL
            URL url = new URL(urlString);
            // ABRIR LA CONEXION
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            //400 Errores de usuario codigos IEM

            //OBTENER LA CONEXION HTTP Codigo get response
            int connCode = urlConnection.getResponseCode();

            //INFORMACION DE LA APLICACION
            Log.i(DEBUG_MESSAGE, "Codigo HTTP"+connCode);

            //PARA VER SI LA CONEXION ESTA EN 200 codigo IEM
            if(connCode == HttpsURLConnection.HTTP_OK){
                //NOS VA A TRAER UN STREAM DE TEXTO NOS LO TRAE LA CONEXION CON EL METODO GET IMPUT STREAM
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                //LEERLO EN MEMORIA BUFER DE TEXTO
                BufferedReader r = new BufferedReader(new InputStreamReader(in));
                //GENERAR LA CADENA
                StringBuilder sb = new StringBuilder();
                String line;
                //MIENTRAS PUEDA LEERLO
                while ((line = r.readLine()) !=null ){
                    //
                    sb.append(line);
                }
                stream = sb.toString();
                urlConnection.disconnect();
            }else {
                Log.i(DEBUG_MESSAGE,"Error de conexion HTTP");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stream;
    }
}

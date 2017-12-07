package com.catcode.practica5;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Carlos on 16/10/2017.
 */

public class LazyAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<HashMap <String, String>> data;
    private static LayoutInflater inflater = null;
    public ImageLoader imageLoader;

    public LazyAdapter(Activity a, ArrayList<HashMap<String, String>> data) {
        this.activity= a;
        this.data = data;
        inflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE
        );
        imageLoader = new ImageLoader(activity.getApplicationContext());
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if(convertView== null)
            vi=inflater.inflate(R.layout.formato_fila, null);

        TextView id =(TextView) vi.findViewById(R.id.personal_id);
        TextView nombre =(TextView) vi.findViewById(R.id.personal_nombre);
        TextView comentarios =(TextView) vi.findViewById(R.id.personal_comentarios);
        TextView img = (TextView) vi.findViewById(R.id.personal_img);
        ImageView thumbImg= (ImageView) vi.findViewById(R.id.list_img);
        HashMap<String, String> persona= new HashMap<String, String>();
        persona = data.get(position);

        id.setText(persona.get(DBhelper.PERSONAL_ID));
        nombre.setText(persona.get(DBhelper.PERSONAL_NOMBRE));
        comentarios.setText(persona.get(DBhelper.PERSONAL_COMENTARIOS));
        img.setText(persona.get(DBhelper.PERSONAL_IMG));
        imageLoader.DisplayImage(persona.get(DBhelper.PERSONAL_IMG), thumbImg);
        return vi;
    }
}

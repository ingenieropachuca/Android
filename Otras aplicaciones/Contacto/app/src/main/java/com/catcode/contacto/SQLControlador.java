package com.catcode.contacto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;



public class SQLControlador {
    private DBhelper dbhelper;
    private Context context;
    private SQLiteDatabase database;

    public SQLControlador(Context context) {
        this.context = context;
    }

    public SQLControlador abriBD() throws SQLException{
        dbhelper = new DBhelper(context);
        database = dbhelper.getWritableDatabase();
        return this;
    }

    public void cerrarBD(){
        dbhelper.close();
    }

    public void insertarDatos(String nombre, String apellido, String ciudad, String telefono, String email){
        ContentValues cv = new ContentValues();
        cv.put(DBhelper.CONTACTOS_NOMBRE,nombre);
        cv.put(DBhelper.CONTACTOS_APELLIDO,apellido);
        cv.put(DBhelper.CONTACTOS_CIUDAD,ciudad);
        cv.put(DBhelper.CONTACTOS_TELEFONO,telefono);
        cv.put(DBhelper.CONTACTOS_EMAIL,email);
        database.insert(DBhelper.TABLE_NAME, null, cv);
    }


    public void borrarDatos(long contactos_id){
        database.delete(DBhelper.TABLE_NAME, DBhelper.CONTACTOS_ID
                +" = " + contactos_id, null);
    }

    public int actualizarDatos(long contactos_id,String nombre, String apellido, String ciudad, String telefono, String email){
        ContentValues cv = new ContentValues();
        cv.put(DBhelper.CONTACTOS_NOMBRE,nombre);
        cv.put(DBhelper.CONTACTOS_APELLIDO,apellido);
        cv.put(DBhelper.CONTACTOS_CIUDAD,ciudad);
        cv.put(DBhelper.CONTACTOS_TELEFONO,telefono);
        cv.put(DBhelper.CONTACTOS_EMAIL,email);

        int i = database.update(DBhelper.TABLE_NAME, cv,
                DBhelper.CONTACTOS_ID + " = " + contactos_id, null);
        return i;
    }

    public Cursor leerDatos(){
        String[] col = new String[]{
                DBhelper.CONTACTOS_ID,
                DBhelper.CONTACTOS_NOMBRE,
                DBhelper.CONTACTOS_APELLIDO,
                DBhelper.CONTACTOS_CIUDAD,
                DBhelper.CONTACTOS_TELEFONO,
                DBhelper.CONTACTOS_EMAIL
        };
        Cursor c = database.query(DBhelper.TABLE_NAME, col, null, null, null, null, null);
        if(c != null){
            c.moveToFirst();
        }
        return c;
    }
}

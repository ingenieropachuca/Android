package com.catcode.contacto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper{
    //informacion de tabla


    public static final String TABLE_NAME = "myContactsTable";
    public static final String CONTACTOS_ID = "_id";
    public static final String CONTACTOS_NOMBRE = "nombre";
    public static final String CONTACTOS_APELLIDO = "apellido";
    public static final String CONTACTOS_CIUDAD = "ciudad";
    public static final String CONTACTOS_TELEFONO = "telefono";
    public static final String CONTACTOS_EMAIL = "email";

    //informacion de BD
    static final String BD_NAME = "CONTACTOSBD";
    static final int BD_VERSION = 4;

    //sentencias Create

    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME + " ("
            +CONTACTOS_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
            +CONTACTOS_NOMBRE+ " TEXT NOT NULL, "
            +CONTACTOS_APELLIDO+ " TEXT NOT NULL, "
            +CONTACTOS_CIUDAD+ " TEXT NOT NULL, "
            +CONTACTOS_TELEFONO+ " TEXT NOT NULL, "
            +CONTACTOS_EMAIL+ " TEXT NOT NULL);";


    public DBhelper(Context context) {
        super(context, BD_NAME, null, BD_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
}

package com.example.realestate.Sqldata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.realestate.Model.REST.Properties.Properties;
import com.google.gson.Gson;
//import com.sis.shasha.ApiWork.ResponsesGetAllCategories.GetAllCategories;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;
    //Tables
    public static final String DB_NAME = "PorAquird";
    public static final int DB_VERSION = 1;
    // Table columns
    public static final String TABLE_Categories = "MyFavourit";
    public static final String KEY_ID = "id";
    public static final String KEY_STRING = "String";
    public Context context;


    // database version

    //Constructer
    public DataBaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context= context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_NEVERMIND = "CREATE TABLE  " + TABLE_Categories + "( " + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_STRING + " Text Not Null)";
        db.execSQL(CREATE_TABLE_NEVERMIND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Categories);
        onCreate(db);
    }


    public long INSERT_Channels(Properties properties) {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, properties.getId());
        contentValues.put(KEY_STRING, fromPropertyObjectToJsonStrin(properties));
        return db.insert(TABLE_Categories, null, contentValues);

    }

    public ArrayList<Properties> ViewData() {
        ArrayList<Properties> propertiesArrayList = new ArrayList<>();
        db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * from " + TABLE_Categories, null);

        if (c.moveToFirst()) {
            do {
                String id = c.getString(0);
                String data_sting = c.getString(1);


                Gson gson = new Gson();
                Properties properties = gson.fromJson(data_sting, Properties.class);

                propertiesArrayList.add(properties);


            } while (c.moveToNext());
        }


        return propertiesArrayList;
    }

    public Integer delete(String sid) {
        db = this.getWritableDatabase();
        return db.delete(TABLE_Categories, "id=?", new String[]{String.valueOf(sid)});
    }
    private String fromPropertyObjectToJsonStrin(Properties properties) {
        Gson gson = new Gson();
        String json = gson.toJson(properties);
        return json;
    }

}

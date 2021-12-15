package com.example.homebudgetusingroomdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {

    private  static final  String dbname ="logindata.db";

    public  database (@Nullable Context context){
        super(context,dbname,null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String q = "create table entries (_id integer primary key autoincrement,username text, password text)";
        sqLiteDatabase.execSQL(q);  //  initialising databse
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists entries");
        onCreate(sqLiteDatabase);
    }

    public boolean insert_data(String name, String pass) { // function to insert values in databse
        SQLiteDatabase sb = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("username", name);
        c.put("password", pass);
        long r = sb.insert("entries", null, c);
        if (r == -1) { // will  return negative r if insertiion fails
            return false;
        }
        return true;
    }

    public Cursor getinfo() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from entries", null);
        return cursor;
    }
}

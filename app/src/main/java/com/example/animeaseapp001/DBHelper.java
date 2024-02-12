package com.example.animeaseapp001;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "users";

    public static final String USER_NAME = "userName";
    public static final String USER_PASS = "password";
    public static final String USER_MAIL = "mail";

    public DBHelper(@Nullable Context context) {
        super(context, "studentsDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String s="CREATE TABLE "+TABLE_NAME+" (";
        s+=USER_NAME+" TEXT, ";
        s+=USER_PASS+" TEXT, ";
        s+=USER_MAIL+" TEXT, ";
        db.execSQL(s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String st = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        db.execSQL(st);
        onCreate(db);
    }

}

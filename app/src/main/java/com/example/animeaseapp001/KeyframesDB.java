package com.example.animeaseapp001;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class KeyframesDB extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "keyframes";
    public static final String TRANSLATION_X = "translationX";
    public static final String TRANSLATION_Y = "translationY";
    public static final String ROTATION_Y = "rotationY";
    public static final String ALPHA = "alpha";

    public KeyframesDB(@Nullable Context context) {
        super(context, "studentsDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String s="CREATE TABLE "+TABLE_NAME+" (";
        s+=;
        db.execSQL(s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String st = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        db.execSQL(st);
        onCreate(db);
    }

}

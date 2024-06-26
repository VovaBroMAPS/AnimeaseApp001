package com.example.animeaseapp001;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class KeyframesDB extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "keyframes";
    public static final String POINT_IN_TIME = "pointInTime";
    public static final String TRANSLATION_X = "translationX";
    public static final String TRANSLATION_Y = "translationY";
    public static final String ROTATION_Y = "rotationY";
    public static final String ALPHA = "alpha";
    public static final String INTERPOLATOR = "interpolator";

    public KeyframesDB(@Nullable Context context) {
        super(context, "keyframesDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String s="CREATE TABLE "+TABLE_NAME+" (";
        s+=POINT_IN_TIME+" TEXT, ";
        s+=TRANSLATION_X+" TEXT, ";
        s+=TRANSLATION_Y+" TEXT, ";
        s+=ROTATION_Y+" TEXT, ";
        s+=ALPHA+" TEXT, ";
        s+=INTERPOLATOR+" TEXT)";
        db.execSQL(s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String st = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        db.execSQL(st);
        onCreate(db);
    }

}

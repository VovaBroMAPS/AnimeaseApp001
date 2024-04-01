package com.example.animeaseapp001;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BackgroundImageDB extends SQLiteOpenHelper {

    public static final String BACKGROUND_TABLE = "background images";
    public static final String IMAGE_ID = "id";
    public static final String IMAGE_URI = "uri";

    private static final String DATABASE_NAME = "background_images_db";
    private static final int DATABASE_VERSION = 1;


    public BackgroundImageDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " +
                BACKGROUND_TABLE + "(" +
                IMAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                IMAGE_URI + " TEXT" +
                ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BACKGROUND_TABLE);
        onCreate(db);
    }

}

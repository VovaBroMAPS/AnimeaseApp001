package com.example.animeaseapp001;





import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;


import androidx.annotation.Nullable;



public class ProjectsDB extends SQLiteOpenHelper {

    public static final String PROJECTS_TABLE = "projects";
    public static final String PROJ_ID = "id";
    public static final String PROJ_NAME = "name";
    public static final String PROJ_RESOLUTION_WIDTH = "resWidth";
    public static final String PROJ_RESOLUTION_HEIGHT = "resHeight";
    public static final String PROJ_FRAMERATE = "framerate";
    public static final String PROJ_BACKGROUND = "background";
    public static final String DURATION = "duration";

    private static final String DATABASE_NAME = "projectsDB.db";
    private static final int DATABASE_VERSION = 1;



    public ProjectsDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String s="CREATE TABLE " +
                PROJECTS_TABLE + "(" +
                PROJ_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                PROJ_NAME + " TEXT," +
                PROJ_RESOLUTION_WIDTH + " INTEGER," +
                PROJ_RESOLUTION_HEIGHT + " INTEGER," +
                PROJ_FRAMERATE + " INTEGER," +
                PROJ_BACKGROUND + " TEXT," +
                DURATION + " INTEGER" + ")";
        db.execSQL(s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PROJECTS_TABLE);
        onCreate(db);
    }

    public long insertProject(String name, int width, int height, int framerate, Uri backgroundUri, long duration) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PROJ_NAME, name);
        values.put(PROJ_RESOLUTION_WIDTH, width);
        values.put(PROJ_RESOLUTION_HEIGHT, height);
        values.put(PROJ_FRAMERATE, framerate);
        values.put(ProjectsDB.PROJ_BACKGROUND, backgroundUri.toString());
        values.put(DURATION, duration);
        long newRowId = db.insert(PROJECTS_TABLE, null, values);
        db.close();
        return newRowId;
    }

}

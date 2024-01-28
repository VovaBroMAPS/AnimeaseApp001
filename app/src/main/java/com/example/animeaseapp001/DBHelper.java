package com.example.animeaseapp001;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "students";

    public static final String STUD_FNAME = "fName";
    public static final String STUD_LNAME = "lName";
    public static final String STUD_PASS = "password";
    public static final String STUD_YEAR = "birthYear";
    public static final String STUD_PHONE = "phoneNumber";
    public static final String STUD_MAIL = "mail";
    public static final String STUD_SEX = "sex";

    public DBHelper(@Nullable Context context) {
        super(context, "studentsDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String s="CREATE TABLE "+TABLE_NAME+" (";
        s+=STUD_FNAME+" TEXT, ";
        s+=STUD_LNAME+" TEXT, ";
        s+=STUD_PASS+" TEXT, ";
        s+=STUD_YEAR+" TEXT, ";
        s+=STUD_PHONE+" TEXT, ";
        s+=STUD_MAIL+" TEXT, ";
        s+=STUD_SEX+" TEXT);";
        db.execSQL(s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String st = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        db.execSQL(st);
        onCreate(db);
    }

}

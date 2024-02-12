package com.example.animeaseapp001;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    TextView tvRegister;
    EditText etRegisterUsername, etRegisterEmail, etRegisterPassword, etRegisterConfirmPassword;
    Button btnRegister;
    DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tvRegister = findViewById(R.id.tvRegister);
        etRegisterUsername = findViewById(R.id.etRegisterUsername);
        etRegisterEmail = findViewById(R.id.etRegisterEmail);
        etRegisterPassword = findViewById(R.id.etRegisterPassword);
        etRegisterConfirmPassword = findViewById(R.id.etRegisterConfirmPassword);
    }
    private boolean isFound(String email, String password){
        dbHelper = new DBHelper(this);
        db = dbHelper.getReadableDatabase();
        Cursor c = db.query(DBHelper.TABLE_NAME,null,null,null,null,null,null,null);
        c.moveToFirst();
        int indexEmail = c.getColumnIndex(DBHelper.USER_MAIL);
        int indexPassword = c.getColumnIndex(DBHelper.USER_PASS);
        while(c.isAfterLast() == false){
            if(c.getString(indexEmail).equals(email))
        }
    }
}
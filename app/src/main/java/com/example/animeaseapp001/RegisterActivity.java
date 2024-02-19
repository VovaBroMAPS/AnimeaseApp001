package com.example.animeaseapp001;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.AbsListView;
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

    public boolean isFound(String email, String password) {
        dbHelper = new DBHelper(RegisterActivity.this);
        db = dbHelper.getReadableDatabase();
        Cursor c = db.query(DBHelper.TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();
        int indexEmail = c.getColumnIndex(DBHelper.USER_MAIL);
        int indexPassword = c.getColumnIndex(DBHelper.USER_PASS);
        while(c.isAfterLast()==false){
            if(c.getString(indexEmail).equals(email) && c.getString((indexPassword)).equals(password)){
                return true;
            }
            c.moveToNext();
        }
        c.close();
        db.close();
        return false;
    }
    public void showMessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage("User already exists, try login instead?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("NO",null);
        }
    }
}
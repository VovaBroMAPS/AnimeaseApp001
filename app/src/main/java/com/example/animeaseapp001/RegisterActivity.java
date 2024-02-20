package com.example.animeaseapp001;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
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
    String[] userInfoArray = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tvRegister = findViewById(R.id.tvRegister);
        etRegisterUsername = findViewById(R.id.etRegisterUsername);
        etRegisterEmail = findViewById(R.id.etRegisterEmail);
        etRegisterPassword = findViewById(R.id.etRegisterPassword);
        etRegisterConfirmPassword = findViewById(R.id.etRegisterConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFound(etRegisterEmail.getText().toString(), etRegisterPassword.getText().toString())) {
                    showMessage();
                } else {
                    insertUser();
                    Intent intent = new Intent(RegisterActivity.this,HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }



    public boolean isFound(String email, String password) {
        dbHelper = new DBHelper(RegisterActivity.this);
        db = dbHelper.getReadableDatabase();
        Cursor c = db.query(DBHelper.TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();
        int indexEmail = c.getColumnIndex(DBHelper.USER_MAIL);
        int indexPassword = c.getColumnIndex(DBHelper.USER_PASS);
        while(!c.isAfterLast()){
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.CustomAlertDialogAnimation);
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

    private void insertUser(){
        userInfoArray[0]=etRegisterUsername.getText().toString();
        userInfoArray[1]=etRegisterPassword.getText().toString();
        userInfoArray[2]=etRegisterEmail.getText().toString();

        ContentValues cv=new ContentValues();
        dbHelper=new DBHelper(RegisterActivity.this);
        db=dbHelper.getWritableDatabase();

        cv.put(DBHelper.USER_NAME,userInfoArray[0]);
        cv.put(DBHelper.USER_PASS,userInfoArray[1]);
        cv.put(DBHelper.USER_MAIL,userInfoArray[2]);

        db.insert(DBHelper.TABLE_NAME,null,cv);
        db.close();

        etRegisterUsername.setText("");
        etRegisterPassword.setText("");
        etRegisterConfirmPassword.setText("");
        etRegisterEmail.setText("");

    }
}
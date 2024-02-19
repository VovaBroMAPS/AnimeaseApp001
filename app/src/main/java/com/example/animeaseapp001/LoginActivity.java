package com.example.animeaseapp001;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView tvLogin;
    EditText etLoginEmail, etLoginPassword;
    Button btnLogin;
    DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvLogin = findViewById(R.id.tvLogin);
        etLoginEmail = findViewById(R.id.etLoginEmail);
        etLoginPassword = findViewById(R.id.etLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFound(etLoginEmail.getText().toString(),etLoginPassword.getText().toString())){
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
                else showMessage();
            }
        });
    }

    private void showMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomAlertDialogAnimation);
        builder.setTitle("Fail");
        builder.setMessage("Incorrect email/password, make sure the details are correct and try again.");
        builder.setIcon(R.drawable.ic_msg_failed);
        builder.setNeutralButton("OK",null);
        builder.setCancelable(false);
        builder.show();
    }

    public boolean isFound(String email, String password) {
        dbHelper = new DBHelper(LoginActivity.this);
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

}
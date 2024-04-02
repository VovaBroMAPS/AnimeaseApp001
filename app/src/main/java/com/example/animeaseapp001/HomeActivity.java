package com.example.animeaseapp001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button btnHomeProjects, btnHomeDrawStudio, btnHomeHowToUse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnHomeProjects= findViewById(R.id.btnHomeProjects);
        btnHomeDrawStudio = findViewById(R.id.btnHomeDrawStudio);
        btnHomeHowToUse = findViewById(R.id.btnHomeHowToUse);

        btnHomeProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ProjectListActivity.class);
                startActivity(intent);
            }
        });
    }
}
package com.example.animeaseapp001;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ProjectListActivity extends AppCompatActivity {

    Button btnNewProject;
    ListView lvProjects;
    FragmentContainerView fcvProjectProperties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);

        btnNewProject = findViewById(R.id.btnNewProject);
        lvProjects = findViewById(R.id.lvProjects);
        fcvProjectProperties = findViewById(R.id.fcvProjectProperties);

        btnNewProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProjectListActivity.this, ProjectCreateActivity.class);
                startActivity(intent);
            }
        });
    }
}
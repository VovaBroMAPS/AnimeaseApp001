package com.example.animeaseapp001;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Permission;

public class ProjectCreateActivity extends AppCompatActivity {
    TextView tvCreateProject, tvBackgroundImage;
    EditText etProjectName, etProjectDuration;
    Button btnCreateProject;
    Spinner spinnerResolution, spinnerFramerate;
    ImageView ivSelectImage;

    private static final int PICK_IMAGE_REQUEST = 1;
    Uri imageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_create);

        tvCreateProject= findViewById(R.id.tvCreateProject);
        tvBackgroundImage= findViewById(R.id.tvBackgroundImage);
        etProjectName= findViewById(R.id.etProjectName);
        etProjectDuration= findViewById(R.id.etProjectDuration);
        btnCreateProject= findViewById(R.id.btnCreateProject);
        spinnerResolution= findViewById(R.id.spinnerResolution);
        spinnerFramerate=findViewById(R.id.spinnerFramerate);
        ivSelectImage = findViewById(R.id.ivSelectImage);

        spinnerResolution.setSelection(0);
        spinnerFramerate.setSelection(0);

        ivSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        btnCreateProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buildProject();
            }
        });

        etProjectDuration.setFilters(new InputFilter[] { new InputFilter.LengthFilter(8) }); // Limit input length


        String[] resolutionOptions = {"1920x1080", "1280x720", "854x480", "640x360"};

        String[] framerateOptions = {"60", "30", "24", "12"};

        ArrayAdapter<String> resolutionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, resolutionOptions);
        ArrayAdapter<String> framerateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, framerateOptions);

        resolutionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        framerateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerResolution.setAdapter(resolutionAdapter);
        spinnerFramerate.setAdapter(framerateAdapter);

    }

    private void buildProject() {
        String durationText = etProjectDuration.getText().toString();
        String[] parts = durationText.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);

        long projectDuration = (hours * 3600 + minutes * 60 + seconds) * 1000; // project duration in milliseconds

        String projectName = etProjectName.getText().toString(); // name of project
        String projectResolution = spinnerResolution.getSelectedItem().toString();
        String[] projectDimensions = projectResolution.split("x");
        int projectWidth = Integer.parseInt(projectDimensions[0]); // width of project
        int projectHeight = Integer.parseInt(projectDimensions[1]); // height of project

        int projectFramerate = Integer.parseInt(spinnerFramerate.getSelectedItem().toString());

        if(projectDuration>0 && !projectName.equals("")) {
            ProjectsDB projectsDB = new ProjectsDB(this);
            long newRowId = projectsDB.insertProject(projectName, projectWidth, projectHeight, projectFramerate, imageUri, projectDuration);

            // Check if the insertion was successful
            if (newRowId != -1) {
                Toast.makeText(this, "Project properties inserted successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProjectCreateActivity.this, ProjectListActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Failed to insert properties", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private static final int REQUEST_CODE_STORAGE_PERMISSION = 1;
    private static final int REQUEST_CODE_PICK_IMAGE = 2;

    private void openGallery() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_STORAGE_PERMISSION);
        } else {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, REQUEST_CODE_PICK_IMAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_STORAGE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, open the gallery
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, REQUEST_CODE_PICK_IMAGE);
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomAlertDialogAnimation);
                builder.setTitle("Fail");
                builder.setMessage("Declining permission to access external content may result in certain features being unavailable. Are you certain you prefer not to grant this permission?");
                builder.setIcon(R.drawable.ic_msg_failed);
                builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        openGallery();
                    }
                });
                builder.setNegativeButton("Deny",null);
                builder.setCancelable(false);
                builder.show();
            }
        }
    }


    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
        }
    }

}
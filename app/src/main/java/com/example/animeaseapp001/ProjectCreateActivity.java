package com.example.animeaseapp001;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class ProjectCreateActivity extends AppCompatActivity {
    TextView tvCreateProject, tvBackgroundImage;
    EditText etProjectName, etProjectDuration;
    Button btnCreateProject;
    Spinner spinnerResolution, spinnerFramerate;
    ImageView ivSelectImage;
    private static final int PICK_IMAGE_REQUEST = 1;

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

        ivSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        etProjectDuration.setFilters(new InputFilter[] { new InputFilter.LengthFilter(8) }); // Limit input length
        etProjectDuration.addTextChangedListener(new TextWatcher() {
            private static final String DURATION_PATTERN = "^([0-9]{2}):([0-5][0-9]):([0-5][0-9])$";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();
                if (!input.matches(DURATION_PATTERN)) {
                    etProjectDuration.setText(input.substring(0, input.length() - 1));
                    etProjectDuration.setSelection(etProjectDuration.getText().length());
                }
            }
        });


        String[] resolutionOptions = {"1920x1080", "1280x720", "854x480", "640x360"};

        String[] framerateOptions = {"60", "30", "24", "12"};

        ArrayAdapter<String> resolutionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, resolutionOptions);
        ArrayAdapter<String> framerateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, framerateOptions);

        resolutionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        framerateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerResolution.setAdapter(resolutionAdapter);
        spinnerFramerate.setAdapter(framerateAdapter);

    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            // Handle the selected image URI here
        }
    }
}
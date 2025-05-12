package com.hcmus.information.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hcmus.information.R;
import com.hcmus.information.dto.UserDTO;

public class DetailActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        UserDTO user = (UserDTO) getIntent().getSerializableExtra("user");
        TextView name = findViewById(R.id.nameText);
        TextView description = findViewById(R.id.descriptionText);
        TextView grades = findViewById(R.id.gradesText);

        name.setText(user.getName());
        description.setText(user.getDescription());
        grades.setText(String.format("GPA: %.2f", user.getGrades()));
        Log.d("Detail", user.getName());
        Log.d("Detail", user.getDescription());
        Log.d("Detail", String.format("GPA: %.2f", user.getGrades()));
    }
}

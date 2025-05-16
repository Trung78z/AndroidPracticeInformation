package com.hcmus.information.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hcmus.information.R;
import com.hcmus.information.dao.UserInfoDao;
import com.hcmus.information.dto.UserInfoDTO;
import com.hcmus.information.enums.Gender;
import com.hcmus.information.model.UserInfo;
import com.hcmus.information.repositories.AppDatabase;

import java.util.concurrent.Executors;

public class DetailActivity extends AppCompatActivity {
    private ImageView profilePicture;
    private TextView fullName, email, mssvText, fullNameText, genderText, dobText,
            majorText, phoneNumberText, locationText, statusText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_test);

        profilePicture = findViewById(R.id.profile_picture);
        fullName = findViewById(R.id.full_name);
        email = findViewById(R.id.email);
        mssvText = findViewById(R.id.mssv_text);
        fullNameText = findViewById(R.id.full_name_text);
        genderText = findViewById(R.id.gender);
        dobText = findViewById(R.id.dob_text);
        majorText = findViewById(R.id.major);
        phoneNumberText = findViewById(R.id.phone_number_text);
        locationText = findViewById(R.id.location_text);
        statusText = findViewById(R.id.status_text);

        ImageButton backButton = findViewById(R.id.goBack);
        backButton.setOnClickListener(v -> finish());

        String studentId = getIntent().getStringExtra("studentId");

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detailUser),
//                (v, insets) -> {
//                    Insets systemBars = insets.getInsets(
//                            WindowInsetsCompat.Type.systemBars());
//                    v.setPadding(systemBars.left, systemBars.top,
//                            systemBars.right, systemBars.bottom);
//                    return insets;
//                });

        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        UserInfoDao userInfoDao = db.userInfoDao();

        Executors.newSingleThreadExecutor().execute(() -> {
            UserInfo user = userInfoDao.getByStudentId(studentId);
            runOnUiThread(() -> bindData(UserInfoDTO.fromEntity(user)));
        });
    }

    public void bindData(UserInfoDTO user) {
        profilePicture.setImageResource(user.getImage());
        fullName.setText(user.getFullName());
        email.setText(user.getEmail());
        mssvText.setText(user.getStudentId());
        fullNameText.setText(user.getFullName());
        if (user.getGender() == Gender.FEMALE) {
            genderText.setText("Nữ");
        } else if (user.getGender() == Gender.MALE) {
            genderText.setText("Nam");
        } else {
            genderText.setText("Khác");
        }

        dobText.setText(user.getDob());
        majorText.setText(user.getMajor());
        phoneNumberText.setText(user.getPhone());
        locationText.setText(user.getAddress());
        statusText.setText(user.getStatus());
    }
}

package com.hcmus.information.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hcmus.information.R;
import com.hcmus.information.adapter.StudentAdapter;
import com.hcmus.information.dao.UserInfoDao;
import com.hcmus.information.data.DataRom;
import com.hcmus.information.dto.UserInfoDTO;
import com.hcmus.information.model.UserInfo;
import com.hcmus.information.repositories.AppDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        List<UserInfoDTO> students = new ArrayList<>();
        StudentAdapter adapter = new StudentAdapter(this, students);

        ListView listStudent = findViewById(R.id.list_students);
        ImageButton setDefault = findViewById(R.id.setDefault);
        ImageButton clearAllData = findViewById(R.id.clearData);

        listStudent.setAdapter(adapter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main),
                (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top,
                            systemBars.right, systemBars.bottom);
                    return insets;
                });
        listStudent.setOnItemClickListener((parent, view, position, id) -> {
            UserInfoDTO selectedUser = adapter.getItem(position);

            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("studentId", selectedUser.getStudentId());
            startActivity(intent);
        });
        SharedPreferences prefs = getSharedPreferences("my_prefs",
                MODE_PRIVATE);
//        prefs.edit().clear().apply();
        boolean isDbInitialized = prefs.getBoolean("db_initialized", false);

        if (!isDbInitialized) {
            this.insertData(prefs, students, adapter);
        }
        this.setList(adapter, students);
        setDefault.setOnClickListener(v -> {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.submit(() -> {
                AppDatabase db = AppDatabase.getInstance(getApplicationContext());
                UserInfoDao userInfoDao = db.userInfoDao();
                userInfoDao.deleteAll();

                students.clear();
                runOnUiThread(adapter::notifyDataSetChanged);

                userInfoDao.insertAll(DataRom.getListUser());
                prefs.edit().putBoolean("db_initialized", true).apply();

                List<UserInfo> users = userInfoDao.getAll();
                students.addAll(users.stream().map(UserInfoDTO::fromEntity).toList());

                runOnUiThread(adapter::notifyDataSetChanged);
            });
        });


        clearAllData.setOnClickListener(v -> {
            clearData(students, adapter);
        });
    }

    private void setList(StudentAdapter adapter, List<UserInfoDTO> students) {
        Executors.newSingleThreadExecutor().execute(() -> {
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            UserInfoDao userInfoDao = db.userInfoDao();
            List<UserInfo> users = userInfoDao.getAll();
            for (UserInfo user : users) {
                students.add(UserInfoDTO.fromEntity(user));
                runOnUiThread(adapter::notifyDataSetChanged);
            }
        });
    }

    private void insertData(SharedPreferences prefs, List<UserInfoDTO> students, StudentAdapter adapter) {
        Executors.newSingleThreadExecutor().execute(() -> {
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            UserInfoDao userInfoDao = db.userInfoDao();
            userInfoDao.insertAll(DataRom.getListUser());

            prefs.edit().putBoolean("db_initialized", true).apply();
        });
    }

    private void clearData(List<UserInfoDTO> students, StudentAdapter adapter) {
        Executors.newSingleThreadExecutor().execute(() -> {
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            UserInfoDao userInfoDao = db.userInfoDao();
            userInfoDao.deleteAll();

            students.clear();
            runOnUiThread(adapter::notifyDataSetChanged);
        });
    }
}
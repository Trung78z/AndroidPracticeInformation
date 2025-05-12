package com.hcmus.information.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hcmus.information.R;
import com.hcmus.information.adapter.StudentAdapter;
import com.hcmus.information.dao.UserDao;
import com.hcmus.information.dto.UserDTO;
import com.hcmus.information.model.User;
import com.hcmus.information.repositories.AppDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        List<UserDTO> students = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            students.add(new UserDTO(R.drawable.user, "Emma Carter", "Excels in Mathematics and debate.", 9.5));
            students.add(new UserDTO(R.drawable.user, "Liam Nguyen", "Passionate about Physics and astronomy.", 8.0));
            students.add(new UserDTO(R.drawable.user, "Sophia Patel", "Creative writer and poetry enthusiast.", 9.0));
            students.add(new UserDTO(R.drawable.user, "Noah Kim", "Skilled coder and robotics team leader.", 9.5));
            students.add(new UserDTO(R.drawable.user, "Olivia Brown", "Dedicated to Biology and environmental science.", 8.5));
            students.add(new UserDTO(R.drawable.user, "Ethan Davis", "History buff and quiz team captain.", 9.0));
            students.add(new UserDTO(R.drawable.user, "Ava Wilson", "異常 in music and orchestra.", 9.0));
            students.add(new UserDTO(R.drawable.user, "Mason Lee", "Avid reader and literature scholar.", 8.5));
            students.add(new UserDTO(R.drawable.user, "Isabella Garcia", "Talented in Chemistry experiments.", 9.0));
            students.add(new UserDTO(R.drawable.user, "Lucas Martinez", "Athlete and statistics enthusiast.", 8.5));
            students.add(new UserDTO(R.drawable.user, "Mia Thompson", "Artistic with a flair for design.", 9.0));
            students.add(new UserDTO(R.drawable.user, "James Anderson", "Future engineer with a love for mechanics.", 9.0));
            students.add(new UserDTO(R.drawable.user, "Charlotte White", "Dedicated to social studies and volunteering.", 8.5));
            students.add(new UserDTO(R.drawable.user, "Benjamin Harris", "Computer science whiz and gamer.", 9.5));
            students.add(new UserDTO(R.drawable.user, "Amelia Clark", "Biology researcher and lab assistant.", 9.5));
            students.add(new UserDTO(R.drawable.user, "Henry Lewis", "Enthusiastic about geography and maps.", 8.0));
            students.add(new UserDTO(R.drawable.user, "Harper Walker", "Drama club star and public speaker.", 9.5));
            students.add(new UserDTO(R.drawable.user, "Alexander Young", "Mathlete and problem-solving champion.", 8.0));
            students.add(new UserDTO(R.drawable.user, "Evelyn Hall", "Passionate about literature and writing.", 9.5));
            students.add(new UserDTO(R.drawable.user, "Daniel Allen", "Science fair winner and innovator.", 9.5));
        }
        StudentAdapter adapter = new StudentAdapter(this, students);

        ListView listStudent = findViewById(R.id.list_students);
        listStudent.setAdapter(adapter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listStudent.setOnItemClickListener((parent, view, position, id) -> {
            UserDTO selectedUser = adapter.getItem(position);

            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("user", selectedUser);
            startActivity(intent);
        });
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
        boolean isDbInitialized = prefs.getBoolean("db_initialized", false);
        UserDao userDao = db.userDao();
        if (!isDbInitialized) {
            Executors.newSingleThreadExecutor().execute(() -> {
                User user = new User();
                user.setUid(1);
                user.setFirstName("John");
                user.setLastName("Doe");
                user.setEmail("trungpspy@gmail.com");
                userDao.insertAll(user);
                prefs.edit().putBoolean("db_initialized", true).apply();
            });
        }
        Executors.newSingleThreadExecutor().execute(() -> {
            List<User> users = userDao.getAll();
            for (User i : users) {
                Log.d("Main", i.toString());
            }
        });
    }
}
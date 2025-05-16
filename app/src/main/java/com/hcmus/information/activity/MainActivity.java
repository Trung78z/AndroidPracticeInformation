package com.hcmus.information.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hcmus.information.R;
import com.hcmus.information.adapter.StudentAdapter;
import com.hcmus.information.dao.UserInfoDao;
import com.hcmus.information.dto.UserInfoDTO;
import com.hcmus.information.enums.Gender;
import com.hcmus.information.model.UserInfo;
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
        List<UserInfoDTO> students = new ArrayList<>();
        StudentAdapter adapter = new StudentAdapter(this, students);

        ListView listStudent = findViewById(R.id.list_students);
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
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        SharedPreferences prefs = getSharedPreferences("my_prefs",
                MODE_PRIVATE);
//        prefs.edit().clear().apply();
        boolean isDbInitialized = prefs.getBoolean("db_initialized", false);
        UserInfoDao userInfoDao = db.userInfoDao();
        if (!isDbInitialized) {
            Executors.newSingleThreadExecutor().execute(() -> {
                List<UserInfo> users = new ArrayList<>();
                users.add(new UserInfo(
                        R.drawable.boy,
                        "Nguyễn Thành Trung",
                        "Trung",
                        "trungpspy@gmail.com",
                        "20200381",
                        Gender.MALE,
                        "20-06-2002",
                        "Điện tử viễn thông",
                        "+84 886506127",
                        "TPHCM, Việt Nam",
                        "Đang học"
                ));
                users.add(new UserInfo(
                        R.drawable.girl,
                        "Lê Thị Minh Anh",
                        "Minh Anh",
                        "minhanh.le@example.com",
                        "20200456",
                        Gender.FEMALE,
                        "15-03-2002",
                        "Công nghệ thông tin",
                        "+84 987654321",
                        "Hà Nội, Việt Nam",
                        "Đang học"
                ));

                users.add(new UserInfo(
                        R.drawable.boy,
                        "Trần Văn Bảo",
                        "Văn Bảo",
                        "vanbao.tran@example.com",
                        "20200567",
                        Gender.MALE,
                        "22-11-2001",
                        "Kỹ thuật điện",
                        "+84 912345678",
                        "Đà Nẵng, Việt Nam",
                        "Đang học"
                ));

                users.add(new UserInfo(
                        R.drawable.girl,
                        "Phạm Thị Cẩm Tú",
                        "Cẩm Tú",
                        "camtu.pham@example.com",
                        "20200678",
                        Gender.FEMALE,
                        "08-09-2002",
                        "Quản trị kinh doanh",
                        "+84 933333333",
                        "Cần Thơ, Việt Nam",
                        "Đang học"
                ));

                users.add(new UserInfo(
                        R.drawable.boy,
                        "Ngô Đức Dũng",
                        "Đức Dũng",
                        "ducdung.ngo@example.com",
                        "20200789",
                        Gender.MALE,
                        "30-01-2001",
                        "Kỹ thuật cơ khí",
                        "+84 944444444",
                        "Hải Phòng, Việt Nam",
                        "Đã tốt nghiệp"
                ));

                users.add(new UserInfo(
                        R.drawable.girl,
                        "Vũ Thị Eo Éo",
                        "Eo Éo",
                        "eo.eo@example.com",
                        "20200890",
                        Gender.FEMALE,
                        "12-12-2000",
                        "Ngôn ngữ Anh",
                        "+84 955555555",
                        "Nha Trang, Việt Nam",
                        "Bảo lưu"
                ));
                users.add(new UserInfo(
                        R.drawable.boy,
                        "Nguyễn Văn Hùng",
                        "Văn Hùng",
                        "hung.nguyen@example.com",
                        "20200901",
                        Gender.MALE,
                        "10-10-2000",
                        "Công nghệ phần mềm",
                        "+84 911111111",
                        "TP.HCM, Việt Nam",
                        "Đang học"
                ));

                users.add(new UserInfo(
                        R.drawable.girl,
                        "Lý Thị Thu Trang",
                        "Thu Trang",
                        "trang.ly@example.com",
                        "20200902",
                        Gender.FEMALE,
                        "21-06-2001",
                        "Kinh tế đối ngoại",
                        "+84 922222222",
                        "Huế, Việt Nam",
                        "Đã tốt nghiệp"
                ));


                userInfoDao.insertAll(users);
                prefs.edit().putBoolean("db_initialized", true).apply();

            });
        }
        this.setList(userInfoDao, students, adapter);
    }

    private void setList(UserInfoDao userInfoDao, List<UserInfoDTO> students,
                         StudentAdapter adapter) {
        Executors.newSingleThreadExecutor().execute(() -> {
            List<UserInfo> users = userInfoDao.getAll();
            for (UserInfo user : users) {
                students.add(UserInfoDTO.fromEntity(user));
                runOnUiThread(adapter::notifyDataSetChanged);
            }
        });
    }
}
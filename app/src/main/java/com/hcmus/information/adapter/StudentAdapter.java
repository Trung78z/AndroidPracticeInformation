package com.hcmus.information.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hcmus.information.R;
import com.hcmus.information.activity.DetailActivity;
import com.hcmus.information.dao.UserInfoDao;
import com.hcmus.information.dto.UserInfoDTO;
import com.hcmus.information.repositories.AppDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StudentAdapter extends BaseAdapter {
    private List<UserInfoDTO> users;
    private Context context;


    public StudentAdapter(Context context, List<UserInfoDTO> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public UserInfoDTO getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_list_item, parent, false);
        }

        UserInfoDTO user = getItem(position);

        if (user == null) {
            return new View(parent.getContext());
        }

        TextView studentName = convertView.findViewById(R.id.studentName);
        studentName.setText(user.getFullName());

        ImageView studentImage = convertView.findViewById(R.id.studentImage);
        studentImage.setImageResource(user.getImage());
        TextView studentIdMajor = convertView.findViewById(R.id.studentIdMajor);
        studentIdMajor.setText(String.format("%s - %s",
                user.getStudentId(),
                user.getMajor()));
        TextView studentStatus = convertView.findViewById(R.id.studentStatus);
        studentStatus.setText(user.getStatus());

        ImageButton removeData = convertView.findViewById(R.id.removeData);
        LinearLayout main_info = convertView.findViewById(R.id.main_info);
        main_info.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("studentId", user.getStudentId());
            context.startActivity(intent);
        });
        removeData.setOnClickListener(v -> {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                AppDatabase db = AppDatabase.getInstance(context.getApplicationContext());
                UserInfoDao userInfoDao = db.userInfoDao();

                userInfoDao.deleteById(user.getStudentId());

                users.removeIf(u -> u.getStudentId().equals(user.getStudentId()));

                new Handler(Looper.getMainLooper()).post(() -> {
                    notifyDataSetChanged();
                    Toast.makeText(context, "Đã xóa " + user.getFullName(), Toast.LENGTH_SHORT).show();
                });
            });
        });
        return convertView;
    }
}

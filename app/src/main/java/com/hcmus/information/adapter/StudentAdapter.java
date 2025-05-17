package com.hcmus.information.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.hcmus.information.activity.MainActivity;
import com.hcmus.information.dto.UserInfoDTO;

import java.util.List;

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

        // set dữ liệu vào các view trong item
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
            // Xóa phần tử tại vị trí hiện tại
            users.remove(position);
            notifyDataSetChanged();
            Toast.makeText(context, "Đã xóa " + user.getFullName(), Toast.LENGTH_SHORT).show();
        });

        return convertView;
    }
}

package com.hcmus.information.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hcmus.information.R;
import com.hcmus.information.dto.UserInfoDTO;
import com.hcmus.information.repositories.AppDatabase;

import java.util.List;
import java.util.concurrent.Executors;

public class StudentAdapter extends ArrayAdapter<UserInfoDTO> {
    public StudentAdapter(Context context, List<UserInfoDTO> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // ViewHolder pattern for better performance
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.activity_list_item, parent, false);

            holder = new ViewHolder();
            holder.studentImage = convertView.findViewById(R.id.studentImage);
            holder.studentName = convertView.findViewById(R.id.studentName);
            holder.studentIdMajor = convertView.findViewById(R.id.studentIdMajor);
            holder.studentStatus = convertView.findViewById(R.id.studentStatus);

            holder.removeDate = convertView.findViewById(R.id.removeDate);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Get current student item
        UserInfoDTO currentStudent = getItem(position);

        // Set data to views
        if (currentStudent != null) {
            // Set image (use placeholder if no image resource)
            int imageRes = currentStudent.getImage() != 0
                    ? currentStudent.getImage()
                    : R.drawable.ic_person_placeholder;
            holder.studentImage.setImageResource(imageRes);

            // Set student name
            holder.studentName.setText(currentStudent.getFullName());

            // Set student ID and major
            String idMajor = String.format("%s - %s",
                    currentStudent.getStudentId(),
                    currentStudent.getMajor());
            holder.studentIdMajor.setText(idMajor);

            // Set student status with icon if needed
            holder.studentStatus.setText(currentStudent.getStatus());
            holder.removeDate.setOnClickListener(v -> {
                Executors.newSingleThreadExecutor().execute(() -> {
                    AppDatabase db = AppDatabase.getInstance(getContext());
                    db.userInfoDao().deleteById(currentStudent.getStudentId());

                    ((Activity) getContext()).runOnUiThread(() -> {
                        remove(currentStudent);
                        notifyDataSetChanged();
                    });
                });
            });
        }

        return convertView;
    }

    // ViewHolder class to improve scrolling performance
    private static class ViewHolder {
        ImageView studentImage;
        TextView studentName;
        TextView studentIdMajor;
        TextView studentStatus;
        ImageView removeDate;
    }
}
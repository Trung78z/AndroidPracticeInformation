package com.hcmus.information.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hcmus.information.R;
import com.hcmus.information.dto.UserDTO;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<UserDTO> {
    public StudentAdapter(Context context, List<UserDTO> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_item, container, false);
        }

        UserDTO currentItem = getItem(position);

        ImageView studentImage = convertView.findViewById(R.id.studentImage);
        TextView studentName = convertView.findViewById(R.id.studentName);
        TextView studentDescription = convertView.findViewById(R.id.studentDescription);
        TextView studentPrice = convertView.findViewById(R.id.studentGrades);

        studentImage.setImageResource(currentItem.getImage());
        studentName.setText(currentItem.getName());
        studentDescription.setText(currentItem.getDescription());
        studentPrice.setText(String.format("GPA: %.2f", currentItem.getGrades()));

        return convertView;
    }
}

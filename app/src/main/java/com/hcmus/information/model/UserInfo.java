package com.hcmus.information.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.hcmus.information.enums.Gender;

import java.io.Serializable;

import lombok.Data;

@Entity(tableName = "user_info")
@Data
public class UserInfo implements Serializable {
    private int image;
    private String fullName;
    private String shortName;
    private String email;
    @PrimaryKey
    @NonNull
    private String studentId;
    private Gender gender;
    private String dob;
    private String major;
    private String phone;
    private String address;
    private String status;

    public UserInfo(int image, String fullName, String shortName, String email,
                    @NonNull String studentId, Gender gender, String dob,
                    String major, String phone, String address, String status) {
        this.image = image;
        this.fullName = fullName;
        this.shortName = shortName;
        this.email = email;
        this.studentId = studentId;
        this.gender = gender;
        this.dob = dob;
        this.major = major;
        this.phone = phone;
        this.address = address;
        this.status = status;
    }
}

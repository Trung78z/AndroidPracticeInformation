package com.hcmus.information.dto;


import com.hcmus.information.enums.Gender;
import com.hcmus.information.model.UserInfo;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserInfoDTO implements Serializable {
    private int image;
    private String fullName;
    private String shortName;
    private String email;
    private String studentId;
    private Gender gender;
    private String dob;
    private String major;
    private String phone;
    private String address;
    private String status;

    public UserInfoDTO(int image, String fullName, String shortName,
                       String email, String studentId, Gender gender,
                       String dob, String major, String phone, String address,
                       String status) {
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

    public static UserInfoDTO fromEntity(UserInfo userInfo) {
        return new UserInfoDTO(
                userInfo.getImage(),
                userInfo.getFullName(),
                userInfo.getShortName(),
                userInfo.getEmail(),
                userInfo.getStudentId(),
                userInfo.getGender(),
                userInfo.getDob(),
                userInfo.getMajor(),
                userInfo.getPhone(),
                userInfo.getAddress(),
                userInfo.getStatus()
        );
    }
}

package com.hcmus.information.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserDTO implements Serializable {

    private int image;
    private String name;
    private String description;
    private Double grades;

    public UserDTO(Integer image, String name, String description, Double grades) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.grades = grades;
    }

}

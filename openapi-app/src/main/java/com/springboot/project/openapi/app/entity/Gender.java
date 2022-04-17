package com.springboot.project.openapi.app.entity;

public enum Gender {

    M, F;

    public static Gender toGender(String genderStr) {
        return Gender.valueOf(genderStr);
    }

}

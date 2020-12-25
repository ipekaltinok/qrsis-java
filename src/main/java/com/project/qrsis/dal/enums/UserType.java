package com.project.qrsis.dal.enums;

import lombok.Getter;

public enum UserType {

    TEACHER("teacher"),
    STUDENT("student");

    @Getter
    String value;

    UserType(String value) {
        this.value = value;
    }


}

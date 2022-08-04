package com.company.course.enums;

public enum ErrorCodeEnum {

    COURSE_NOT_FOUND(1001,"Can not find course given id"),
    VALIDATION_ERROR(1002," is not valid"), UNKNOWN_ERROR(1003,"Unknown error");

    private int code;

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ErrorCodeEnum(int code, String message) {
        this.code=code;
        this.message=message;
    }
}

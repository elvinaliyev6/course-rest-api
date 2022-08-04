package com.company.course.exception;

import com.company.course.enums.ErrorCodeEnum;

public class CourseNotFoundException extends RuntimeException{

    public CourseNotFoundException(ErrorCodeEnum errorCodeEnum) {
        super(ErrorCodeEnum.COURSE_NOT_FOUND.getMessage());
    }
}

package com.company.course.controller;

import com.company.course.dto.response.ErrorResponse;
import com.company.course.enums.ErrorCodeEnum;
import com.company.course.exception.CourseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(CourseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleCourseNotFoundException(CourseNotFoundException e){
        return ErrorResponse.builder()
                .code(ErrorCodeEnum.COURSE_NOT_FOUND.getCode())
                .message(ErrorCodeEnum.COURSE_NOT_FOUND.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInputParam(MethodArgumentTypeMismatchException e){
        String paramName=e.getParameter().getParameterName();
        return ErrorResponse.builder()
                .code(ErrorCodeEnum.VALIDATION_ERROR.getCode())
                .message(paramName+ErrorCodeEnum.VALIDATION_ERROR.getMessage())
                .build();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidation(MethodArgumentNotValidException e){
      List<String> fieldErrorNamesList=e.getBindingResult().getFieldErrors().stream().map(fieldError ->fieldErrorName(fieldError))
              .collect(Collectors.toList());

      return ErrorResponse.builder()
              .code(ErrorCodeEnum.VALIDATION_ERROR.getCode())
              .message(fieldErrorNamesList+ErrorCodeEnum.VALIDATION_ERROR.getMessage())
              .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handlUnknownException(Exception e){
        return ErrorResponse.builder()
                .code(ErrorCodeEnum.UNKNOWN_ERROR.getCode())
                .message(ErrorCodeEnum.UNKNOWN_ERROR.getMessage())
                .build();
    }

    private String fieldErrorName(FieldError fieldError){
        return fieldError.getField();
    }


}

package com.company.course.service;

import com.company.course.dto.request.CourseDto;
import com.company.course.dto.response.CourseResponse;

public interface CourseService {

    CourseResponse getAllCourses();

    CourseDto getCourse(long id);
    
    void insert(CourseDto courseDto);

    void updateAll(CourseDto courseDto,long id);

    void updateSome(CourseDto courseDto,long id);

    void deleteCourse(long id);

    CourseResponse getCourseByTitle(String title);



}

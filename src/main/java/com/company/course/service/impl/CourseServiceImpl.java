package com.company.course.service.impl;

import com.company.course.dto.request.CourseDto;
import com.company.course.dto.response.CourseResponse;
import com.company.course.enums.ErrorCodeEnum;
import com.company.course.exception.CourseNotFoundException;
import com.company.course.model.Course;
import com.company.course.repository.CourseRepository;
import com.company.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repo;


    @Override
    public CourseResponse getAllCourses() {
        List<CourseDto> courses = repo.findAll().stream().map(course -> convertToDto(course)).collect(Collectors.toList());

        return makeCourseResponse(courses);
    }

    @Override
    public CourseDto getCourse(long id) {
        return repo.findById(id).map(course -> convertToDto(course)).orElseThrow(() -> new CourseNotFoundException(ErrorCodeEnum.COURSE_NOT_FOUND));
    }

    @Override
    public void insert(CourseDto courseDto) {
        Course course = makeCourse(courseDto);
        repo.save(course);
    }

    @Override
    public void updateAll(CourseDto courseDto, long id) {
        Course course = getCourseById(id);

        course.setDescription(courseDto.getDescription());
        course.setTitle(courseDto.getTitle());

        repo.save(course);
    }

    @Override
    public void updateSome(CourseDto courseDto, long id) {
        Course course = getCourseById(id);
        if (courseDto.getTitle() != null) {
            course.setTitle(courseDto.getTitle());
        }

        if (courseDto.getDescription() != null) {
            course.setTitle(courseDto.getTitle());
        }

        repo.save(course);

    }

    @Override
    public void deleteCourse(long id) {
        Course course = getCourseById(id);

        repo.delete(course);
    }

    @Override
    public CourseResponse getCourseByTitle(String title) {
        List<CourseDto> courseDtos = repo.findCourseByTitle(title).stream().map(course -> convertToDto(course)).collect(Collectors.toList());
        return makeCourseResponse(courseDtos);

    }

    private CourseResponse makeCourseResponse(List<CourseDto> courses) {
        return CourseResponse.builder().courses(courses).build();
    }

    private Course getCourseById(long id) {
        return repo.findById(id).orElseThrow(() -> new CourseNotFoundException((ErrorCodeEnum.COURSE_NOT_FOUND)));
    }

    private Course makeCourse(CourseDto courseDto) {
        return Course.builder().title(courseDto.getTitle()).description(courseDto.getDescription()).build();
    }

    private CourseDto convertToDto(Course course) {
        return CourseDto.builder().title(course.getTitle()).description(course.getDescription()).build();
    }
}

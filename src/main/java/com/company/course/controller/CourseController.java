package com.company.course.controller;

import com.company.course.dto.request.CourseDto;
import com.company.course.dto.response.CourseResponse;
import com.company.course.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
@Tag(name = "Course services",description = "course services")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    @Operation(summary = "This gets all courses")
    public CourseResponse getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    @Operation(summary = "This gets course by id")
    public CourseDto getCourse(@PathVariable("id") long courseId){
        return courseService.getCourse(courseId);
    }

    @PostMapping
    @Operation(summary = "This inserts new course")
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@RequestBody @Valid CourseDto courseDto){
        courseService.insert(courseDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "This updates employee given id")
    public void updateAll(@RequestBody CourseDto courseDto,@PathVariable("id") long id){
        courseService.updateAll(courseDto,id);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "This updates employee given id")
    public void updateSome(@RequestBody CourseDto courseDto,@PathVariable("id") long id){
        courseService.updateSome(courseDto,id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "This deletes course given id")
    public void delete(@PathVariable("id") long id){
        courseService.deleteCourse(id);
    }

    @GetMapping("/search")
    @Operation(summary = "This gets course given title")
    public CourseResponse getCourseByTitle(@RequestParam("title") String title){
        return courseService.getCourseByTitle(title);
    }

}

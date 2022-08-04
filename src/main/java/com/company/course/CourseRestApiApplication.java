package com.company.course;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Course Service API",
                description = "Course crud services",
                version = "v1"
        )
)
public class CourseRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseRestApiApplication.class, args);
    }

}

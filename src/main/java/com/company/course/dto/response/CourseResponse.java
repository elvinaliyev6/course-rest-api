package com.company.course.dto.response;

import com.company.course.dto.request.CourseDto;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseResponse {

    private List<CourseDto> courses;

}

package com.bank.boubyan.util;

import com.bank.boubyan.dto.CourseDTO;
import com.bank.boubyan.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseMapper {
    public static List<CourseDTO> courseDTOList(List<Course> courseList) {
        List<CourseDTO> courseDTOS = new ArrayList<>();
        courseList.forEach((c)-> {
            var courseDTO = getCourseDTO(c);
            courseDTOS.add(courseDTO);
        });
        return courseDTOS;
    }

    private static CourseDTO getCourseDTO(Course c) {
        var courseDTO = new CourseDTO();
        courseDTO.setName(c.getName());
        courseDTO.setDescription(c.getDescription());
        courseDTO.setId(c.getId());
        courseDTO.setInstructorName(c.getInstructorName());
        courseDTO.setRate(c.getRating());
        return courseDTO;
    }

}

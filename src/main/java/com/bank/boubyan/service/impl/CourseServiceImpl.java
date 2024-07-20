package com.bank.boubyan.service.impl;

import com.bank.boubyan.dto.CourseDTO;
import com.bank.boubyan.service.CourseService;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CourseServiceImpl implements CourseService {
    @Override
    public List<CourseDTO> getAll() {
        var course = new CourseDTO();
        course.setId(1);
        course.setCapacity(100);
        course.setName("Introduction to Java");
        course.setLocation("Cairo");
        course.setDescription("This is description");

        var course2 = course.clone();
        course2.setId(2);
        course2.setDescription("Hello");
        course2.setName("C");
        return List.of(course, course2);
    }

    @Override
    public List<CourseDTO> viewUserCourses() {
        return null;
    }
}

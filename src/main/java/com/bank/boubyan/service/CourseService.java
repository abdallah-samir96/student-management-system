package com.bank.boubyan.service;

import com.bank.boubyan.dto.CourseDTO;
import com.bank.boubyan.dto.CourseRegistrationDTO;

import java.util.List;

public interface CourseService {
    List<CourseDTO> getAll();
    List<CourseDTO> viewUserCourses();
    byte[] courseSchedule(CourseRegistrationDTO courseRegistrationDTO);
}

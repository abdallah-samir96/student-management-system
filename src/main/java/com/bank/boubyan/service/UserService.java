package com.bank.boubyan.service;

import com.bank.boubyan.dto.CourseDTO;
import com.bank.boubyan.dto.UserDTO;

public interface UserService {
    UserDTO login(UserDTO userDTO);
    void registerCourse(CourseDTO courseDTO);
    void cancelCourse(CourseDTO courseDTO);
    byte[] courseSchedule(Integer id);
}

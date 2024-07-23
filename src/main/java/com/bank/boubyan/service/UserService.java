package com.bank.boubyan.service;

import com.bank.boubyan.dto.CourseDTO;
import com.bank.boubyan.dto.CourseRegistrationDTO;
import com.bank.boubyan.dto.UserDTO;

public interface UserService {
    UserDTO login(UserDTO userDTO);
    void registerCourse(CourseRegistrationDTO courseRegistrationDTO);
    void cancelCourse(CourseRegistrationDTO courseRegistrationDTO);

}

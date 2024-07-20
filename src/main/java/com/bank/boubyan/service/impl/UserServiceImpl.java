package com.bank.boubyan.service.impl;

import com.bank.boubyan.dto.CourseDTO;
import com.bank.boubyan.dto.UserDTO;
import com.bank.boubyan.service.UserService;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserServiceImpl implements UserService {
    @Override
    public UserDTO login(UserDTO userDTO) {
        return new UserDTO("abdallahsameer22@gmail.com", "abdallah123");
    }

    @Override
    public void registerCourse(CourseDTO courseDTO) {
        // need to validate and check registration
    }

    @Override
    public void cancelCourse(CourseDTO courseDTO) {
        // need to validate and check cancellation
    }

    @Override
    public byte[] courseSchedule(CourseDTO courseDTO) {
        return "Hello".getBytes();
    }
}

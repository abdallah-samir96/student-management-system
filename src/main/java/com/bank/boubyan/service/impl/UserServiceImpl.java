package com.bank.boubyan.service.impl;

import com.bank.boubyan.dto.UserDTO;
import com.bank.boubyan.service.UserService;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserServiceImpl implements UserService {
    @Override
    public UserDTO login(UserDTO userDTO) {
        return new UserDTO("abdallahsameer22@gmail.com", "abdallah123");
    }
}

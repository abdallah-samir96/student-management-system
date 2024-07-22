package com.bank.boubyan.service.impl;

import com.bank.boubyan.dto.CourseDTO;
import com.bank.boubyan.dto.UserDTO;
import com.bank.boubyan.security.utils.TokenUtils;
import com.bank.boubyan.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import java.security.Principal;

@ApplicationScoped
public class UserServiceImpl implements UserService {
    private final SecurityContext securityContext;
    private final TokenUtils tokenUtils;
    @Inject
    public UserServiceImpl(SecurityContext context, TokenUtils tokenUtils) {
        this.securityContext = context;
        this.tokenUtils = tokenUtils;
        System.out.println("Context is : " + context);
        System.out.println("Token Utils is : " + tokenUtils);
    }
    @Override
    public UserDTO login(UserDTO userDTO) {

//        var student = new Student();
//   //     student.setId(new Random().nextInt(10000));
//        student.setFirstName("abdallah");
//        student.setLastName("samir");
//        student.setEmail(UUID.randomUUID().toString() +"@gmail.com");
//        student.setPassword("12345");
//        student.setPhoneNumber("010976");
//        student.setDateOfBirth(LocalDateTime.now());
//        student.setGender("Male");
//
//        System.out.println("The persistence Unit name is " + persistenceUnit);
//        var entityManager = persistenceUnit.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.persist(student);
//        entityManager.getTransaction().commit();
        Principal principal = securityContext.getCallerPrincipal();
        System.out.println("The principal is " + principal);
        if(principal != null) {
            userDTO.setToken(tokenUtils.generateToken(principal.getName()));
        return userDTO;
        }
        userDTO.setToken("");
        return userDTO;
    }

    @Override
    public void registerCourse(CourseDTO courseDTO) {
        // need to validate and check registration
    }

    @Override
    public void cancelCourse(CourseDTO courseDTO) {
        // need to validate and check cancellation
    }

}

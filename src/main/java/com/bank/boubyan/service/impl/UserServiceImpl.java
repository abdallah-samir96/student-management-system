package com.bank.boubyan.service.impl;

import com.bank.boubyan.dto.CourseDTO;
import com.bank.boubyan.dto.UserDTO;
import com.bank.boubyan.model.Student;
import com.bank.boubyan.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@ApplicationScoped
public class UserServiceImpl implements UserService {
    @Override
    public UserDTO login(UserDTO userDTO) {

        var student = new Student();
   //     student.setId(new Random().nextInt(10000));
        student.setFirstName("abdallah");
        student.setLastName("samir");
        student.setEmail(UUID.randomUUID().toString() +"@gmail.com");
        student.setPassword("12345");
        student.setPhoneNumber("010976");
        student.setDateOfBirth(LocalDateTime.now());
        student.setGender("Male");
        var persistenceUnit = Persistence.createEntityManagerFactory("student-management-system");
        System.out.println("The persistence Unit name is " + persistenceUnit);
        var entityManager = persistenceUnit.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
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

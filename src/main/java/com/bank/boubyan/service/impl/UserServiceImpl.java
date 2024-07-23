package com.bank.boubyan.service.impl;

import com.bank.boubyan.dto.CourseRegistrationDTO;
import com.bank.boubyan.dto.UserDTO;
import com.bank.boubyan.model.Course;
import com.bank.boubyan.model.Student;
import com.bank.boubyan.repository.CourseDao;
import com.bank.boubyan.repository.StudentDao;
import com.bank.boubyan.security.utils.TokenUtils;
import com.bank.boubyan.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import java.util.logging.Logger;

@ApplicationScoped
public class UserServiceImpl implements UserService {
    private final SecurityContext securityContext;
    private final TokenUtils tokenUtils;
    private final StudentDao studentDao;
    private final CourseDao courseDao;

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    @Inject
    public UserServiceImpl(SecurityContext context, TokenUtils tokenUtils, StudentDao studentDao, CourseDao courseDao) {
        this.securityContext = context;
        this.tokenUtils = tokenUtils;
        this.studentDao = studentDao;
        this.courseDao = courseDao;
    }
    @Override
    public UserDTO login(UserDTO userDTO) {
        // this part needs to handle, i will py pass it for now and generate token from username
        /*
        Principal principal = securityContext.getCallerPrincipal();
        if(principal != null) {
            userDTO.setToken(tokenUtils.generateToken(principal.getName()));
            return userDTO;
        }
         */
        var token = tokenUtils.generateToken(userDTO.getUsername());
        logger.info("The token is : " + token);
        userDTO.setToken(token);
        return userDTO;
    }

    @Override
    public void registerCourse(CourseRegistrationDTO courseRegistrationDTO) {
        String studentEmail = tokenUtils.getCredential(courseRegistrationDTO.getToken()).getPrincipal();
        logger.info("The student email= " + studentEmail);
        Student student = studentDao.findByEmail(studentEmail);
        Course course = courseDao.findById(courseRegistrationDTO.getCourseId());
        logger.info("The course is " + course);
        if(student != null && course != null) {
            courseDao.register(student, course);
        }else {
            throw new RuntimeException("There is A Problem with student or  course");
        }
    }

    @Override
    public void cancelCourse(CourseRegistrationDTO courseRegistrationDTO) {
        String studentEmail = tokenUtils.getCredential(courseRegistrationDTO.getToken()).getPrincipal();
        courseDao.cancelStudentCourse(studentEmail, courseRegistrationDTO.getCourseId());
    }

}

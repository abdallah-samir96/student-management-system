package com.bank.boubyan.repository;

import com.bank.boubyan.model.Course;
import com.bank.boubyan.model.Student;
import com.bank.boubyan.model.StudentCourse;

import java.util.List;

public interface CourseDao {
    List<Course> getAll();

     Course findById(Integer id);
     void register(Student student, Course course);
     void cancelStudentCourse(String userEmail, Integer courseId);
    StudentCourse getStudentCourse(String userEmail, Integer courseId);
}

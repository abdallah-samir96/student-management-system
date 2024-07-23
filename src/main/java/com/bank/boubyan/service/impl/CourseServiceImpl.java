package com.bank.boubyan.service.impl;

import com.bank.boubyan.dto.CourseDTO;
import com.bank.boubyan.dto.CourseRegistrationDTO;
import com.bank.boubyan.dto.UserDTO;
import com.bank.boubyan.model.Course;
import com.bank.boubyan.repository.StudentDao;
import com.bank.boubyan.security.utils.TokenUtils;
import com.bank.boubyan.util.CourseMapper;
import com.bank.boubyan.repository.CourseDao;
import com.bank.boubyan.service.CourseService;
import com.ironsoftware.ironpdf.PdfDocument;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;
    private final StudentDao studentDao;
    private final TokenUtils tokenUtils;
    private static final Logger logger = Logger.getLogger(CourseServiceImpl.class.getName());
    @Inject
    public CourseServiceImpl(CourseDao courseDao, StudentDao studentDao, TokenUtils tokenUtils) {
        logger.log(Level.INFO, "Initialize CourseDAO, studentDao, tokenUtils {0}{}{}", courseDao);
        this.courseDao = courseDao;
        this.studentDao = studentDao;
        this.tokenUtils = tokenUtils;
    }
    @Override
    public List<CourseDTO> getAll() {
        return CourseMapper.courseDTOList(courseDao.getAll());
    }

    @Override
    public List<CourseDTO> viewUserCourses() {
        return null;
    }
    @Override
    public byte[] courseSchedule(CourseRegistrationDTO courseRegistrationDTO) {
        String studentEmail = tokenUtils.getCredential(courseRegistrationDTO.getToken()).getPrincipal();
        logger.info("Student Email is " + studentEmail);
        var courseStudent = courseDao.getStudentCourse(studentEmail, courseRegistrationDTO.getCourseId());
        logger.log(Level.INFO, "Student Course is {0}", courseStudent);
        if(courseStudent == null || courseStudent.getCourse() == null || courseStudent.getStudent() == null) {
            return new byte[]{};
        }
        logger.log(Level.INFO, "Student  is {0}", courseStudent.getStudent());
        logger.log(Level.INFO, "Course  is {0}", courseStudent.getCourse());
        PdfDocument myPdf = null;
        try(InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("templates/schedule.html")){
            if(inputStream != null) {
                String templateText = new String(inputStream.readAllBytes());
                String printableText = getPrintableText(templateText, courseStudent.getCourse());
                myPdf = PdfDocument.renderHtmlAsPdf(printableText);
            }
            if(myPdf != null){
                return myPdf.getBinaryData();
            }
            return null;
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage() );
            throw new RuntimeException(e);
        }
    }

    private String getPrintableText(String templateText, Course course) {
        return templateText
                .replace("{{courseName}}", course.getName())
                .replace("{{rating}}", course.getRating() + "")
                .replace("{{instructorName}}", course.getInstructorName())
                .replace("{{startDate}}", course.getStartDate().toString());
    }
}

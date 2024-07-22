package com.bank.boubyan.service.impl;

import com.bank.boubyan.dto.CourseDTO;
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
    private static final Logger logger = Logger.getLogger(CourseServiceImpl.class.getName());
    @Inject
    public CourseServiceImpl(CourseDao courseDao) {
        logger.log(Level.INFO, "Initialize CourseDAO {0}", courseDao);
        this.courseDao = courseDao;
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
    public byte[] courseSchedule(Integer id) {
        PdfDocument myPdf = null;
        try(InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("templates/schedule.html")){
            if(inputStream != null) {
                System.out.println("input stream has data " + inputStream);
                myPdf = PdfDocument.renderHtmlAsPdf(new String(inputStream.readAllBytes()));
                System.out.println("MyPDF is " + myPdf);
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
}

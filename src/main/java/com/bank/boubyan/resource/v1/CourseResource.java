package com.bank.boubyan.resource.v1;

import com.bank.boubyan.dto.CourseDTO;
import com.bank.boubyan.dto.ResourcePath;
import com.bank.boubyan.service.CourseService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path(ResourcePath.COURSE_PATH)
public class CourseResource {
    private final CourseService courseService;
    @Inject
    public CourseResource(CourseService courseService) {
        this.courseService = courseService;
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CourseDTO> viewCourses() {
        return courseService.getAll();
    }


    @Path(ResourcePath.USER_COURSES)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CourseDTO> viewUserCourses() {
        return courseService.viewUserCourses();
    }

}

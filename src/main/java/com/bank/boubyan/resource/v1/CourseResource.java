package com.bank.boubyan.resource.v1;

import com.bank.boubyan.dto.CourseDTO;
import com.bank.boubyan.dto.ResourcePath;
import com.bank.boubyan.service.CourseService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

    @Path(ResourcePath.COURSE_SCHEDULE)
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response courseSchedule(@QueryParam("id") Integer id) {
        byte[] fileStream = courseService.courseSchedule(id);
        return Response.ok(fileStream, MediaType.APPLICATION_OCTET_STREAM).build();
    }
}

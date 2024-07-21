package com.bank.boubyan.resource.v1;

import com.bank.boubyan.dto.CourseDTO;
import com.bank.boubyan.dto.ResourcePath;
import com.bank.boubyan.dto.UserDTO;
import com.bank.boubyan.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(ResourcePath.USER_PATH)
public class UserResource {
    private final UserService userService;
    @Inject
    public UserResource(UserService service) {
        this.userService = service;
    }

    @Path(ResourcePath.USER_LOGIN)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO login(UserDTO userDTO) {
        return userService.login(userDTO);
    }

    @Path(ResourcePath.COURSE_REGISTER)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerCourse(CourseDTO courseDTO) {
        userService.registerCourse(courseDTO);
        return Response.ok().build();
    }

    @Path(ResourcePath.COURSE_CANCEL)
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cancelCourse(CourseDTO courseDTO) {
        userService.cancelCourse(courseDTO);
        return Response.ok().build();
    }

    @Path(ResourcePath.COURSE_SCHEDULE)
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response courseSchedule() {
        byte[] fileStream = userService.courseSchedule(new CourseDTO());
        return Response.ok(fileStream, MediaType.APPLICATION_OCTET_STREAM).build();
    }
}

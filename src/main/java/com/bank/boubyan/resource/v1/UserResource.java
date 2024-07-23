package com.bank.boubyan.resource.v1;

import com.bank.boubyan.dto.CourseRegistrationDTO;
import com.bank.boubyan.dto.ResourcePath;
import com.bank.boubyan.dto.UserDTO;
import com.bank.boubyan.service.UserService;

import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(ResourcePath.USER_PATH)
public class UserResource {
    private final UserService userService;
    private  final SecurityContext securityContext;

    @Inject
    public UserResource(UserService service, SecurityContext securityContext) {
        this.userService = service;
        this.securityContext = securityContext;
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
    public Response registerCourse(CourseRegistrationDTO courseRegistrationDTO) {
        userService.registerCourse(courseRegistrationDTO);
        return Response.ok().build();
    }

    @Path(ResourcePath.COURSE_CANCEL)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cancelCourse(CourseRegistrationDTO courseRegistrationDTO) {
        userService.cancelCourse(courseRegistrationDTO);
        return Response.ok().build();
    }

}

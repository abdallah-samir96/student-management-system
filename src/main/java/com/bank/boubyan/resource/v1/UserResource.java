package com.bank.boubyan.resource.v1;

import com.bank.boubyan.dto.CourseDTO;
import com.bank.boubyan.dto.CourseRegistrationDTO;
import com.bank.boubyan.dto.ResourcePath;
import com.bank.boubyan.dto.UserDTO;
import com.bank.boubyan.security.utils.TokenUtils;
import com.bank.boubyan.service.UserService;

import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Principal;
import java.util.logging.Logger;

@Path(ResourcePath.USER_PATH)
public class UserResource {
    private final UserService userService;
    private  final SecurityContext securityContext;
    private static final Logger logger = Logger.getLogger(UserResource.class.getName());

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

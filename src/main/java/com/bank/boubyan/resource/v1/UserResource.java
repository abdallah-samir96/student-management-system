package com.bank.boubyan.resource.v1;

import com.bank.boubyan.dto.CourseDTO;
import com.bank.boubyan.dto.ResourcePath;
import com.bank.boubyan.dto.UserDTO;
import com.bank.boubyan.service.UserService;

import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Principal;

@Path(ResourcePath.USER_PATH)
@ServletSecurity(
        value = @HttpConstraint(rolesAllowed = {"user_hd"}),
        httpMethodConstraints = {
                @HttpMethodConstraint(
                        value = "GET",
                        rolesAllowed = {"user_hd"}),
                @HttpMethodConstraint(
                        value = "POST",
                        rolesAllowed = {"user_hd"})
        })
public class UserResource {
    private final UserService userService;

    private  final SecurityContext securityContext;
    @Inject
    public UserResource(UserService service, SecurityContext securityContext) {
        System.out.println("Hello from securityContext: " + securityContext);
        this.userService = service;
        this.securityContext = securityContext;
    }

    @Path(ResourcePath.USER_LOGIN)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO login(UserDTO userDTO) {
        System.out.println(securityContext);
        Principal principal = securityContext.getCallerPrincipal();
        System.out.println("The principal is " + principal);
        if(principal != null) {
            System.out.println("Hello with " + principal.getName());
        }
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

}

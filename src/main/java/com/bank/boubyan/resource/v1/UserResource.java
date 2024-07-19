package com.bank.boubyan.resource.v1;

import com.bank.boubyan.dto.ResourcePath;
import com.bank.boubyan.dto.UserDTO;
import com.bank.boubyan.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
        System.out.println("Hello World!!!!!!!");
        return userService.login(userDTO);
    }

}

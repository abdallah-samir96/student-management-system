package com.bank.boubyan.resource.v1;

import com.bank.boubyan.dto.ResourcePath;
import com.bank.boubyan.dto.UserDTO;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(ResourcePath.USER_PATH)
public class UserResource {

    @Path(ResourcePath.USER_LOGIN)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO login() {
        return new UserDTO("Hello", "Abdallah");
    }

}

package com.bank.boubyan;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@DeclareRoles("user_hd")
@ApplicationPath("/api")
public class App extends Application {

}

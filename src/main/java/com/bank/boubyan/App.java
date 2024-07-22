package com.bank.boubyan;

import com.bank.boubyan.security.manager.JWTAuthenticationManager;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@DeclareRoles("user_hd")
@ApplicationPath("/api")
public class App extends Application {
//    @Override
//    public Set<Class<?>> getClasses() {
//        Set<Class<?>> classes = new HashSet<>();
//        classes.add(JWTAuthenticationManager.class);
//        return classes;
//    }
}

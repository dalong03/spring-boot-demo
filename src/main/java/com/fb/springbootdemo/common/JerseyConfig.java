package com.fb.springbootdemo.common;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.fb.springbootdemo.controller.UserResource;

@Component
@ApplicationPath("/jersey")
public class JerseyConfig extends ResourceConfig{

    public JerseyConfig(){
        register(UserResource.class);
    }
}

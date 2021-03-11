package com.fb.springbootdemo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

@Component
@Path("/user")
public class UserResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> get() {
		Map<String, Object> map = new HashMap<>();
		map.put("name", "tom");
		return map;
	}

}

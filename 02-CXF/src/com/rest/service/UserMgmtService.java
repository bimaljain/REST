package com.rest.service;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import com.rest.exception.ErrorCode;
import com.rest.exception.ErrorType;
import com.rest.model.User;
import com.rest.model.Users;

@Component("userMgmtService")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "user-management")
@Path("/userMgmt")
public class UserMgmtService {
	
    @XmlElement(name = "users")
    private String uri1 = "/userMgmt/users";
 
    @XmlElement(name = "users")
    private String uri2 = "/userMgmt/generate-report";
 
    public String getUri1() {return uri1;} 
    public void setUri1(String uri1) {this.uri1 = uri1;} 
    public String getUri2() {return uri2;} 
    public void setUri2(String uri2) {this.uri2 = uri2;}
	
    @GET
    @Path("/")
    @Produces({MediaType.APPLICATION_XML , MediaType.APPLICATION_JSON})
    public UserMgmtService getServiceInfo() {
        return new UserMgmtService();
    }

	@GET
	@Path("/users")
	@Produces({MediaType.APPLICATION_XML , MediaType.APPLICATION_JSON})
	public Response getAllUsers() {	 
		Users users = new Users();
		List<User> list = new ArrayList<User>();
		list.add(createUser());
		list.add(createUser());
		list.add(createUser());
		users.setUsers(list);
		Response response = Response.status(200).entity(users).build();	 
		return response;	 
	}
	
	@GET
	@Path("/users/{id}")
	@Produces({MediaType.APPLICATION_XML , MediaType.APPLICATION_JSON})
	public Response getUserById(@PathParam("id") String id) {	 
		User user = createUser();
		user.setName(user.getName()+id);
		Response response = Response.status(200).entity(user).build();	 
		return response;	 
	}
	
	@POST
	@Path("/users")
	@Consumes({MediaType.APPLICATION_XML , MediaType.APPLICATION_JSON})
//	@Produces({MediaType.APPLICATION_XML , MediaType.APPLICATION_JSON})
	public Response createUser(User user) throws URISyntaxException {	 
		System.out.println(user.getName());
		System.out.println(user.getZip());
//		Response response = Response.status(201).contentLocation(new URI("/userMgmt/users/123")).build();
		Response response = Response.status(201).entity("/userMgmt/users/123").build();
		return response;	 
	}
	
	@PUT
	@Path("/users/{id}")
	@Produces({MediaType.APPLICATION_XML , MediaType.APPLICATION_JSON})
	public Response updateUser(@PathParam("id") String id) {	 
		User user = createUser();
		user.setName(user.getName()+id);
		Response response = Response.status(200).entity(user).build();	 
		return response;	 
	}
	
	@DELETE
	@Path("/users/{id}")
	@Produces({MediaType.APPLICATION_XML , MediaType.APPLICATION_JSON})
	public Response deleteUser(@PathParam("id") String id) {	 
		Response response = Response.status(204).build();	 
		return response;	 
	}
	
	@GET
	@Path("/errorSrv")
	@Produces({MediaType.APPLICATION_XML , MediaType.APPLICATION_JSON})
	public Users getError() {	 
		Users user = new Users();
		user.setErrors(new ArrayList<com.rest.exception.Error>());
		user.getErrors().add(new com.rest.exception.Error(ErrorType.BUSINESSERROR, ErrorCode.INVALID_INPUT, "WTF", "Insane Input"));
		return user;	 
	}
	
	public User createUser(){
		User user = new User();
		user.setName("Bimal" + ((Double)Math.random()).intValue());
		user.setZip(((Double)Math.random()).intValue());
		user.setUri("/userMgmt/users/" + user.getZip());
		return user;
	}
}

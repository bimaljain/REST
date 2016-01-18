package com.rest.service;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.stereotype.Component;

@Component("userService")
@Path("/users")
public class UserService {

	@GET
	public Response getAllUsers() {	 
		return Response.status(200).entity("get all user profiles").build();	 
	}

	@GET
	@Path("/{userid}")
	public Response getUserProfile(@PathParam("userid") String userId) {	 
		return Response.status(200).entity("get profile for user=" + userId).build();	 
	}

	@GET
	@Path("/deletedusers")
	public Response getInactiveUsers() {	 
		return Response.status(200).entity("get all deleted users").build();	 
	}

	@GET
	@Path("/deletedusers/{userid}")
	public Response getmessage(@PathParam("userid") String usrId) {	 
		return Response.status(200).entity("get deleted user with id=" + usrId).build();	 
	}

	@GET
	@Path("/userid/{id : \\d+}") //support digit only
	public Response getUserById(@PathParam("id") String id) {	 
		return Response.status(200).entity("get user by id=" + id).build();	 
	}

	@GET
	@Path("/username/{username : [a-zA-Z][a-zA-Z0-9]}")
	public Response getUserByUserName(@PathParam("username") String username) {	 
		return Response.status(200).entity("get user by user name=" + username).build();	 
	}

	@GET
	@Path("{userid}/messages/{messageid}")
	public Response getUserMessage(
			@PathParam("userid") String userId,	
			@PathParam("messageid") int messageId) {	 
		return Response.status(200).entity("msgId=" + messageId + " belongs to userId=" + userId).build();	 
	}

	@GET
	@Path("/query1")
	public Response getUserByQuery1 (
			@QueryParam("from") int from, 
			@QueryParam("to") int to, 
			@QueryParam("orderBy") List<String> orderBy) {	 
		return Response.status(200).entity("get user is called, from=" + from + ", to=" + to + ", orderBy=" + orderBy.toString()).build();	 
	}

	@GET
	@Path("/query2")
	public Response getUserByQuery2 (@Context UriInfo info) {	 
		String from = info.getQueryParameters().getFirst("from");
		String to = info.getQueryParameters().getFirst("to");
		List<String> orderBy = info.getQueryParameters().get("orderBy");	 
		return Response.status(200).entity("get user is called, from=" + from + ", to=" + to + ", orderBy=" + orderBy.toString()).build();	 
	}	

	@GET
	@Path("/query3")
	public Response getUserByQuery3 (
			@DefaultValue("1") @QueryParam("from") int from,
			@DefaultValue("99") @QueryParam("to") int to,
			@DefaultValue("name") @QueryParam("orderBy") List<String> orderBy) {	 
		return Response.status(200).entity("get user is called, from=" + from + ", to=" + to + ", orderBy=" + orderBy.toString()).build();	 
	}

	// http://localhost:8080/01-Rest/rest/users/query4;from=0;to=9;orderBy=name
	@GET
	@Path("/query4")
	public Response getUserByQuery4 (
			@MatrixParam("from") int from,
			@MatrixParam("to") int to,
			@MatrixParam("orderBy") List<String> orderBy) {	 
		return Response.status(200).entity("get user is called, from=" + from + ", to=" + to + ", orderBy=" + orderBy.toString()).build();	 
	}

	// http://localhost:8080/01-Rest/rest/users/query5/name;to=9?from=1 (working uri)
	// http://localhost:8080/01-Rest/rest/users/query5/name?from=1;to=9 (don't work)
	@GET
	@Path("/query5/{orderby}")
	public Response getUserByQuery5 (
			@PathParam("orderby") String orderBy,
			@QueryParam("from") int from,
			@MatrixParam("to") int to) {	 
		return Response.status(200).entity("get user is called, from=" + from + ", to=" + to + ", orderBy=" + orderBy.toString()).build();	 
	}

	@POST
	public Response addUser(
			@FormParam("name") String name,
			@FormParam("age") int age) {	 
		return Response.status(200).entity("new user added, name=" + name + ", age=" + age).build();	 
	}

	@GET
	@Path("/header1")
	public Response getUserAgent(@HeaderParam("user-agent") String userAgent) {
		return Response.status(200).entity("userAgent : " + userAgent).build();
	}

	@GET
	@Path("/header2")
	public Response getUserAgent(@Context HttpHeaders headers) {
		String userAgent = headers.getRequestHeader("user-agent").get(0);
		return Response.status(200).entity("userAgent : " + userAgent).build();
	}

	//List all request headers 
	@GET
	@Path("/header3")
	public Response getHeaders(@Context HttpHeaders headers) {
		for(String header : headers.getRequestHeaders().keySet()){
			System.out.println(header);
		}
		return Response.status(200).build();
	}
}

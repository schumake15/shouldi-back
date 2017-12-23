/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zenith.entrypoints;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.zenith.Beans.UserBean;
import com.zenith.interfaces.UserService;
import com.zenith.request.model.UserSignUpModel;
import com.zenith.service.UserServiceImpl;
import com.zenith.user.response.SuccessOrFailureRegistration;

/**
 * This will be our resource class, it will accept an
 * HTTP request and return an HTTP response
 * 
 */
@Path("/users") // you need a path annotation to make a class a resource class
public class UserEntryPoint {
    
    /*
    * When an http request arrives at this path, and it is a post
    * request, this method will be called. The request will contain
    * JSON data. This method will accept this information and convert it 
    * into a java object (using a framework to do this conversion). Upon 
    * completion this method will convert the CreateUserResponseModel POJO
    * back into JSON that can be read by our front-end
    */
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }) 
    public SuccessOrFailureRegistration createUser(UserSignUpModel requestObject){
        // create new user
        UserService userService = new UserServiceImpl(); 
        UserBean createdUserProfile = userService.createUser(requestObject); 
        SuccessOrFailureRegistration success = new SuccessOrFailureRegistration();
        return success;
    }
    
    @GET
    @Path("/favorites")
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces({MediaType.APPLICATION_JSON})    
    public List<UserBean> getFavoriteUsers(){
    	UserService userService = new UserServiceImpl();
    	return userService.getFavoriteUsers();
    }
    
    @GET
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces({MediaType.APPLICATION_JSON})    
    public UserBean loginUser(){
    	
    	return null; 
    }
    
}

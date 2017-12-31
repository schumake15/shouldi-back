/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zenith.entrypoints;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zenith.Beans.UserBean;
import com.zenith.interfaces.UserService;
import com.zenith.request.model.GenericGetModel;
import com.zenith.request.model.UserLoginModel;
import com.zenith.request.model.UserSignUpModel;
import com.zenith.service.UserServiceImpl;
import com.zenith.session.Token;
import com.zenith.templates.UserTemplate;
import com.zenith.user.response.FavoriteUserWrapper;
import com.zenith.user.response.GenericSuccessOrFailureMessage;
import com.zenith.user.response.ScoreWrapper;
import java.util.ArrayList;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;


/**
 * This will be our resource class, it will accept an HTTP request and return an
 * HTTP response
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
    public Response createUser(UserSignUpModel requestObject){
        // create new user
        UserService userService = new UserServiceImpl(); 
        UserBean createdUserProfile = userService.createUser(requestObject); 
        GenericSuccessOrFailureMessage success = new GenericSuccessOrFailureMessage();
        return Response.ok() //200
			.entity(success).build(); 
			
    }
    //only need emails of favorites
    @GET
    @Path("/favorites")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response getFavoriteUsers() {
        UserService userService = new UserServiceImpl();
        FavoriteUserWrapper wrapper = new FavoriteUserWrapper(); 
        List<String> temp = new ArrayList<String>(); 
        List<UserTemplate> users = userService.getFavoriteUsers(); 
        for(UserTemplate user : users){
            temp.add(user.getEmail()); 
        }
        
        Gson gson = new GsonBuilder().create();
        wrapper.setUserBeans(temp);
        String json = gson.toJson(wrapper); 
        return Response.ok(json).build(); 
    }
    
    @POST
    @Path("/score")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUserScore(GenericGetModel requestModel) {
        UserServiceImpl userService = new UserServiceImpl(); 
        
        ScoreWrapper wrapper = new ScoreWrapper(userService.getUserByToken(requestModel.getToken()).getScore()); 
        
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(wrapper);
       return Response.ok(json).build(); 
               }

    //only need emails of favorites
    @GET
    @Path("/flagged")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response getFlaggedUsers() {
        UserServiceImpl userService = new UserServiceImpl();
        List<UserTemplate> users = userService.getFlaggedUsers();        
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(users); 
        return Response.ok(json).build(); 
    }
    
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Token loginUser(UserLoginModel userLoginModel) {

        UserServiceImpl service = new UserServiceImpl();
        UserBean userBean = service.validateCredentials(userLoginModel);

        if (userBean != null) {
            /* Generates random token and stores it in user */
            Token token = new Token();
            userBean.setToken(token.getToken());
            service.updateUser(userBean);
            token.setUser_role(userBean.getRole());

            /* Return token to user so we can validate who the user is */
            return token;
        }
        return null; /* Compiler happy */ 
    }
    
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/lock")
	public void lockUser(GenericGetModel user)
	{
		UserServiceImpl service= new UserServiceImpl();
		service.lockUser(user);
	}

}

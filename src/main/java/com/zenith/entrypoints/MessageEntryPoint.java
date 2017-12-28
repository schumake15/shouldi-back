package com.zenith.entrypoints;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zenith.Beans.UserBean;
import com.zenith.request.model.MessageModel;
import com.zenith.service.MessageService;

@Path("/messages")
public class MessageEntryPoint {
    @GET
    @Path("/send")
    @Consumes(MediaType.APPLICATION_JSON) // our method consumes or takes in json data
    @Produces({MediaType.APPLICATION_JSON})
    public void sendMessage(MessageModel message) {
    	MessageService service = new MessageService();
    	service.sendMessage(message);    	
    }
    
    @GET
    @Path("/user")
    @Consumes(MediaType.APPLICATION_JSON) // our method consumes or takes in json data
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUserMessages(UserBean user) {
    	MessageService service = new MessageService(); 	    	
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(service.getUserMessages(user)); 
        return Response.ok(json).build(); 
    }
}

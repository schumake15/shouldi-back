package com.zenith.entrypoints;

import com.zenith.Beans.MessageBean;
import com.zenith.request.model.GenericGetModel;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zenith.request.model.GenericGetModel;
import com.zenith.request.model.MessageModel;
import com.zenith.service.MessageService;
import com.zenith.templates.PostTemplate;
import com.zenith.user.response.MessageWrapper;
import java.util.List;
import javax.ws.rs.POST;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

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

    @POST
    @Path("/my/messages")
    @Consumes(MediaType.APPLICATION_JSON) // our method consumes or takes in json data
    @Produces({MediaType.APPLICATION_JSON})
    public MessageWrapper getMessages(GenericGetModel getModel){
        MessageService service = new MessageService(); 
        MessageWrapper wrapper = new MessageWrapper(service.getMyMessages(getModel)); 
        return wrapper; 
    }
    
    @GET
    @Path("/user")
    @Consumes(MediaType.APPLICATION_JSON) // our method consumes or takes in json data
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUserMessages(GenericGetModel user) {
    	MessageService service = new MessageService(); 	    	
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(service.getUserMessages(user)); 
        return Response.ok(json).build(); 
    }

}

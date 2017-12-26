package com.zenith.entrypoints;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
}

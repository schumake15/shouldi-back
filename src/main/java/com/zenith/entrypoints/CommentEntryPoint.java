package com.zenith.entrypoints;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.zenith.Beans.CommentBean;
import com.zenith.service.CommentService;

@Path("/comments")
public class CommentEntryPoint {

    @GET
    @Path("/flagged")
    @Consumes(MediaType.APPLICATION_JSON) // our method consumes or takes in json data
    @Produces({MediaType.APPLICATION_JSON})
    public List<CommentBean> getFlaggedComments() {
        CommentService service = new CommentService();
        return service.getFlaggedComments();
    }
}

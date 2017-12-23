package com.zenith.entrypoints;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.zenith.Beans.PostBean;
import com.zenith.service.PostsService;

@Path("/posts")
public class PostEntryPoint {

    @GET
    @Path("/flagged")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public List<PostBean> getFlaggedPosts() {
        PostsService service = new PostsService();
        return service.getFlaggedPosts();
    }
}

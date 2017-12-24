package com.zenith.entrypoints;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.zenith.Beans.PostBean;
import com.zenith.request.model.CommentModel;
import com.zenith.request.model.PostModel;
import com.zenith.service.CommentService;
import com.zenith.service.PostsService;
import com.zenith.service.VerifyTokenCredentials;
import com.zenith.user.response.GenericSuccessOrFailureMessage;
import javax.ws.rs.POST;

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

    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public GenericSuccessOrFailureMessage createPost(PostModel postModel) {
        
        GenericSuccessOrFailureMessage message = new GenericSuccessOrFailureMessage();
        
        /* Check against the token before any other operations can be done */
        if (VerifyTokenCredentials.verifyCredentials(postModel.getToken()) != null) {
            PostsService service = new PostsService();
            if(service.createPost(postModel)) /* if post was created successfully */ 
                return message; 
        }
        /* Toggles message from success to failure */ 
        message.toggleMessage();
        return message;
    }

}

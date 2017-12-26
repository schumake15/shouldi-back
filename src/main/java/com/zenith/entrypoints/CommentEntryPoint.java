package com.zenith.entrypoints;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.zenith.Beans.CommentBean;
import com.zenith.request.model.CommentModel;
import com.zenith.service.CommentService;
import com.zenith.service.VerifyTokenCredentials;
import com.zenith.user.response.GenericSuccessOrFailureMessage;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

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

    @PUT
    @Path("/flag")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public GenericSuccessOrFailureMessage flagComment(CommentModel comment) {

        GenericSuccessOrFailureMessage message = new GenericSuccessOrFailureMessage();
        CommentService service = new CommentService();
        
        /* Check against the token before any other operations can be done */
        if (VerifyTokenCredentials.verifyCredentials(comment.getToken()) != null) {
            /* If comment can be flagged, return success message */
            if (service.flagComment(comment)) {
                return message;
            }
        }
        /* By default the message is success, toggle to change it to failure */
        message.toggleMessage();
        return message;
    }
}
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
    @Path("/flagComment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public GenericSuccessOrFailureMessage flagComment(CommentModel comment) {

        GenericSuccessOrFailureMessage message = new GenericSuccessOrFailureMessage();
        CommentService service = new CommentService();
        if (service.flagComment(comment)) {
            return message;
        } else {
            /* By default the message is success, toggle to change it to failure */
            message.toggleMessage();
            return message;
        }

    }
}

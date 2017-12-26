package com.zenith.entrypoints;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.zenith.Beans.PostBean;
import com.zenith.Beans.UserBean;
import com.zenith.DAO.UserDAO;
import com.zenith.request.model.AdPostModel;
import com.zenith.request.model.CommentModel;
import com.zenith.request.model.FlagPostModel;
import com.zenith.request.model.PostModel;
import com.zenith.request.model.PostRequestModel;
import com.zenith.request.model.RatingModel;
import com.zenith.service.CommentService;
import com.zenith.service.PostsService;
import com.zenith.service.UserServiceImpl;
import com.zenith.service.VerifyTokenCredentials;
import com.zenith.user.response.GenericSuccessOrFailureMessage;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

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
            if (service.createPost(postModel)) /* if post was created successfully */ {
                return message;
            }
        }
        /* Toggles message from success to failure */
        message.toggleMessage();
        return message;
    }

    @POST
    @Path("/ad/post")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public GenericSuccessOrFailureMessage createAd(AdPostModel AdPostModel) {

        GenericSuccessOrFailureMessage message = new GenericSuccessOrFailureMessage();

        /* Check against the token before any other operations can be done */
        if (VerifyTokenCredentials.verifyCredentials(AdPostModel.getToken()) != null) {
            PostsService postService = new PostsService();
            if (postService.createAd(AdPostModel)) /* if post was created successfully */ {
                return message;
            }
        }
        /* Toggles message from success to failure */
        message.toggleMessage();
        return message;
    }

    @PUT
    @Path("/flag")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public GenericSuccessOrFailureMessage flagPost(FlagPostModel post) {

        GenericSuccessOrFailureMessage message = new GenericSuccessOrFailureMessage();
        PostsService service = new PostsService();

        /* Check against the token before any other operations can be done */
        if (VerifyTokenCredentials.verifyCredentials(post.getToken()) != null) {
            /* If post can be flagged, return success message */
            if (service.flagPost(post)) {
                return message;
            }
        }
        /* By default the message is success, toggle to change it to failure */
        message.toggleMessage();
        return message;
    }
    
    @GET
    @Path("/unseen/all")
    @Consumes(MediaType.APPLICATION_JSON) // our method consumes or takes in json data
    @Produces({MediaType.APPLICATION_JSON})
	public PostBean getUnseenPost(UserBean user){
    	PostsService service= new PostsService();
    	return service.getUnseenPost(user);
    }
    
    @GET
    @Path("/unseen/gender")
    @Consumes(MediaType.APPLICATION_JSON) // our method consumes or takes in json data
    @Produces({MediaType.APPLICATION_JSON})
	public PostBean getUnseenPostGendered(PostRequestModel post){
    	PostsService service= new PostsService();
    	
    	return service.getUnseenPostGendered(post.getUser(), post.getGender());
    }
    
    @GET
    @Path("/best/{eventId}")
    @Consumes(MediaType.APPLICATION_JSON) // our method consumes or takes in json data
    @Produces({MediaType.APPLICATION_JSON})
	public PostBean getBestEventPost(@PathParam("eventId") int event){
    	PostsService service= new PostsService();
    	return service.getBestEventPost(event);
    }
    
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/remove")
	public void removePost(PostBean post)
	{
		PostsService service= new PostsService();
		service.removePost(post);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/like")
	public void like(RatingModel rating)
	{
		PostsService service= new PostsService();
		service.like(rating);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/dislike")
	public void dislike(RatingModel rating)
	{
		PostsService service= new PostsService();
		service.dislike(rating);
	}

}

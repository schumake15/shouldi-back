package com.zenith.request.model;

import com.zenith.Beans.PostBean;
import com.zenith.Beans.UserBean;

public class RatingModel {
	private PostBean post;
	private UserBean rater;
	private String comment;
	
	public PostBean getPost() {
		return post;
	}
	public void setPost(PostBean post) {
		this.post = post;
	}
	public UserBean getRater() {
		return rater;
	}
	public void setRater(UserBean rater) {
		this.rater = rater;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}

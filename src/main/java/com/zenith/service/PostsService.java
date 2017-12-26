package com.zenith.service;

import java.util.List;

import com.zenith.Beans.PostBean;
import com.zenith.Beans.UserBean;
import com.zenith.DAO.PostDAO;
import com.zenith.interfaces.DAO;
import com.zenith.request.model.AdPostModel;
import com.zenith.request.model.FlagPostModel;
import com.zenith.request.model.PostModel;
import com.zenith.request.model.RatingModel;
import org.hibernate.HibernateException;

public class PostsService {

    PostDAO database;

    public PostsService() {
        this.database = new PostDAO();
    }

    public List<PostBean> getFlaggedPosts() {
        try {
            this.database.openConnection();
            return database.getFlaggedPosts();
        } finally {
            database.closeConnection();
        }
    }

    public PostBean getUnseenPost(UserBean user) {
        try {
            this.database.openConnection();
            return database.getUnseenPost(user);
        } finally {
            database.closeConnection();
        }
    }

    public PostBean getUnseenPostGendered(UserBean user, String gender) {
        try {
            this.database.openConnection();
            return database.getUnseenPostGendered(user, gender);
        } finally {
            database.closeConnection();
        }
    }

    public PostBean getBestEventPost(int event) {
        try {
            this.database.openConnection();
            return database.getBestEventPost(event);
        } finally {
            database.closeConnection();
        }
    }

    public boolean createPost(PostModel postModel) {
        try {
            this.database.openConnection();
            if (database.createPost(postModel)) {
                return true;
            }
        } finally {
            database.closeConnection();
        }
        return false;
    }

    public boolean flagPost(FlagPostModel flagPostModel) {
        try {
            this.database.openConnection();
            return database.flagPost(flagPostModel);
        } finally {
            database.closeConnection();
        }
    }

    public boolean createAd(AdPostModel adPostModel) {
        try {
            this.database.openConnection();
            if (database.createAd(adPostModel)) {
                return true;
            }
        }catch(HibernateException e) {
        
        e.printStackTrace();
    }finally {
            database.closeConnection();
        }
        return false;
    }
    
	public void removePost(PostBean post) {
	   	try 
    	{
    		this.database.openConnection();
    		database.removePost(post);
    	}
    	finally
    	{
    		database.closeConnection();
    	}
	}

	public void like(RatingModel rating) {
	   	try 
    	{
    		this.database.openConnection();
    		database.like(rating);
    	}
    	finally
    	{
    		database.closeConnection();
    	}		
	}
	
	public void dislike(RatingModel rating) {
	   	try 
    	{
    		this.database.openConnection();
    		database.dislike(rating);
    	}
    	finally
    	{
    		database.closeConnection();
    	}	
	}

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zenith.templates;

import java.util.List;
import javax.ws.rs.core.GenericEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wayne
 */
@XmlRootElement
public class PostTemplate {
    
    private int post_id;
    private String image; 
    private int likes;
    private int dislikes; 
    private List<String> comments; 
    

    
    public PostTemplate(){}
    public PostTemplate(int post_id,  int likes, String image, int dislikes, List<String> comments){
        this.post_id = post_id;
        this.image = image;
        /* Mark it as a png */ 
        this.image = "data:image/png;base64," + image; 
        this.likes = likes;
        this.dislikes = dislikes; 
        this.comments = comments;
      
    }
    /**
     * @return the post_id
     */
    public int getPost_id() {
        return post_id;
    }

    /**
     * @param post_id the post_id to set
     */
    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the likes
     */
    public int getLikes() {
        return likes;
    }

    /**
     * @param likes the likes to set
     */
    public void setLikes(int likes) {
        this.likes = likes;
    }

    /**
     * @return the dislikes
     */
    public int getDislikes() {
        return dislikes;
    }

    /**
     * @param dislikes the dislikes to set
     */
    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    /**
     * @return the comments
     */
    public List<String> getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(List<String> comments) {
        this.comments = comments;
    }
}

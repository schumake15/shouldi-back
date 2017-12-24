/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zenith.request.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wayne
 */
@XmlRootElement
public class CommentModel {
    
    private String token; 
    private int commentID; 

    /**
     * @return the commentID
     */
    public int getCommentID() {
        return commentID;
    }

    /**
     * @param commentID the commentID to set
     */
    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }
    
    
    
}

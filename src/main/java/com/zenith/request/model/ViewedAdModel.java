/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zenith.request.model;

/**
 *
 * @author wayne
 */
public class ViewedAdModel {
    
    private String token;
    private int post_id;
    private int clicked; 

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
     * @return the clicked
     */
    public int getClicked() {
        return clicked;
    }

    /**
     * @param clicked the clicked to set
     */
    public void setClicked(int clicked) {
        this.clicked = clicked;
    }
    
    
}

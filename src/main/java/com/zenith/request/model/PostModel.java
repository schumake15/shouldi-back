/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zenith.request.model;

import java.sql.Blob;
import java.sql.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wayne
 */
@XmlRootElement
public class PostModel {

    /* Upon creating a post, all we need is the imagePath, occasion and token */
    private String imagePath;
    private String occasion;
    private String  token;
    
    public PostModel(String image, String occasion, int usser_id){
        this.imagePath = image;
        this.occasion = occasion;
        this.token = token;
    }
    
    public PostModel(){}
    /**
     * @return the imagePath
     */
    public String getImage() {
        return imagePath;
    }

    /**
     * @param image the imagePath to set
     */
    public void setImage(String image) {
        this.imagePath = image;
    }

    /**
     * @return the occasion
     */
    public String getOccasion() {
        return occasion;
    }

    /**
     * @param occasion the occasion to set
     */
    public void setOccasion(String occasion) {
        this.occasion = occasion;
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

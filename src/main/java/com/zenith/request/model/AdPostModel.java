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
public class AdPostModel {
    
    private String url; 
    private String image; 
    private String token; 
    private int amountToPay; 

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
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
     * @return the amountToPay
     */
    public int getAmountToPay() {
        return amountToPay;
    }

    /**
     * @param amountToPay the amountToPay to set
     */
    public void setAmountToPay(int amountToPay) {
        this.amountToPay = amountToPay;
    }
    
    
}

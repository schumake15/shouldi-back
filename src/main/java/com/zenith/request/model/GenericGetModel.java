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
public class GenericGetModel {
    
    private String token; 

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

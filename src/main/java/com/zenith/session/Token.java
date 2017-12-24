/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zenith.session;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wayne
 */
@XmlRootElement
public class Token {

    private String token;

    public Token() {
        Random random = new SecureRandom();
        token = new BigInteger(130, random).toString(32);

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

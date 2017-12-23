/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zenith.user.response;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wayne
 */
@XmlRootElement
public class SuccessOrFailureRegistration {

    private String message = "Success";

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}

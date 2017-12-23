/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zenith.user.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {

    private String errorMessage;
    private String errorMessageKey;

    public ErrorMessage() {
    }

    public ErrorMessage(String errorMessage, String errorMessageKey) {
        this.errorMessage = errorMessage;
        this.errorMessageKey = errorMessageKey;

    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the errorMessageKey
     */
    public String getErrorMessageKey() {
        return errorMessageKey;
    }

    /**
     * @param errorMessageKey the errorMessageKey to set
     */
    public void setErrorMessageKey(String errorMessageKey) {
        this.errorMessageKey = errorMessageKey;
    }
}

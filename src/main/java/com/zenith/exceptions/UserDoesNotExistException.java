/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zenith.exceptions;

/**
 *
 * @author wayne
 */
public class UserDoesNotExistException extends RuntimeException  {
    
    public UserDoesNotExistException(String message){
        super(message); 
    }
}

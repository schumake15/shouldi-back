/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zenith.service;

import com.zenith.Beans.UserBean;
import com.zenith.DAO.UserDAO;

/**
 *
 * @author wayne
 */
public class VerifyTokenCredentials {
    
    static UserDAO userdao; 
    static UserBean userBean; 
    
    public static UserBean verifyCredentials(String token){
        userdao = new UserDAO(); 
        userdao.openConnection();
        userBean = userdao.getUserByToken(token); 
        userdao.closeConnection();
        if (userBean != null) {
            return userBean;
        } else {
            return null; 
        }
        
    }
    
}

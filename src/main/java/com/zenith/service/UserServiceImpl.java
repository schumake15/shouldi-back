/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zenith.service;

import com.zenith.DAO.OracleDB;
import com.zenith.Beans.UserBean;
import com.zenith.DAO.UserDAO;
import com.zenith.exceptions.RecordAlreadyExistsException;
import com.zenith.exceptions.UserDoesNotExistException;
import com.zenith.interfaces.DAO;
import com.zenith.interfaces.UserService;
import com.zenith.request.model.UserLoginModel;
import com.zenith.request.model.UserSignUpModel;
import com.zenith.user.response.ErrorMessages;

import java.util.List;

import org.springframework.beans.BeanUtils;

/**
 *
 * @author calebschumake
 */
public class UserServiceImpl implements UserService {

    UserDAO database = null;

    public UserServiceImpl() {
        this.database = new UserDAO();
    }

    /**
     * UserSignUpModel is an object that represents the users sign-up
     * information If the user does not already exist, this object must be
     * converted to the Persistent class UserBean and then persisted
     *
     * @param requestObject
     * @return UserBean
     */
    public UserBean createUser(UserSignUpModel requestObject) {

        UserBean userBean = new UserBean();

        /* Check if user already exits */
        UserBean existingUser = this.getUserByEmail(requestObject.getEmail());

        if (existingUser != null) {
            throw new RecordAlreadyExistsException(ErrorMessages.RECORD_ALREADY_EXISTS.name());
        }

        /* Convert the user request into a persistent Object */
        userBean.setEmail(requestObject.getEmail());
        userBean.setGender(requestObject.getGender());
        userBean.setPassword(requestObject.getPassword());
        userBean.setRole(requestObject.getRole());

        /* Save user in database */
        saveUser(userBean);

        /* return information back to client */
        return userBean;
    }

    private UserBean getUserByEmail(String email) {

        UserBean userBean = null;
        if (email == null || email.isEmpty()) {
            return userBean;
        }

        try {
            this.database.openConnection();
            userBean = this.database.getUserByEmail(email);
        } finally {
            this.database.closeConnection();
        }
        return userBean;
    }

    public UserBean validateCredentials(UserLoginModel userLoginModel) {
        UserBean userBean = null;
        userBean = this.getUserByEmail(userLoginModel.getEmail());
        /* If user cannot be found by email, or pasword do not let them login */
        if (userBean == null || !(userBean.getPassword().equals(userLoginModel.getPassword()))) {
            throw new UserDoesNotExistException(ErrorMessages.USER_DOES_NOT_EXIST.name()); 
        }
        return userBean;
    }

    public void saveUser(UserBean user) {

        try {
            this.database.openConnection();
            this.database.saveUser(user);
        } finally {
            this.database.closeConnection();
        }

    }

    public void updateUser(UserBean user) {

        try {
            this.database.openConnection();
            this.database.updateUser(user);
        } finally {
            this.database.closeConnection();
        }

    }

    public List<UserBean> getFavoriteUsers() {
        try {
            this.database.openConnection();
            return database.getFavoriteUsers();
        } finally {
            database.closeConnection();
        }
    }
    
	public void lockUser(UserBean user) {
    	try 
    	{
    		this.database.openConnection();
    		database.lockUser(user);
    	}
    	finally
    	{
    		database.closeConnection();
    	}
		
	}
}

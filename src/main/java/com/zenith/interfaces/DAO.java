/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zenith.interfaces;

import java.util.List;

import com.zenith.Beans.PostBean;
import com.zenith.Beans.UserBean;

/**
 * 
 * @author calebschumake
 */
public interface DAO {

    public void openConnection();

    public void closeConnection();

}

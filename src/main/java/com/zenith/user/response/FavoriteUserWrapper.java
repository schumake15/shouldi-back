/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zenith.user.response;

import com.zenith.Beans.UserBean;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wayne
 */
@XmlRootElement
public class FavoriteUserWrapper {
    private List<String> userEmail; 

    /**
     * @return the userBeans
     */
    public List<String> getUserBeans() {
        return userEmail;
    }

    /**
     * @param userBeans the userBeans to set
     */
    public void setUserBeans(List<String> userBeans) {
        this.userEmail = userBeans;
    }
}

package com.zenith.request.model;

import com.zenith.Beans.UserBean;

public class PostRequestModel {
	private UserBean user;
	private String gender;
	
	
	public UserBean getUser() {
		return user;
	}
	public void setUser(UserBean user) {
		this.user = user;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
}

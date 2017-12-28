package com.zenith.templates;

import java.util.List;

import com.zenith.Beans.AdvertisementBean;
import com.zenith.Beans.CommentBean;
import com.zenith.Beans.DislikeBean;
import com.zenith.Beans.LikeBean;
import com.zenith.Beans.MessageBean;
import com.zenith.Beans.PostBean;
import com.zenith.Beans.VPBean;

public class UserTemplate {
    private int user_id;
    private String token;
    private String email; 
    private String password;
    private String gender; 
    private String role; 
    private int lock;
    private int flag;
    private int score;
    
    public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getLock() {
		return lock;
	}
	public void setLock(int lock) {
		this.lock = lock;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	public UserTemplate(){}
    public UserTemplate(int user_id,  String email, String password, String gender, String role, int lock, int flag, int score, String token){
        this.user_id = user_id;
        this.email = email;
        this.password = password; 
        this.gender = gender;
        this.role = role; 
        this.lock = lock;
        this.flag = flag;
        this.score = score; 
        this.token = token;
      
    }
}

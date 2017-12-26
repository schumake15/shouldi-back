package com.zenith.request.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.zenith.Beans.UserBean;

@XmlRootElement
public class MessageModel {
	private UserBean to;
	private UserBean from;
	private String content;
	
	
	public UserBean getTo() {
		return to;
	}
	public void setTo(UserBean to) {
		this.to = to;
	}
	public UserBean getFrom() {
		return from;
	}
	public void setFrom(UserBean from) {
		this.from = from;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}

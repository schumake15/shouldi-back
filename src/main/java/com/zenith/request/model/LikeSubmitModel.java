package com.zenith.request.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LikeSubmitModel {
	String comment;
	int like; 
        String token; 
}

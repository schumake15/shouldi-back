package com.zenith.Beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "Likes")
public class LikeBean {

    @Id //designates as primary key
    @Column(name = "LIKE_ID")
    @SequenceGenerator(sequenceName = "LIKE_SEQ", name = "LIKE_SEQ")
    @GeneratedValue(generator = "LIKE_SEQ", strategy = GenerationType.SEQUENCE)
    private int like_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserBean user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostBean post;

    
    
    
    public LikeBean() {
		super();
	}

	public LikeBean(UserBean user, PostBean post) {
		super();
		this.user = user;
		this.post = post;
	}

	public LikeBean(int like_id, UserBean user, PostBean post) {
		super();
		this.like_id = like_id;
		this.user = user;
		this.post = post;
	}

	/**
     * @return the like_id
     */
    public int getLike_id() {
        return like_id;
    }

    /**
     * @param like_id the like_id to set
     */
    public void setLike_id(int like_id) {
        this.like_id = like_id;
    }

    /**
     * @return the user
     */
    public UserBean getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserBean user) {
        this.user = user;
    }

    /**
     * @return the post
     */
    public PostBean getPost() {
        return post;
    }

    /**
     * @param post the post to set
     */
    public void setPost(PostBean post) {
        this.post = post;
    }
}

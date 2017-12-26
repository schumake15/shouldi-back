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
@Table(name = "Dislikes")
public class DislikeBean {

    @Id //designates as primary key
    @Column(name = "DISLIKE_ID")
    @SequenceGenerator(sequenceName = "DISLIKE_SEQ", name = "DISLIKE_SEQ")
    @GeneratedValue(generator = "DISLIKE_SEQ", strategy = GenerationType.SEQUENCE)
    private int dislike_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserBean user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostBean post;

    
    public DislikeBean() {
		super();
	}

	public DislikeBean(UserBean user, PostBean post) {
		super();
		this.user = user;
		this.post = post;
	}

	public DislikeBean(int like_id, UserBean user, PostBean post) {
		super();
		this.dislike_id = like_id;
		this.user = user;
		this.post = post;
	}
    
    /**
     * @return the dislike_id
     */
    public int getDislike_id() {
        return dislike_id;
    }

    /**
     * @param dislike_id the dislike_id to set
     */
    public void setDislike_id(int dislike_id) {
        this.dislike_id = dislike_id;
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

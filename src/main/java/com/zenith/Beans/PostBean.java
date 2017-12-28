/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zenith.Beans;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@XmlRootElement
@Table(name = "Posts")
public class PostBean implements Serializable, Comparable<PostBean> {

    @Id //designates as primary key
    @Column(name = "POST_ID")
    @SequenceGenerator(sequenceName = "POST_SEQ", name = "POST_SEQ")
    @GeneratedValue(generator = "POST_SEQ", strategy = GenerationType.SEQUENCE)
    private int post_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserBean poster;

    @OneToMany(
            mappedBy = "viewed",
            orphanRemoval = true
    )
    private List<VPBean> viewed_by;

    @OneToMany(
            mappedBy = "postBean",
            orphanRemoval = true
    )
    private List<CommentBean> post_comments;

    @OneToMany(
            mappedBy = "post",
            orphanRemoval = true
    )
    private List<LikeBean> likes;

    @OneToMany(
            mappedBy = "post",
            orphanRemoval = true
    )
    private List<DislikeBean> dislikes;

    @CreationTimestamp
    @Column(name = "date_created")
    private Date created; 

    @Column(name = "occasion")
    private String occasion;

    @Column(name = "COMPLETED")
    private int completed;

    @Column(name = "FLAG")
    private int flag;

    @Column(name = "image")
    private Blob image;

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public UserBean getPoster() {
        return poster;
    }

    public void setPoster(UserBean poster) {
        this.poster = poster;
    }

    public List<CommentBean> getPost_comments() {
        return post_comments;
    }

    public void setPost_comments(ArrayList<CommentBean> post_comments) {
        this.setPost_comments(post_comments);
    }

    public int isFlag() {
        return getFlag();
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public PostBean(int post_id, UserBean poster, ArrayList<CommentBean> post_comments, int flag) {
        super();
        this.post_id = post_id;
        this.poster = poster;
        this.post_comments = post_comments;
        this.flag = flag;
    }

    public PostBean(UserBean poster, ArrayList<CommentBean> post_comments, int flag) {
        super();
        this.poster = poster;
        this.post_comments = post_comments;
        this.flag = flag;
    }

    public PostBean(Blob image, String occasion, UserBean poster) {
        this.image = image;
        this.occasion = occasion;
        this.poster = poster;
    }

    public PostBean() {
        super();
    }

    /**
     * @return the viewed_by
     */
    public List<VPBean> getViewed_by() {
        return viewed_by;
    }

    /**
     * @param viewed_by the viewed_by to set
     */
    public void setViewed_by(List<VPBean> viewed_by) {
        this.viewed_by = viewed_by;
    }

    /**
     * @param post_comments the post_comments to set
     */
    public void setPost_comments(List<CommentBean> post_comments) {
        this.post_comments = post_comments;
    }

    /**
     * @return the likes
     */
    public List<LikeBean> getLikes() {
        return likes;
    }

    /**
     * @param likes the likes to set
     */
    public void setLikes(List<LikeBean> likes) {
        this.likes = likes;
    }

    /**
     * @return the dislikes
     */
    public List<DislikeBean> getDislikes() {
        return dislikes;
    }

    /**
     * @param dislikes the dislikes to set
     */
    public void setDislikes(List<DislikeBean> dislikes) {
        this.dislikes = dislikes;
    }

    /**
     * @return the created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * @return the occasion
     */
    public String getOccasion() {
        return occasion;
    }

    /**
     * @param occasion the occasion to set
     */
    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }

    /**
     * @return the completed
     */
    public int getCompleted() {
        return completed;
    }

    /**
     * @param completed the completed to set
     */
    public void setCompleted(int completed) {
        this.completed = completed;
    }

    /**
     * @return the flag
     */
    public int getFlag() {
        return flag;
    }

    /**
     * @return the image
     */
    public Blob getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Blob image) {
        this.image = image;
    }

	public int compareTo(PostBean post) {
//		if(String.sort(this.occasion.toLowerCase(),post.getOccasion().toLowerCase()))
		return 0;
	}

}

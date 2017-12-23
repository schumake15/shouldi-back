/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zenith.Beans;

/**
 *
 * @author calebschumake
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SIUser")
public class UserBean implements Serializable {

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Id
    @Column(name = "user_id")
    @SequenceGenerator(sequenceName = "USER_SEQ", name = "USER_SEQ")
    @GeneratedValue(generator = "USER_SEQ", strategy = GenerationType.SEQUENCE)
    private int user_id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "gender")
    private int gender;

    @Column(name = "is_moderator")
    private int moderator;

    @Column(name = "locked_account")
    private int lock;

    @OneToMany(
            mappedBy = "poster",
            orphanRemoval = true
    )
    private List<PostBean> user_posts;

    @OneToMany(
            mappedBy = "viewer",
            orphanRemoval = true
    )
    private List<VPBean> viewed_posts;

    @OneToMany(
            mappedBy = "userBean",
            orphanRemoval = true
    )
    private List<MessageBean> messages;

    @OneToMany(
            mappedBy = "commentor",
            orphanRemoval = true
    )
    private List<CommentBean> user_comments;

    @OneToMany(
            mappedBy = "user",
            orphanRemoval = true
    )
    private List<LikeBean> likes;

    @OneToMany(
            mappedBy = "user",
            orphanRemoval = true
    )
    private List<DislikeBean> dislikes;

    @Column(name = "score")
    private int score;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return getEmail();
    }

    public void setUsername(String username) {
        this.setEmail(username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int isModerator() {
        return getModerator();
    }

    public void setModerator(int moderator) {
        this.moderator = moderator;
    }

    public int isLock() {
        return getLock();
    }

    public void setLock(int lock) {
        this.lock = lock;
    }
//

    public List<PostBean> getUser_posts() {
        return user_posts;
    }

    public void setUser_posts(ArrayList<PostBean> user_posts) {
        this.setUser_posts(user_posts);
    }

    public List<VPBean> getViewed_posts() {
        return viewed_posts;
    }

    public void setViewed_posts(ArrayList<VPBean> viewed_posts) {
        this.setViewed_posts(viewed_posts);
    }

    public List<MessageBean> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<MessageBean> messages) {
        this.setMessages(messages);
    }

    public List<CommentBean> getUser_comments() {
        return user_comments;
    }

    public void setUser_comments(ArrayList<CommentBean> user_comments) {
        this.setUser_comments(user_comments);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<LikeBean> getLikes() {
        return likes;
    }

    public void setLikes(List<LikeBean> likes) {
        this.likes = likes;
    }

    public List<DislikeBean> getDislikes() {
        return dislikes;
    }

    public void setDislikes(List<DislikeBean> dislikes) {
        this.dislikes = dislikes;
    }

    public int getModerator() {
        return moderator;
    }

    public int getLock() {
        return lock;
    }

    public void setUser_posts(List<PostBean> user_posts) {
        this.user_posts = user_posts;
    }

    public void setViewed_posts(List<VPBean> viewed_posts) {
        this.viewed_posts = viewed_posts;
    }

    public void setMessages(List<MessageBean> messages) {
        this.messages = messages;
    }

    public void setUser_comments(List<CommentBean> user_comments) {
        this.user_comments = user_comments;
    }

    public UserBean(int user_id, String username, String password, int gender, int moderator, int lock,
            ArrayList<PostBean> user_posts, ArrayList<VPBean> viewed_posts, ArrayList<MessageBean> messages,
            ArrayList<CommentBean> user_comments, ArrayList<LikeBean> likes, ArrayList<DislikeBean> dislikes, int score) {
        super();
        this.user_id = user_id;
        this.email = username;
        this.password = password;
        this.gender = gender;
        this.moderator = moderator;
        this.lock = lock;
        this.user_posts = user_posts;
        this.viewed_posts = viewed_posts;
        this.messages = messages;
        this.likes = likes;
        this.dislikes = dislikes;
        this.user_comments = user_comments;
        this.score = score;
    }

    public UserBean() {
        super();
    }

    public UserBean(String username, String password, int gender, int moderator, int lock,
            ArrayList<PostBean> user_posts, ArrayList<VPBean> viewed_posts, ArrayList<MessageBean> messages,
            ArrayList<CommentBean> user_comments, ArrayList<LikeBean> likes, ArrayList<DislikeBean> dislikes, int score) {
        super();
        this.email = username;
        this.password = password;
        this.gender = gender;
        this.moderator = moderator;
        this.lock = lock;
        this.user_posts = user_posts;
        this.viewed_posts = viewed_posts;
        this.messages = messages;
        this.user_comments = user_comments;
        this.likes = likes;
        this.dislikes = dislikes;
        this.score = score;
    }

}

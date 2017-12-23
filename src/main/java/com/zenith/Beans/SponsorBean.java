/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zenith.Beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Sponsor")
@PrimaryKeyJoinColumn(name = "USER_ID")
public class SponsorBean extends UserBean implements Serializable {

    @Column(name = "EMAIL")
    private String email;

    /* Have to grab user_id from parent class */
    @Column(name = "spon_id")
    private int spon_id = this.getUser_id();

    @Column(name = "BALANCE")
    private int balance;

    @OneToMany(
            mappedBy = "Sponsor",
            orphanRemoval = true
    )
    private List<AdvertisementBean> ads;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<AdvertisementBean> getAds() {
        return ads;
    }

    public void setAds(ArrayList<AdvertisementBean> ads) {
        this.ads = ads;
    }

    public SponsorBean(int user_id, String username, String password, String gender, String role, int lock,
            ArrayList<PostBean> user_posts, ArrayList<VPBean> viewed_posts, ArrayList<MessageBean> messages,
            ArrayList<CommentBean> user_comments, int score, String email, int balance, ArrayList<AdvertisementBean> ads, ArrayList<LikeBean> likes, ArrayList<DislikeBean> dislikes) {
        super(user_id, username, password, gender, role, lock, user_posts, viewed_posts, messages, user_comments, likes, dislikes,
                score);
        this.email = email;
        this.balance = balance;
        this.ads = ads;
    }

    public SponsorBean() {
        super();
    }

    public SponsorBean(String username, String password, String gender, String role, int lock,
            ArrayList<PostBean> user_posts, ArrayList<VPBean> viewed_posts, ArrayList<MessageBean> messages,
            ArrayList<CommentBean> user_comments, int score, String email, int balance, ArrayList<AdvertisementBean> ads, ArrayList<LikeBean> likes, ArrayList<DislikeBean> dislikes) {
        super(username, password, gender, role, lock, user_posts, viewed_posts, messages, user_comments, likes, dislikes,
                score);
        this.email = email;
        this.balance = balance;
        this.ads = ads;
    }

}

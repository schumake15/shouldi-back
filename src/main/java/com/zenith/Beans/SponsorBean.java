///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.zenith.Beans;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.PrimaryKeyJoinColumn;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "Sponsor")
//public class SponsorBean implements Serializable {
//    
//    @Id
//    @Column(name = "sponsor_id")
//    @SequenceGenerator(sequenceName = "SPONSOR_SEQ", name = "SPONSOR_SEQ")
//    @GeneratedValue(generator = "SPONSOR_SEQ", strategy = GenerationType.SEQUENCE)
//    private int sponsor_id;
//
//    @Column(name = "EMAIL")
//    private String email;
//
//
//    
//    @Column(name="token")
//    protected String token; 
//    
//    @OneToMany(
//            mappedBy = "sponsorBean",
//            orphanRemoval = true
//    )
//    private List<MessageBean> messages;
//
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public int getBalance() {
//        return balance;
//    }
//
//    public void setBalance(int balance) {
//        this.balance = balance;
//    }
//
//    public List<AdvertisementBean> getAds() {
//        return ads;
//    }
//
//    public void setAds(ArrayList<AdvertisementBean> ads) {
//        this.ads = ads;
//    }
//
//    public SponsorBean(String email, int balance, ArrayList<AdvertisementBean> ads ){
//        this.email = email;
//        this.balance = balance;
//        this.ads = ads;
//    }
//
//    public SponsorBean() {
//        super();
//    }
//
//    public SponsorBean(String username, String password, String gender, String role, int lock,
//            ArrayList<PostBean> user_posts, ArrayList<VPBean> viewed_posts, ArrayList<MessageBean> messages,
//            ArrayList<CommentBean> user_comments, int score, String email, int balance, ArrayList<AdvertisementBean> ads, ArrayList<LikeBean> likes, ArrayList<DislikeBean> dislikes) {
//
//        this.email = email;
//        this.balance = balance;
//        this.ads = ads;
//    }
//
//}

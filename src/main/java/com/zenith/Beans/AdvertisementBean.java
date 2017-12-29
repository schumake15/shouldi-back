/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zenith.Beans;

import java.io.Serializable;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AdTable")
public class AdvertisementBean implements Serializable {

    @Id 
    @Column(name = "AD_ID")
    @SequenceGenerator(sequenceName = "AD_SEQ", name = "AD_SEQ")
    @GeneratedValue(generator = "AD_SEQ", strategy = GenerationType.SEQUENCE)
    private int ad_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spon_id")
    private UserBean Sponsor;

    @Column(name = "AD_LINK")
    private String ad_link;
    
    @Column(name = "IMAGE")
    private Blob image;

    @Column(name = "CLICK_THRU")
    private int num_clicked;

    @Column(name = "NUM_SHOWN")
    private int num_shown;

    public AdvertisementBean(Blob image, String url, UserBean sponsor){
        this.image = image;
        this.ad_link = url;
        this.Sponsor = sponsor; 
    }
    /**
     * @return the ad_id
     */
    public int getAd_id() {
        return ad_id;
    }

    /**
     * @param ad_id the ad_id to set
     */
    public void setAd_id(int ad_id) {
        this.ad_id = ad_id;
    }

    /**
     * @return the Sponsor
     */
    public UserBean getSponsor() {
        return Sponsor;
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

    /**
     * @return the num_clicked
     */
    public int getNum_clicked() {
        return num_clicked;
    }

    /**
     * @param num_clicked the num_clicked to set
     */
    public void setNum_clicked(int num_clicked) {
        this.num_clicked = num_clicked;
    }

    /**
     * @return the num_shown
     */
    public int getNum_shown() {
        return num_shown;
    }
    
    public AdvertisementBean(){};

    /**
     * @param num_shown the num_shown to set
     */
    public void setNum_shown(int num_shown) {
        this.num_shown = num_shown;
    }

    /**
     * @param Sponsor the Sponsor to set
     */
    public void setSponsor(UserBean Sponsor) {
        this.Sponsor = Sponsor;
    }

    /**
     * @return the ad_link
     */
    public String getAd_link() {
        return ad_link;
    }

    /**
     * @param ad_link the ad_link to set
     */
    public void setAd_link(String ad_link) {
        this.ad_link = ad_link;
    }

    
    
}
 
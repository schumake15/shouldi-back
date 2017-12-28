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
    private String num_clicked;

    @Column(name = "NUM_SHOWN")
    private String num_shown;

    public int getAd_id() {
        return ad_id;
    }

    public void setAd_id(int ad_id) {
        this.ad_id = ad_id;
    }

    public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public String getNum_clicked() {
		return num_clicked;
	}

	public void setNum_clicked(String num_clicked) {
		this.num_clicked = num_clicked;
	}

	public String getNum_shown() {
		return num_shown;
	}

	public void setNum_shown(String num_shown) {
		this.num_shown = num_shown;
	}

	public UserBean getSponsor() {
        return Sponsor;
    }

    public void setSponsor(UserBean Sponsor) {
        this.Sponsor = Sponsor;
    }

    public String getAd_link() {
        return ad_link;
    }

    public void setAd_link(String ad_link) {
        this.ad_link = ad_link;
    }

    public AdvertisementBean(int ad_id, UserBean spon_id, String ad_link) {
        super();
        this.ad_id = ad_id;
        this.Sponsor = spon_id;
        this.ad_link = ad_link;
    }

    public AdvertisementBean(UserBean spon_id, String ad_link) {
        super();
        this.Sponsor = spon_id;
        this.ad_link = ad_link;
    }
    
    public AdvertisementBean(Blob image, String url, UserBean sponsor) {
        
        this.ad_link = url;
        this.image = image;
        this.Sponsor = sponsor;
        
    }

    public AdvertisementBean() {
        super();
    }

}

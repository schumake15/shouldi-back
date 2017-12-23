/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zenith.Beans;

import java.io.Serializable;
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
    private SponsorBean Sponsor;

    @Column(name = "AD_LINK")
    private String ad_link;

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

    public SponsorBean getSponsor() {
        return Sponsor;
    }

    public void setSponsor(SponsorBean Sponsor) {
        this.Sponsor = Sponsor;
    }

    public String getAd_link() {
        return ad_link;
    }

    public void setAd_link(String ad_link) {
        this.ad_link = ad_link;
    }

    public AdvertisementBean(int ad_id, SponsorBean spon_id, String ad_link) {
        super();
        this.ad_id = ad_id;
        this.Sponsor = spon_id;
        this.ad_link = ad_link;
    }

    public AdvertisementBean(SponsorBean spon_id, String ad_link) {
        super();
        this.Sponsor = spon_id;
        this.ad_link = ad_link;
    }

    public AdvertisementBean() {
        super();
    }

}

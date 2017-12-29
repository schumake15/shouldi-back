/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zenith.templates;

import com.zenith.ImageUtils.ImageConversionUtil;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wayne
 */
@XmlRootElement
public class AdPostTemplate {
    
    private int numberOfClicks;
    private int timesViewed;
    private String image; 
    private String url; 
    
    public AdPostTemplate(int numberOfClicks, int timesViewed, String image) {
        
        this.numberOfClicks = numberOfClicks;
        this.timesViewed = timesViewed;
        this.image = "data:image/png;base64," + image; 
        
    }
    
    public AdPostTemplate(int numberOfClicks, int timesViewed, String image, String url) {

        this.numberOfClicks = numberOfClicks;
        this.timesViewed = timesViewed;
        this.image = "data:image/png;base64," + image;
        this.url = url; 

    }
    
    public AdPostTemplate(){}; 

    /**
     * @return the numberOfClicks
     */
    public int getNumberOfClicks() {
        return numberOfClicks;
    }

    /**
     * @param numberOfClicks the numberOfClicks to set
     */
    public void setNumberOfClicks(int numberOfClicks) {
        this.numberOfClicks = numberOfClicks;
    }

    /**
     * @return the timesViewed
     */
    public int getTimesViewed() {
        return timesViewed;
    }

    /**
     * @param timesViewed the timesViewed to set
     */
    public void setTimesViewed(int timesViewed) {
        this.timesViewed = timesViewed;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
    
}

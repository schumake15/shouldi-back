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
@Table(name = "Viewed_Posts")
public class VPBean {

    @Id //designates as primary key
    @Column(name = "VP_ID")
    @SequenceGenerator(sequenceName = "VP_SEQ", name = "VP_SEQ")
    @GeneratedValue(generator = "VP_SEQ", strategy = GenerationType.SEQUENCE)
    private int vp_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserBean viewer;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostBean viewed;

    /**
     * @return the vp_id
     */
    public int getVp_id() {
        return vp_id;
    }

    /**
     * @param vp_id the vp_id to set
     */
    public void setVp_id(int vp_id) {
        this.vp_id = vp_id;
    }

    /**
     * @return the viewer
     */
    public UserBean getViewer() {
        return viewer;
    }

    /**
     * @param viewer the viewer to set
     */
    public void setViewer(UserBean viewer) {
        this.viewer = viewer;
    }

    /**
     * @return the viewed
     */
    public PostBean getViewed() {
        return viewed;
    }

    /**
     * @param viewed the viewed to set
     */
    public void setViewed(PostBean viewed) {
        this.viewed = viewed;
    }
}

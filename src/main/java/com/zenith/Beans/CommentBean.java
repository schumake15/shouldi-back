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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Comments")
public class CommentBean implements Serializable {

    @Id //designates as primary key
    @Column(name = "COMMENT_ID")
    @SequenceGenerator(sequenceName = "COMMENT_SEQ", name = "COMMENT_SEQ")
    @GeneratedValue(generator = "COMMENT_SEQ", strategy = GenerationType.SEQUENCE)
    private int comment_id;

    /* Maps to user comments */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserBean commentor;

    @Column(name = "is_flagged")
    int isFlagged;

    /* Link to Posts */
    @ManyToOne
    @JoinColumn(name = "POST_ID")
    PostBean postBean;

    @Column(name = "COMMENT_TEXT")
    private String comment_text;

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public CommentBean(int comment_id, PostBean postBean, UserBean commenter, String comment_text) {
        super();
        this.comment_id = comment_id;
        this.postBean = postBean;
        this.commentor = commenter;
        this.comment_text = comment_text;
    }

    public CommentBean(PostBean postBean, UserBean commenter, String comment_text) {
        super();
        this.postBean = postBean;
        this.commentor = commenter;
        this.comment_text = comment_text;
    }

    public CommentBean() {
        super();
    }

}

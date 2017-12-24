/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zenith.Beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author calebschumake
 */
@Entity
@XmlRootElement
@Table(name = "Messages")
public class MessageBean implements Serializable {

    @Id //designates as primary key
    @Column(name = "MESSAGE_ID")
    @SequenceGenerator(sequenceName = "MESSAGE_SEQ", name = "MESSAGE_SEQ")
    @GeneratedValue(generator = "MESSAGE_SEQ", strategy = GenerationType.SEQUENCE)
    private int message_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserBean userBean;

    @OneToOne
    @JoinColumn(name = "TO_ID")
    private UserBean to;

    @OneToOne
    @JoinColumn(name = "FROM_ID")
    private UserBean from;

    @Column(name = "CONTENT")
    private String content;

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public UserBean getTo() {
        return to;
    }

    public void setTo(UserBean to) {
        this.to = to;
    }

    public UserBean getFrom() {
        return from;
    }

    public void setFrom(UserBean from) {
        this.from = from;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageBean(int message_id, UserBean to, UserBean from, String content) {
        super();
        this.message_id = message_id;
        this.to = to;
        this.from = from;
        this.content = content;
    }

    public MessageBean(UserBean to, UserBean from, String content) {
        super();
        this.to = to;
        this.from = from;
        this.content = content;
    }

    public MessageBean() {
        super();
    }

}

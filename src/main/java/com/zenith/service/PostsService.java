package com.zenith.service;

import java.util.List;

import com.zenith.Beans.PostBean;
import com.zenith.Beans.UserBean;
import com.zenith.DAO.PostDAO;
import com.zenith.interfaces.DAO;

public class PostsService {

    PostDAO database;

    public PostsService() {
        this.database = new PostDAO();
    }

    public List<PostBean> getFlaggedPosts() {
        try {
            this.database.openConnection();
            return database.getFlaggedPosts();
        } finally {
            database.closeConnection();
        }
    }

    public PostBean getUnseenPost(UserBean user) {
        try {
            this.database.openConnection();
            return database.getUnseenPost(user);
        } finally {
            database.closeConnection();
        }
    }

    public PostBean getUnseenPostGendered(UserBean user, String gender) {
        try {
            this.database.openConnection();
            return database.getUnseenPostGendered(user, gender);
        } finally {
            database.closeConnection();
        }
    }

    public PostBean getBestEventPost(int event) {
        try {
            this.database.openConnection();
            return database.getBestEventPost(event);
        } finally {
            database.closeConnection();
        }
    }
}

package com.zenith.service;

import java.util.List;

import com.zenith.Beans.CommentBean;
import com.zenith.DAO.CommentDAO;
import com.zenith.request.model.CommentModel;

public class CommentService {

    CommentDAO database;

    public CommentService() {
        this.database = new CommentDAO();
    }

    public List<CommentBean> getFlaggedComments() {
        try {
            this.database.openConnection();
            return database.getFlaggedComments();
        } finally {
            database.closeConnection();
        }
    }
    
    public boolean flagComment(CommentModel commentModel){
        try {
            this.database.openConnection();
            return database.flagComment(commentModel); 
        } finally {
            database.closeConnection();
        }
    }

}

package com.zenith.service;

import java.util.List;

import com.zenith.Beans.UserBean;
import com.zenith.DAO.MessageDao;
import com.zenith.request.model.MessageModel;
import com.zenith.templates.MessageTemplate;

public class MessageService {
	
	MessageDao database= null;
	
    public MessageService() {
        this.database = new MessageDao();
    }
	public void sendMessage(MessageModel message) {
		MessageDao dao= new MessageDao();
		dao.sendMessage(message);
		
	}
	
	public List<MessageTemplate> getUserMessages(UserBean user)
	{
        try {
            this.database.openConnection();
            return database.getUserMessages(user);
        } finally {
            database.closeConnection();
        }
    }
	}
}

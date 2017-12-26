package com.zenith.service;

import com.zenith.DAO.MessageDao;
import com.zenith.request.model.MessageModel;

public class MessageService {
	public void sendMessage(MessageModel message) {
		MessageDao dao= new MessageDao();
		dao.sendMessage(message);
		
	}
}

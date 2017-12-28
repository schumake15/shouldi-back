package com.zenith.service;

import com.zenith.Beans.MessageBean;
import com.zenith.DAO.MessageDao;
import com.zenith.request.model.GenericGetModel;
import com.zenith.request.model.MessageModel;
import java.util.List;

public class MessageService {

    MessageDao dao = null; 
    
    public MessageService(){
        dao = new MessageDao(); 
    }
    public void sendMessage(MessageModel message) {
        dao.openConnection();
        dao.sendMessage(message); 
        dao.closeConnection();
    }
    
    public List<String> getMyMessages(GenericGetModel getModel) {
        
        return dao.getMyMessages(getModel); 
        
    }
    
    
}

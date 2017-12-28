package com.zenith.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.zenith.Beans.MessageBean;
import com.zenith.Beans.UserBean;
import com.zenith.hibernate.utils.HibernateUtil;
import com.zenith.hibernate.utils.HibernateUtils;
import com.zenith.request.model.GenericGetModel;
import com.zenith.request.model.MessageModel;

import com.zenith.templates.MessageTemplate;

import java.util.ArrayList;
import java.util.List;

public class MessageDao {

    Session session = null;

    public void openConnection() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        session = sessionFactory.openSession();
    }
	
	    public void closeConnection() {
	        if (session != null) {
	            session.close();
	        }
	    }
	    
	public void sendMessage(MessageModel message) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		MessageBean mail= new MessageBean(message.getTo(), message.getFrom(), message.getContent());
		session.save(mail);
		tx.commit();
	}
	
	public List<MessageTemplate> getUserMessages(GenericGetModel user) {
	       session.beginTransaction();
	       UserDAO dao= new UserDAO();
	        List<MessageBean> messages = session.createCriteria(MessageBean.class).list();
	        List<MessageTemplate> myMessages = new ArrayList<MessageTemplate>();
	        UserBean commenter= dao.getUserByToken(user.getToken());
	        for(MessageBean message: messages)
	        {
	        	if(message.getTo().getUser_id()==commenter.getUser_id())
	        		myMessages.add(new MessageTemplate(message.getTo().getUsername(), message.getFrom().getUsername(), message.getContent()));
	        }
	        return myMessages;
	}


    
    public List<String> getMyMessages(GenericGetModel getModel){
        UserDAO userDAO = new UserDAO();
        userDAO.openConnection();
        UserBean userBean = userDAO.getUserByToken(getModel.getToken()); 
        List<MessageBean> messages = userBean.getMessages(); 
        
        List<String> userMessages = new ArrayList<String>(); 
        for (MessageBean message : messages) {
            userMessages.add(message.getContent()); 
        }
        
        userDAO.closeConnection();
        return userMessages; 
        
    }
    
    
}

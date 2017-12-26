package com.zenith.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.zenith.Beans.MessageBean;
import com.zenith.hibernate.utils.HibernateUtils;
import com.zenith.request.model.MessageModel;

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
		Transaction tx = session.beginTransaction();
		MessageBean mail= new MessageBean(message.getTo(), message.getFrom(), message.getContent());
		session.save(mail);
		tx.commit();
	}
}

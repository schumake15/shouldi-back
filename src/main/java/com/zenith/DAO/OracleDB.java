/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zenith.DAO;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import com.zenith.Beans.PostBean;
import com.zenith.Beans.UserBean;
import com.zenith.hibernate.utils.HibernateUtils;
import com.zenith.interfaces.DAO;

/**
 *
 * @author calebschumake
 */
public class OracleDB implements DAO {

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

    public void test() {
        Transaction tx = session.beginTransaction();
        UserBean test = new UserBean();
        session.save(test);
        tx.commit();
    }

}

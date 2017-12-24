package com.zenith.DAO;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import com.zenith.Beans.UserBean;
import com.zenith.hibernate.utils.HibernateUtils;
import com.zenith.interfaces.DAO;

public class UserDAO {

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

    public UserBean getUserByEmail(String email) {

        UserBean user = null;

        /* make sure value is not null */
        if (email == null || email.isEmpty()) {
            return null;
        }

        /* Get user based on ID */
        String hql = "From UserBean E WHERE E.email = :email";
        List users
                = session.createQuery(hql).setParameter("email", email)
                        .list();
        if (users.isEmpty()) {
            return null;
        } else {
            return (UserBean) users.get(0);
        }
    }
    
    public UserBean getUserByToken(String token) {

        UserBean user = null;

        /* make sure token is not null */
        if (token == null || token.isEmpty()) {
            return null;
        }

        /* Get user based on token */
        String hql = "From UserBean E WHERE E.token = :token";
        List users
                = session.createQuery(hql).setParameter("token", token)
                        .list();
        if (users.isEmpty()) {
            return null;
        } else {
            return (UserBean) users.get(0);
        }

    }

    public void saveUser(UserBean user) {

        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();

    }

    public void updateUser(UserBean user) {

        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();

    }

    public List<UserBean> getFavoriteUsers() {

        session.beginTransaction();
        Criteria criteria;

        List<UserBean> favorites = session.createCriteria(UserBean.class).list();

        favorites = session.createCriteria(UserBean.class).add(Restrictions.gt("score", 2000)).list();
        return favorites;

    }
}

package com.zenith.DAO;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.zenith.Beans.UserBean;
import com.zenith.hibernate.utils.HibernateUtil;
import com.zenith.hibernate.utils.HibernateUtils;
import com.zenith.request.model.GenericGetModel;

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
    
    public int getUserScore(GenericGetModel requestModel){
        this.openConnection();
        UserBean user = this.getUserByToken(requestModel.getToken()); 
        this.closeConnection();
        return user.getScore(); 
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

	public UserBean getUserById(int username) {

		/* make sure value is not null */
		UserBean userBean = null;

		CriteriaBuilder cb = session.getCriteriaBuilder();

		// create criteria against a particular persistent class
		CriteriaQuery<UserBean> criteria = cb.createQuery(UserBean.class);

		String hql = "FROM UserBean E WHERE E.user_id = " + username;
		Query query = session.createQuery(hql);
		List resultList = query.list();

		if (resultList != null && resultList.size() > 0) {
			userBean = (UserBean) resultList.get(0);

		}
		System.out.println(userBean);
		return userBean;
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
    
	public void lockUser(UserBean user) {
		UserBean lockUser = null;
		System.out.println(session.createCriteria(UserBean.class).add(Restrictions.eq("user_id", user.getUser_id())));
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			lockUser = (UserBean)session.createCriteria(UserBean.class).add(Restrictions.eq("user_id", user.getUser_id())).list().get(0);
			if (lockUser != null) {
				lockUser.setLock(1);
				session.save(lockUser);
				tx.commit();
			}
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}
}

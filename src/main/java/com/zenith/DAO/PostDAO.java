package com.zenith.DAO;

import java.sql.Blob;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.zenith.Beans.AdvertisementBean;
import com.zenith.Beans.CommentBean;
import com.zenith.Beans.DislikeBean;
import com.zenith.Beans.LikeBean;
import com.zenith.Beans.PostBean;
import com.zenith.Beans.UserBean;
import com.zenith.Beans.VPBean;
import com.zenith.hibernate.utils.HibernateUtil;
import com.zenith.hibernate.utils.HibernateUtils;
import com.zenith.request.model.AdPostModel;
import com.zenith.request.model.FlagPostModel;
import com.zenith.request.model.PostModel;
import com.zenith.request.model.RatingModel;

import ImageUtils.ImageConversionUtil;

public class PostDAO {

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

    public List<PostBean> getFlaggedPosts() {

        session.beginTransaction();

        List<PostBean> flagged = session.createCriteria(PostBean.class).list();

        flagged = session.createCriteria(PostBean.class).add(Restrictions.eq("flagged", 1)).list();
        return flagged;
    }

    public PostBean getBestEventPost(int event) {
        List<PostBean> posts = session.createCriteria(PostBean.class).list();
        posts = session.createCriteria(PostBean.class).add(Restrictions.eq("event", event)).list();
        PostBean highest = null;
        for (PostBean post : posts) {
            if (highest == null) {
                highest = post;
            } else {
                if (highest.getLikes().size() < post.getLikes().size()) {
                    highest = post;
                }
            }
        }
        return highest;

    }

    public PostBean getUnseenPost(UserBean user) {

        session.beginTransaction();

        List<PostBean> choosable = session.createCriteria(PostBean.class).list();
        choosable = session.createCriteria(PostBean.class).add(Restrictions.eq("completed", 0)).list();

        List<VPBean> seen = user.getViewed_posts();
        List<PostBean> left = new ArrayList<PostBean>();
        for (VPBean vp : seen) {
            choosable.remove(vp.getViewed());
        }
        PostBean random = choosable.get(new Random().nextInt(choosable.size()));
        return random;
    }

    public PostBean getUnseenPostGendered(UserBean user, String gender) {
        session.beginTransaction();

        List<PostBean> choosable = session.createCriteria(PostBean.class).list();
        choosable = session.createCriteria(PostBean.class).add(Restrictions.eq("completed", 0)).list();

        List<VPBean> seen = user.getViewed_posts();
        List<PostBean> left = new ArrayList<PostBean>();
        for (VPBean vp : seen) {
            choosable.remove(vp.getViewed());
        }
        List<PostBean> remove = new ArrayList<PostBean>();
        for (PostBean post : choosable) {
            String postGender = post.getPoster().getGender();
            if (!gender.equalsIgnoreCase(postGender)) {
                remove.add(post);
            }
        }
        for (PostBean post : remove) {
            choosable.remove(post);
        }
        PostBean random = choosable.get(new Random().nextInt(choosable.size()));
        return random;
    }

    public void checkScore() {
        List<PostBean> posts = session.createCriteria(PostBean.class).list();
        posts = session.createCriteria(PostBean.class).add(Restrictions.eq("completed", 0)).list();
        for (PostBean post : posts) {
            Date curDate = new Date(Calendar.getInstance().getTime().getTime());
            long diff = getDateDiff(curDate, post.getCreated());
            if (diff > 7) {
                score(post);
            }
        }
    }

    private void score(PostBean post) {
        List<LikeBean> likes = post.getLikes();
        List<DislikeBean> dislikes = post.getDislikes();
        if (likes.size() > dislikes.size()) {
            for (LikeBean like : likes) {
                like.getUser().setScore(like.getUser().getScore() + 2);
            }
        } else if (likes.size() < dislikes.size()) {
            for (DislikeBean dislike : dislikes) {
                dislike.getUser().setScore(dislike.getUser().getScore() + 2);
            }
        }
        post.getPoster().setScore(post.getPoster().getScore() + (likes.size() / 10));
        post.setCompleted(1);
        session.save(post);
        session.getTransaction().commit();

    }

    private static long getDateDiff(Date date1, Date date2) {
        long difference = date2.getTime() - date1.getTime();
        difference = difference / 1000 / 60 / 60 / 24;//millisecods to days
        return difference;
    }

    public boolean createPost(PostModel postModel) {

        /* Creates new post */
        UserDAO userdao = new UserDAO();
        userdao.openConnection();
        Blob image = ImageConversionUtil.convertToBlob(postModel.getImage());
        PostBean postBean = new PostBean(image, postModel.getOccasion(), userdao.getUserByToken(postModel.getToken()));
        userdao.closeConnection();
        session.beginTransaction();
        session.save(postBean);
        session.getTransaction().commit();
        return true;
    }
    
	public void removePost(PostBean post) {
		PostBean delPost=null;
		Transaction tx=session.getTransaction();
		try {
			tx=session.beginTransaction();
			delPost=getPostById(post.getPost_id());
			if(delPost!=null) {
				session.delete(delPost);
				tx.commit();
			}
		}
		catch(HibernateException e)
		{
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}	
	}

	public void like(RatingModel rating) {
		PostBean post=null;
		Session session = HibernateUtil.getSession();
		Transaction tx=null;
		try {
			tx=session.beginTransaction();
			post=getPostById(rating.getPost().getPost_id());
			if(post!=null) {
				UserDAO udao= new UserDAO();
				LikeBean like =new LikeBean(udao.getUserById(rating.getRater().getUser_id()), post);
				
				if(!rating.getComment().equals(""))
				{
					CommentBean message= new CommentBean(post, udao.getUserById(rating.getRater().getUser_id()), rating.getComment());
				}
				session.save(like);
				tx.commit();
			}
		}
		catch(HibernateException e)
		{
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}	
	}
	
	public void dislike(RatingModel rating) {
		PostBean post=null;
		Session session = HibernateUtil.getSession();
		Transaction tx=null;
		try {
			tx=session.beginTransaction();
			post=getPostById(rating.getPost().getPost_id());
			if(post!=null) {
				UserDAO udao= new UserDAO();
				DislikeBean like =new DislikeBean(udao.getUserById(rating.getRater().getUser_id()), post);
				
				if(!rating.getComment().equals(""))
				{
					CommentBean message= new CommentBean(post, udao.getUserById(rating.getRater().getUser_id()), rating.getComment());
				}
				session.save(like);
				tx.commit();
			}
		}
		catch(HibernateException e)
		{
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}	
	}

    public boolean flagPost(FlagPostModel flagPostModel) {

        int post_id = flagPostModel.getPostID();

        /* Get post based on ID */
        String hql = "From PostBean E WHERE E.post_id = :_id";
        List posts
                = session.createQuery(hql).setParameter("_id", post_id)
                        .list();
        if (posts.isEmpty()) {
            return false;
        } else {
            session.beginTransaction();
            PostBean postBean = (PostBean) posts.get(0);
            postBean.setFlag(1);
            session.update(postBean);
            session.getTransaction().commit();
            return true;
        }

    }

    public boolean createAd(AdPostModel adPostModel) {

        /* Creates new post */
        UserDAO userdao = new UserDAO();
        userdao.openConnection();
        
        /* Remove money from the sponsors balance based on amount to pay for ad */ 
        UserBean sponsor = userdao.getUserByToken(adPostModel.getToken()); 
      
        /* create ad and save both the ad and the sponsor */
        Blob image = ImageConversionUtil.convertToBlob(adPostModel.getImage());
        AdvertisementBean adBean = new AdvertisementBean(image, adPostModel.getUrl(), sponsor);
        
        session.beginTransaction();
        session.save(adBean);
        session.getTransaction().commit();
        userdao.closeConnection();
        
        this.saveNewBalance(adPostModel); 
        
        return true;
    }
    
    /* Should make a check so user cannot go into negative balance */ 
    private void saveNewBalance(AdPostModel adPostModel) {
        
        UserDAO userdao = new UserDAO();
        userdao.openConnection();
        
        UserBean sponsor = userdao.getUserByToken(adPostModel.getToken()); 
        int currrentBalance = sponsor.getBalance(); 
        currrentBalance = currrentBalance - adPostModel.getAmountToPay(); 
        sponsor.setBalance(currrentBalance);
       
        session.beginTransaction();
        session.merge(sponsor); 
        session.getTransaction().commit();
        userdao.closeConnection();
        
    }
    
	public PostBean getPostById(int username) {

		/* make sure value is not null */
		PostBean postBean = null;

		CriteriaBuilder cb = session.getCriteriaBuilder();

		// create criteria against a particular persistent class
		CriteriaQuery<PostBean> criteria = cb.createQuery(PostBean.class);

		String hql = "FROM PostBean E WHERE E.post_id = " + username;
		Query query = session.createQuery(hql);
		List resultList = query.list();

		if (resultList != null && resultList.size() > 0) {
			postBean = (PostBean) resultList.get(0);

		}
		System.out.println(postBean);
		return postBean;
	}
}

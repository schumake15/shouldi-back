package com.zenith.DAO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import com.zenith.Beans.DislikeBean;
import com.zenith.Beans.LikeBean;
import com.zenith.Beans.PostBean;
import com.zenith.Beans.UserBean;
import com.zenith.Beans.VPBean;
import com.zenith.hibernate.utils.HibernateUtils;
import com.zenith.request.model.PostModel;

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
        Criteria criteria;

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
        Criteria criteria;

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
        Criteria criteria;

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
        PostBean postBean = new PostBean(postModel.getImage(), postModel.getOccasion(), userdao.getUserByToken(postModel.getToken()));
        userdao.closeConnection();
        session.beginTransaction();
        session.save(postBean);
        session.getTransaction().commit();;
        return true;
    }
}

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
import com.zenith.ImageUtils.ImageConversionUtil;
import com.zenith.hibernate.utils.HibernateUtils;
import com.zenith.request.model.AdPostModel;
import com.zenith.request.model.FlagPostModel;
import com.zenith.request.model.GenderedGetModel;
import com.zenith.request.model.GenericGetModel;
import com.zenith.request.model.PostModel;
import com.zenith.request.model.RatingModel;
import com.zenith.service.UserServiceImpl;
import com.google.gson.Gson;
import com.zenith.Beans.AdvertisementBean;
import java.sql.Blob;
import com.zenith.ImageUtils.ImageConversionUtil;
import com.zenith.templates.AdPostTemplate;
import com.zenith.templates.PostTemplate;
import java.util.Random;




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
    
    private PostTemplate getRandomAd() {


        /* Get post based on ID */
        String hql = "From AdvertisementBean";
        List ads
                = session.createQuery(hql)
                        .list();
        
        // number of ads is the maximum and the 0 is our minimum 
        Random rand = new Random();
        if(ads.size() == 0) {
            return null; 
        }
        int n = rand.nextInt(ads.size()) + 0;
        AdvertisementBean adBean = (AdvertisementBean)ads.get(n); 
        
        
        String image = ImageConversionUtil.convertToB64(adBean.getImage()); 
        return new PostTemplate(adBean.getNum_clicked(), adBean.getNum_shown(), image, adBean.getAd_link(), 1, adBean.getAd_id()); 

    }

    public List<AdPostTemplate> adGetMyPosts(GenericGetModel getModel) {

        String token = getModel.getToken();
        UserServiceImpl service = new UserServiceImpl();
        UserBean userBean = service.getUserByToken(token);

        /* Need to hold posts temporarily */
        List<AdvertisementBean> ads = userBean.getAds(); 

        /* All posts will be converted to a PostTemplate */
        List<AdPostTemplate> postTemplate = new ArrayList<AdPostTemplate>();
        String image;
        
        session.update(userBean);
        for (AdvertisementBean adBean : ads) {
            
            image = ImageConversionUtil.convertToB64(adBean.getImage());
            postTemplate.add(new AdPostTemplate(adBean.getNum_clicked(), adBean.getNum_shown(), image)); 
    
        }
        return postTemplate; 
    }

    public List<PostTemplate> getMyPosts(GenericGetModel getModel) {
        String token = getModel.getToken();
        UserServiceImpl service = new UserServiceImpl();
        UserBean userBean = service.getUserByToken(token);

        /* Need to hold posts temporarily */
        List<PostBean> temp = new ArrayList<PostBean>();
        /* All posts will be converted to a PostTemplate */
        List<PostTemplate> postTemplate = new ArrayList<PostTemplate>();
        /* Comments for every post */
        List<String> comments = new ArrayList<String>();

        /* Image needs to be converted to proper format for each post */
        String image;
        session.update(userBean);
        temp = userBean.getUser_posts(); 
        for (PostBean bean : temp) {
            image = ImageConversionUtil.convertToB64(bean.getImage());
            List<CommentBean> commentBean = bean.getPost_comments();
            for (CommentBean comment : commentBean) {
                comments.add(comment.getComment_text());
            }
            postTemplate.add(new PostTemplate(bean.getPost_id(), bean.getLikes().size(), image, bean.getDislikes().size(), comments));
        }
        return postTemplate;
    }

    public List<PostTemplate> getFlaggedPosts() {

        session.beginTransaction();
        List<String> comments = new ArrayList<String>(); 
        List<PostBean> flagged = session.createCriteria(PostBean.class).list();
        flagged = session.createCriteria(PostBean.class).add(Restrictions.eq("flagged", 1)).list();
        List<PostTemplate> templates=new ArrayList<PostTemplate>();
        for(PostBean post: flagged)
        {
        	String image = ImageConversionUtil.convertToB64(post.getImage());
            List<CommentBean> commentBean = post.getPost_comments(); 
            for (CommentBean comment : commentBean) {
                comments.add(comment.getComment_text()); 
            }
            templates.add(new PostTemplate(post.getPost_id(), post.getLikes().size(), image, post.getDislikes().size(), comments)); 
        }
        return templates;
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
    /*
    public List<PostTemplate> getHall() {
        List<PostBean> posts = session.createCriteria(PostBean.class).s
        posts.sort(c);;
        events= posts.
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

    }*/

    public List<PostTemplate> getUnseenPost(GenericGetModel user) {

        session.beginTransaction();
        UserDAO dao= new UserDAO();
        List<PostBean> choosable = session.createCriteria(PostBean.class).list();
        choosable = session.createCriteria(PostBean.class).add(Restrictions.eq("completed", 0)).list();
        dao.openConnection();
        UserBean viewer= dao.getUserByToken(user.getToken());
        List<VPBean> seen = viewer.getViewed_posts();
        List<PostBean> left = new ArrayList<PostBean>();
        for (VPBean vp : seen) {
            choosable.remove(vp.getViewed());
        }
        List<PostTemplate> posts= new ArrayList<PostTemplate>();
        for(int i=0; i<choosable.size();i++)
        {
        	if(i<9)
        	{
        		posts.add(new PostTemplate(choosable.get(i).getPost_id(), ImageConversionUtil.convertToB64(choosable.get(i).getImage())));
        	}
        }
        dao.closeConnection();
       
        /* Get random ad */ 
        PostTemplate model = this.getRandomAd(); 
        if (model != null){
            posts.add(model); 
        }
        
        PostBean random = choosable.get(new Random().nextInt(choosable.size()));
        return posts;
    }

    public List<PostTemplate> getUnseenPostGendered(GenderedGetModel request) {
        session.beginTransaction();
        UserDAO udao= new UserDAO();
        UserBean user= udao.getUserByToken(request.getToken());
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
            if (!request.getGender().equalsIgnoreCase(postGender)) {
                remove.add(post);
            }
        }
        for (PostBean post : remove) {
            choosable.remove(post);
        }
        List<PostTemplate> posts= new ArrayList<PostTemplate>();
        for(int i=0; i<choosable.size();i++)
        {
        	if(i<9)
        	{
        		posts.add(new PostTemplate(choosable.get(i).getPost_id(),  ImageConversionUtil.convertToB64( choosable.get(i).getImage())));
        	}
        }
        PostBean random = choosable.get(new Random().nextInt(choosable.size()));
        return posts;
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
        String s = postModel.getImage();
        s = s.substring(s.lastIndexOf(',') + 1);
        Blob image = ImageConversionUtil.convertToBlob(s);
        PostBean postBean = new PostBean(image, postModel.getOccasion(), userdao.getUserByToken(postModel.getToken()));
        userdao.closeConnection();
        session.beginTransaction();
        session.save(postBean);
        session.getTransaction().commit();
        return true;
    }

    public void removePost(PostBean post) {
        PostBean delPost = null;
        Transaction tx = session.getTransaction();
        try {
            tx = session.beginTransaction();
            delPost = (PostBean) session.get(PostBean.class, post);
            if (delPost != null) {
                session.delete(delPost);
                tx.commit();
            }
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void like(RatingModel rating) {
        PostBean post = null;
        Transaction tx = session.getTransaction();
        try {
            tx = session.beginTransaction();
            post = (PostBean) session.get(PostBean.class, rating.getPost());
            if (post != null) {
                LikeBean like=new LikeBean(rating.getRater(), rating.getPost());
                VPBean view=new VPBean(rating.getRater(), rating.getPost());
                if (!rating.getComment().equals("")) {
                    CommentBean comment= new CommentBean(rating.getPost(), rating.getRater(), rating.getComment());
                    session.save(comment);
                }
                session.save(like);
                session.save(view);
                tx.commit();
            }
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void dislike(RatingModel rating) {
        PostBean post = null;
        Transaction tx = session.getTransaction();
        try {
            tx = session.beginTransaction();
            post = (PostBean) session.get(PostBean.class, rating.getPost());
            if (post != null) {
                DislikeBean dislike=new DislikeBean(rating.getRater(), rating.getPost());
                VPBean view=new VPBean(rating.getRater(), rating.getPost());
                if (!rating.getComment().equals("")) {
                    CommentBean comment= new CommentBean(rating.getPost(), rating.getRater(), rating.getComment());
                    session.save(comment);
                }
                session.save(dislike);
                session.save(view);
                tx.commit();
            }
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
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
            UserBean user=postBean.getPoster();
            user.setFlag(1);
            session.save(user);
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

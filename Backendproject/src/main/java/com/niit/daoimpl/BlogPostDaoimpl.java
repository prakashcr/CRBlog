package com.niit.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;



import com.niit.dao.BlogPostDao;
import com.niit.model.BlogComment;
import com.niit.model.BlogPost;



@Repository("BlogDao")
@Transactional
public class BlogPostDaoimpl implements BlogPostDao {

@Autowired
private SessionFactory sessionFactory;

public void saveBlogPost(BlogPost blogPost) {
	Session session = sessionFactory.getCurrentSession();
	session.save(blogPost);

}

public List<BlogPost> getAllBlogs(int approved) {
	// Session session = sessionFactory.getCurrentSession();

	if (approved == 1) {
		return sessionFactory.getCurrentSession()
				.createQuery("from BlogPost where approved=" + approved, BlogPost.class).list();
	} // query = session.createQuery("from BlogPost where approved=" +
		// approved);
	else {
		return sessionFactory.getCurrentSession()
				.createQuery("from BlogPost where approved=0 and rejectionReason is null", BlogPost.class).list();
	}
	// query = session.createQuery("from BlogPost where approved=0 and
	// rejectionReason is null");

	// return query.getResultList();
}

public BlogPost getBlogPost(int id) {

	Session session = sessionFactory.getCurrentSession();
	BlogPost blogPost = (BlogPost) session.get(BlogPost.class, id);
	return blogPost;
}

public void updateBlogPost(BlogPost blogPost) {
	Session session = sessionFactory.getCurrentSession();
	session.update(blogPost);

}

public List<BlogPost> getApprovalStatus(@PathVariable String username) {
	Session session = sessionFactory.getCurrentSession();
	Query query = session.createQuery(
			"from BlogPost where postedBy.username=? and (approved= "+1+" or rejectionReason!=null)",BlogPost.class);

	query.setParameter(0, username);
	
	List<BlogPost> blogPosts = query.getResultList();
	return blogPosts;
}

public void addBlogComment(BlogComment blogComment) {
	Session session = sessionFactory.getCurrentSession();
	session.save(blogComment);

}

public List<BlogComment> getBlogComments(int id) {

	Session session = sessionFactory.getCurrentSession();
	Query query = session.createQuery("from BlogComment where blogPost.id=?");
	query.setParameter(0, id);
	return query.getResultList();

}


}
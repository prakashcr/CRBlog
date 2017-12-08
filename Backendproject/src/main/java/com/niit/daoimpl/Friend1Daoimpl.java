package com.niit.daoimpl;
import java.util.List;

import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.Friend1Dao;
import com.niit.model.Friend1;
import com.niit.model.User;
@Transactional
@Repository
public class Friend1Daoimpl implements Friend1Dao{
	@Autowired
	private SessionFactory sessionFactory;

	public List<User> listOfSuggestedUser(String username) {
		
		return sessionFactory.getCurrentSession().createQuery("From User",User.class).list();
	
	}
	public void friendRequest(String username, String toUsername) {
	Session session=sessionFactory.getCurrentSession();
	Friend1 friend=new Friend1();
	
	friend.setFromUser(username);
	System.out.println("from username :" + friend.getFromUser());
	
	friend.setToUser(toUsername);
	System.out.println(" to username "+ friend.getToUser());
	friend.setStatus('p');
	session.save(friend);
	
	}
	
	public List<Friend1> pendingRequests(String username) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Friend1 where toUser=? and status='p'");
		query.setParameter(0, username);
		List<Friend1> pendingRequests=query.getResultList();
		return pendingRequests;
	}
	/*
	public void updatePendingRequest(Friend1 friend) {
		Session session=sessionFactory.getCurrentSession();
		if(friend.getStatus()=='A')
			session.update(friend);//update the status from P to A
		else
			session.delete(friend);//delete the record	
	}
	
	*/
	
	
	
	public void updatePendingRequest(String fromUsername, String username, char status) {
	Session session=sessionFactory.getCurrentSession();
	Query query=session.createQuery("from Friend1 where fromUser=? and toUser=?");
	query.setParameter(0, fromUsername);
	
	query.setParameter(1, username);
	
		
	Friend1 friend=(Friend1) query.getSingleResult();
	friend.setStatus(status);
	session.update(friend);
	System.out.println("from user : "+ friend.getFromUser() + " status : " + friend.getStatus() + " to user : " + friend.getToUser() );
	
	}
	
	public List<Friend1> friends(String username) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Friend1 where (fromUser=? or toUser=?) and status=?");
		query.setParameter(0, username);
		query.setParameter(1, username);
		query.setParameter(2, 'A');
		List<Friend1> friends=query.getResultList();
		
		return friends;
	}
	
}

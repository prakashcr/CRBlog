package com.niit.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.UserDao;
import com.niit.model.User;

@Repository("userDao")
@Transactional
public class UserDaoimpl implements UserDao {
	@Autowired
	SessionFactory sessionFactory;

	public void saveUser(User user) {

		System.out.println(user.getUsername());
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
		session.flush();

	}

	@Override
	public boolean validateUser(User user) {
		try {
			user = sessionFactory.getCurrentSession().createQuery(
					"FROM User WHERE username = '" + user.getUsername() + "' OR emailId = '" + user.getEmailId() + "'",
					User.class).getSingleResult();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override

	public User check(String username, String password) {
		System.out.println("check user");
		try {
			return sessionFactory.getCurrentSession()
					.createQuery("FROM User WHERE username = '" + username + "' and password = '" + password + "'",
							User.class)
					.getSingleResult();

		} catch (Exception e) {
			System.out.println("INVALID USER ");
			return null;
		}
	}

	@Override
	public boolean updateUser(User user) {

		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User getUserByUsername(String username) {
		return sessionFactory.getCurrentSession().createQuery("FROM User WHERE username = '"+username+"'", User.class).getSingleResult();

	}

	@Override
	public User getUserByUserId(int Id) {
		return sessionFactory.getCurrentSession().get(User.class, Id);
	}
}

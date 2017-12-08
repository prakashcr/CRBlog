package com.niit.dao;

import com.niit.model.User;

public interface UserDao {

	public void saveUser(User user);

	public boolean validateUser(User user);

	public User check(String username, String password);

	public boolean updateUser(User user);

	public User getUserByUsername(String username);

	public   User getUserByUserId(int Id);
}

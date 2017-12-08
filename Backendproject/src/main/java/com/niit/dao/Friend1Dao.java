package com.niit.dao;

import java.util.List;

import com.niit.model.User;

import com.niit.model.Friend1;;

public interface Friend1Dao {
	List<User> listOfSuggestedUser(String username);

	void friendRequest(String fromUsername, String toUsername);

	List<Friend1> pendingRequests(String username);

	// void updatePendingRequest(Friend1 friend);
	void updatePendingRequest(String fromUsername, String username, char status);

	List<Friend1> friends(String username);
}

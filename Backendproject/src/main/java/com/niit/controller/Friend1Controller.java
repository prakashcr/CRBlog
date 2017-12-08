package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.Friend1Dao;
import com.niit.dao.UserDao;
import com.niit.model.Error;
import com.niit.model.Friend1;
import com.niit.model.User;

@RestController
public class Friend1Controller {
	@Autowired
	private Friend1Dao friend1Dao;

	@Autowired
	private UserDao userDAO;

	@Autowired
	User user;

	@RequestMapping("/suggestedusers/{username}")
	public ResponseEntity<?> suggestedFriends(@PathVariable String username) {

		System.out.println("entering into suggested friends");

		User user = new User();
		try {

			System.out.println("user : " + userDAO.getUserByUsername(username));
			List<User> suggestedUser = friend1Dao.listOfSuggestedUser(user.getUsername());
			return new ResponseEntity<List<User>>(suggestedUser, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Error error = new Error(1, "error");
			return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/friendrequest/{toUsername}/{username}")
	public ResponseEntity<?> friendRequest(@PathVariable String toUsername, @PathVariable String username) {

		User user = new User();

		user = userDAO.getUserByUsername(username);

		username = user.getUsername();

		System.out.println("user : " + user.getUsername());

		friend1Dao.friendRequest(username, toUsername);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/pendingrequests/{username}")
	public ResponseEntity<?> pendingRequests(@PathVariable String username) {

		System.out.println("entering into pending friends request");

		user = userDAO.getUserByUsername(username);
		System.out.println("user :" + user.getUsername());
		List<Friend1> pendingRequests = friend1Dao.pendingRequests(username);
		return new ResponseEntity<List<Friend1>>(pendingRequests, HttpStatus.OK);

	}

	/*
	 * @RequestMapping(value="/updatependingrequest/{username)") public
	 * ResponseEntity<?> updatePendingRequest(@RequestBody Friend1
	 * friend,@PathVariable String username){
	 * 
	 * System.out.println("entering into decision friends request");
	 * 
	 * user=userDAO.getUserByUsername(username); System.out.println("user :"
	 * +user.getUsername());
	 * 
	 * friend1Dao.updatePendingRequest(friend);//updated status (A/D)
	 * System.out.println("from User"+ friend.getFromUser() + "is " +
	 * friend.getStatus() ); return new
	 * ResponseEntity<Friend1>(friend,HttpStatus.OK); }
	 */
	@RequestMapping(value = "/updatependingrequests/{fromUsername}/{status}/{username}")
	public ResponseEntity<?> updatePendingReqests(@PathVariable String fromUsername, @PathVariable char status,
			@PathVariable String username) {
		Friend1 friend = new Friend1();
		try {
			System.out.println("entering into decision friends request");

			user = userDAO.getUserByUsername(username);
			System.out.println("user :" + user.getUsername());
			friend1Dao.updatePendingRequest(fromUsername, user.getUsername(), status);
			System.out.println("from User" + friend.getFromUser() + "is " + friend.getStatus());
			return new ResponseEntity<Friend1>(HttpStatus.OK);

		} catch (Exception e) {
			Error error = new Error(1, "");
			System.out.println("error " + e.getMessage());
			return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/friends/{username}")
	public ResponseEntity<?> friends(@PathVariable String username) {

		user = userDAO.getUserByUsername(username);
		System.out.println("user :" + user.getUsername());

		List<Friend1> friends = friend1Dao.friends(user.getUsername());
		return new ResponseEntity<List<Friend1>>(friends, HttpStatus.OK);
	}

}

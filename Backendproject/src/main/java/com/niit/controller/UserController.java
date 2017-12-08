package com.niit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.UserDao;
import com.niit.model.User;
import com.niit.model.Error;

@RestController
public class UserController {

	@Autowired
	private UserDao userDao;
	private User user;

	@RequestMapping(value = "/registeruser", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user) {

		System.out.println("Register controller");
		if ((userDao.validateUser(user))) {
			System.out.println("content arrived");
			System.out.println("user:" + user.getUsername() + " is already present");

			Error error = new Error(406, "User already exists...");
			return new ResponseEntity<Error>(error, HttpStatus.NOT_ACCEPTABLE);
		}
		try {
			System.out.println("hello");

			userDao.saveUser(user);
			System.out.println("User : " + user.getUsername() + " registerded sucessfully.");
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			Error error = new Error(2, "cannot register");
			return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/loginuser", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody User user) {
		System.out.println("LoginController");
		user = userDao.check(user.getUsername(), user.getPassword());
		if (user == null) {
			System.out.println("NO SUCH USER IS AVAILABLE");
			Error error = new Error(404, "User does not exist..");
			return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
		} else {
			System.out.println("Hello " + user.getUsername() + ". Logged in successfully...!!");
			user.setOnline(true);
			userDao.updateUser(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/logout")
	public ResponseEntity<?> logout(@RequestBody User user) {

		System.out.println("inside the controller");
		System.out.println("Hello " + user.getUsername() + ". Logged out successfully...!!");
		user.setOnline(false);
		userDao.updateUser(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping("/getusers/{username}")
	public ResponseEntity<?> getuserDetails(@PathVariable String username, @RequestBody User user) {
		user = userDao.getUserByUsername(username);
		System.out.println("username :" + user.getUsername());
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateprofile", method = RequestMethod.POST)
	public ResponseEntity<?> getuser(@RequestBody User user) {
		System.out.println("update ....");
		try {
			System.out.println("user" + user.getUsername() + "successfully updated");
			userDao.updateUser(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			Error error = new Error(402, "unable to update");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}
	}
}

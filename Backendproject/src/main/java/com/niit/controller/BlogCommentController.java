package com.niit.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niit.dao.BlogPostDao;
import com.niit.dao.UserDao;
import com.niit.model.BlogComment;
import com.niit.model.User;

@Controller
public class BlogCommentController {

	@Autowired
	private BlogPostDao blogPostDAO;

	@Autowired
	private UserDao userDao;

	@Autowired
	User user;

	@RequestMapping("/addcomment/{username}")
	private ResponseEntity<BlogComment> addBlogComment(@RequestBody BlogComment blogComment,
			@PathVariable String username) {
		user = userDao.getUserByUsername(username);
		blogComment.setCommentedBy(user);
		blogComment.setCommentedOn(new Date());
		System.out.println("comment by " + user.getUsername() + "comment" + blogComment.getCommentText());
		System.out.println(blogComment);
		blogPostDAO.addBlogComment(blogComment);
		return new ResponseEntity<BlogComment>(blogComment, HttpStatus.OK);
	}

	@RequestMapping("/getcomments/{id}")
	private ResponseEntity<?> getBlogComments(@PathVariable int id) {
		List<BlogComment> blogComments = blogPostDAO.getBlogComments(id);
		return new ResponseEntity<List<BlogComment>>(blogComments, HttpStatus.OK);
	}
}
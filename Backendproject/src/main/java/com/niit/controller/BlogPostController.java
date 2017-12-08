package com.niit.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.BlogPostDao;
import com.niit.dao.UserDao;
import com.niit.model.BlogPost;
import com.niit.model.Error;
import com.niit.model.User;

@RestController
public class BlogPostController {

	@Autowired
	BlogPostDao blogPostDAO;

	@Autowired
	UserDao userDao;
@Autowired
private User user;
	

	@RequestMapping(value = "/saveblogpost/{username}", method = RequestMethod.POST)
	public ResponseEntity<?> saveBlogpost( @RequestBody BlogPost blogPost,@PathVariable String username) {

		
			user = userDao.getUserByUsername(username);

			System.out.println("user :" + user.getUsername());
			blogPost.setPostedOn(new Date());
			
			blogPost.setPostedBy(user);
			System.out.println("posted by" + blogPost.getPostedBy().getUsername());
			blogPostDAO.saveBlogPost(blogPost);

			return new ResponseEntity<BlogPost>(blogPost, HttpStatus.OK);

	}

	@RequestMapping("/getallblogs/{approved}")
	public ResponseEntity<?> getAllBlogs(@PathVariable int approved) {

		try {

			List<BlogPost> blogPosts = blogPostDAO.getAllBlogs(approved);
			return new ResponseEntity<List<BlogPost>>(blogPosts, HttpStatus.OK);

		} catch (Exception e) {
			System.out.println("Error : " + e.getMessage());
			Error error = new Error(500, "error in posting");
			return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@RequestMapping("/getblogpost/{id}")
	public ResponseEntity<BlogPost> getBlogPost(@PathVariable int id) {
		BlogPost blogPost = blogPostDAO.getBlogPost(id);
		return new ResponseEntity<BlogPost>(blogPost, HttpStatus.OK);
	}

	@RequestMapping("/updateblogpost")
	public ResponseEntity<?> updateBlogpost(@RequestBody BlogPost blogpost) {
		try {
			System.out.println("blog is updated");
			if (!blogpost.isApproved() && blogpost.getRejectionReason() == null)
				System.out.println("------------------------------------- ptinting-------------------");
			blogpost.setRejectionReason("not mentioned");
			blogPostDAO.updateBlogPost(blogpost);
			return new ResponseEntity<BlogPost>(blogpost, HttpStatus.OK);
		} catch (Exception e) {
			Error error = new Error(500, "error in update posting");
			return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@RequestMapping("/blogpostapprovalstatus/{username}")
	public ResponseEntity<?> getBlogPostapproval(@PathVariable String username) {

		List<BlogPost> blogPosts = blogPostDAO.getApprovalStatus(username);
		return new ResponseEntity<List<BlogPost>>(blogPosts, HttpStatus.OK);
	}

	
}
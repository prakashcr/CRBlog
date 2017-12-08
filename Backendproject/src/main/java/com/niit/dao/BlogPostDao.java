package com.niit.dao;

import java.util.List;

import com.niit.model.BlogComment;
import com.niit.model.BlogPost;

public interface BlogPostDao{

	public void saveBlogPost(BlogPost blogPost);
	
	List<BlogPost> getAllBlogs(int approved);
	
	BlogPost getBlogPost(int id);
	
	public void updateBlogPost(BlogPost blogPost);

	List<BlogPost> getApprovalStatus(String username);

	//blogcomment
	
	void addBlogComment(BlogComment blogComment);
	
	List<BlogComment> getBlogComments(int id);
	
	

}
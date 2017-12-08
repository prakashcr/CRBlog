package com.niit.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.niit.model.BlogPost;
import com.niit.model.User;

@Entity

public class BlogComment {
@Id
@GeneratedValue(strategy =GenerationType.AUTO )
private int id;
@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
@JoinColumn(name="commentdBy")
private User commentedBy;

private Date commentedOn;
private String commentText;
@ManyToOne(fetch= FetchType.EAGER,cascade=CascadeType.ALL)
@JoinColumn(name="blogpost_id")
private BlogPost blogPost;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public User getCommentedBy() {
	return commentedBy;
}
public void setCommentedBy(User commentedBy) {
	this.commentedBy = commentedBy;
}
public Date getCommentedOn() {
	return commentedOn;
}
public void setCommentedOn(Date commentedOn) {
	this.commentedOn = commentedOn;
}
public String getCommentText() {
	return commentText;
}
public void setCommentText(String commentText) {
	this.commentText = commentText;
}
public BlogPost getBlogPost() {
	return blogPost;
}
public void setBlogPost(BlogPost blogPost) {
	this.blogPost = blogPost;
}

}

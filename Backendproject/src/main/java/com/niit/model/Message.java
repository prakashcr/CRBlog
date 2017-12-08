package com.niit.model;


public class Message {

	private String message;
	private int id;
	private String to;
	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	private String userId;

	public Message() {

	}

	public Message(int id, String message, String userId)
	{
		this.id = id;
		this.message = message;
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


}
package com.niit.controller;


import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.niit.model.Message;
import com.niit.model.OutMessage;


@Controller
public class ChatController {

	@MessageMapping("/chat")
	@SendTo("/topic/message")
	public OutMessage sendMessage(Message message){
		
		System.out.println("Message recieved: "+message.getMessage()+" from : "+message.getUserId());
		return new  OutMessage(message, new Date());
	}
}

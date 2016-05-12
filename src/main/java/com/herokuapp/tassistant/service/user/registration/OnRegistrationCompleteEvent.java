package com.herokuapp.tassistant.service.user.registration;

import org.springframework.context.ApplicationEvent;

import com.herokuapp.tassistant.database.entity.User;

public class OnRegistrationCompleteEvent extends ApplicationEvent {

	private static final long serialVersionUID = -8541626989051452230L;
	
	private final User user;
	
	public OnRegistrationCompleteEvent(User user) {
		super(user);
		this.user = user;
	}

	public User getUser() {
		return user;
	}

}

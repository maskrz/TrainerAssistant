package com.herokuapp.tassistant.service.user.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.herokuapp.tassistant.service.email.EmailService;

@Component("registrationListener")
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

	@Autowired 
	private EmailService emailService;
	
	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {
		getEmailService().sendConfirmationEmail(event.getUser());		
	}

	public EmailService getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

}

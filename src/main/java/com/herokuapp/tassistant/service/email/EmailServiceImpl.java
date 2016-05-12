package com.herokuapp.tassistant.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.herokuapp.tassistant.database.entity.User;
import com.herokuapp.tassistant.service.configuration.ConfigurationService;
import com.herokuapp.tassistant.service.configuration.PropertyName;

@Service("emailService")
public class EmailServiceImpl implements EmailService {

	@Autowired
	private MailSender mailSender;

	@Autowired
	private ConfigurationService configurationService;

	@Override
	public void sendConfirmationEmail(User user) {
		if (getConfigurationService().getBooleanProperty(PropertyName.SEND_EMAIL)) {
			getMailSender().send(createConfirmationMessage(user));
		}
	}

	private SimpleMailMessage createConfirmationMessage(User user) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(getConfigurationService().getProperty(PropertyName.APPLICATION_EMAIL));
		message.setTo(user.getEmail());
		message.setSubject("Confirmation Link");
		message.setText(createMessageText(user));
		return message;
	}

	private String createMessageText(User user) {
		StringBuilder messageBuilder = new StringBuilder();
		messageBuilder.append("Twoj link weryfikacyjny to: ").append(System.getProperty("line.separator"))
				.append(getConfigurationService().getProperty((PropertyName.CONFIRMATION_LINK_PATTERN), user.getEmail(),
						user.getPasswordHash()));
		return messageBuilder.toString();
	}

	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

}

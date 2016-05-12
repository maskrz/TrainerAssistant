package com.herokuapp.tassistant.service.email;

import com.herokuapp.tassistant.database.entity.User;

public interface EmailService {

	public void sendConfirmationEmail(User user);

}

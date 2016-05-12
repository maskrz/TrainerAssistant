package com.herokuapp.tassistant.service.user;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.herokuapp.tassistant.bean.general.Response;
import com.herokuapp.tassistant.database.dao.user.UserDAO;
import com.herokuapp.tassistant.database.entity.User;
import com.herokuapp.tassistant.service.user.registration.OnRegistrationCompleteEvent;
import com.herokuapp.tassistant.util.security.SecurityUtil;
import com.herokuapp.tassistant.util.validation.ValidationException;
import com.herokuapp.tassistant.util.validation.ValidationUtil;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private SecurityUtil securityUtil;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private ValidationUtil validationUtil;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public Response registerUser(User user) {
		Response response = new Response();
		try {
			getValidationUtil().validate(user);
			String passwordHash = getSecurityUtil().generateHash(user.getEmail());
			user.setPasswordHash(passwordHash);
			getUserDAO().createUser(user);
			getApplicationEventPublisher().publishEvent(new OnRegistrationCompleteEvent(user));
			response.setMessage("Activation link has been sent");
		} catch (ValidationException ex) {
			response.setMessage(ex.getMessage());
		}
		return response;
	}

	@Override
	public Response confirmRegistration(String email, String token) {
		Response response = new Response();
		Optional<User> userOpt = getUserDAO().getUserByColumnValue("email", email);
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			if (user.getPasswordHash() != null && user.getPasswordHash().equals(token)) {
				user.setActive(true);
				user.setPasswordHash(null);
				getUserDAO().updateUser(user);
				response.setMessage("User Activated");
			} else {
				response.setMessage("Wrong token!");
			}
		} else {
			response.setMessage("Wrong token!");
		}		
		return response;
	}

	@Override
	public Optional<User> getUserByColumnValue(String columnName, String value) {
		return getUserDAO().getUserByColumnValue(columnName, value);
	}

	public SecurityUtil getSecurityUtil() {
		return securityUtil;
	}

	public void setSecurityUtil(SecurityUtil securityUtil) {
		this.securityUtil = securityUtil;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public ValidationUtil getValidationUtil() {
		return validationUtil;
	}

	public void setValidationUtil(ValidationUtil validationUtil) {
		this.validationUtil = validationUtil;
	}

	public ApplicationEventPublisher getApplicationEventPublisher() {
		return applicationEventPublisher;
	}

	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

}

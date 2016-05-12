package com.herokuapp.tassistant.util.validation.user.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.herokuapp.tassistant.service.user.UserService;
import com.herokuapp.tassistant.util.validation.user.annotation.UniqueValue;

@Component
public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object>  {

	@Autowired
	private UserService userService;
	
	private String columnName;
	
	@Override
	public void initialize(UniqueValue constraintAnnotation) {
		columnName = constraintAnnotation.columnName();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value != null && getUserService() != null) {
			if (!getUserService().getUserByColumnValue(getColumnName(), value.toString()).isPresent()) {
				return true;
			}
		}
		return false;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

}

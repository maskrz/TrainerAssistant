package com.herokuapp.tassistant.util.validation.user.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.herokuapp.tassistant.util.validation.user.annotation.Email;

public class EmailValidator implements ConstraintValidator<Email, Object> {
	
	@Override
	public void initialize(Email constraintAnnotation) {
		// do nothing
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		if(value != null && !value.toString().isEmpty()){
			return Pattern.matches(".+[@]{1}.+[.]{1}.+", value.toString());
		} else {
			return false;
		}
	}

}

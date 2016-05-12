package com.herokuapp.tassistant.util.validation.user.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.herokuapp.tassistant.util.validation.user.annotation.NotEmpty;

public class NotEmptyValidator implements ConstraintValidator<NotEmpty, Object>  {
	
	@Override
	public void initialize(NotEmpty constraintAnnotation) {
		// do nothing
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		return value != null && !value.toString().isEmpty();
	}
}

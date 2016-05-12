package com.herokuapp.tassistant.util.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("validationUtil")
public class ValidationUtilImpl implements ValidationUtil {

	@Autowired
	private Validator validator;

	@Override
	public <T> void validate(T obj) throws ValidationException {
		if (obj != null) {
			Set<ConstraintViolation<T>> results = getValidator().validate(obj);
			if (results.size() > 0) {
				List<ConstraintViolation<T>> sortedResults = new ArrayList<ConstraintViolation<T>>();
				sortedResults.addAll(results);
				sortedResults.sort((ConstraintViolation<T> object1, ConstraintViolation<T> object2) -> object1
						.getPropertyPath().toString().compareTo(object2.getPropertyPath().toString()));
				StringJoiner msg = new StringJoiner(", ");
				sortedResults.forEach(result -> {
					if (!result.getPropertyPath().toString().isEmpty()) {
						msg.add(createValidatorMessage(result));
					} else {
						msg.add(result.getMessageTemplate());
					}
				});
				throw new ValidationException(msg.toString());
			}
		}

	}

	private <T> String createValidatorMessage(ConstraintViolation<T> constraintViolation) {
		return constraintViolation.getRootBeanClass().getSimpleName() + "." + constraintViolation.getPropertyPath()
				+ "=" + constraintViolation.getInvalidValue() + " " + constraintViolation.getMessage();
	}

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

}

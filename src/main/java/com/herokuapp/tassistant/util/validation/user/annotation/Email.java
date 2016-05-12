package com.herokuapp.tassistant.util.validation.user.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import com.herokuapp.tassistant.util.validation.user.validator.EmailValidator;

@Target(FIELD)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy={EmailValidator.class})
@ReportAsSingleViolation
public @interface Email {

	String message() default "Invalid email";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};

}

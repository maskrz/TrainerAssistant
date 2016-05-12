package com.herokuapp.tassistant.util.validation.user.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import com.herokuapp.tassistant.util.validation.user.validator.NotEmptyValidator;

@Target(FIELD)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy={NotEmptyValidator.class})
@ReportAsSingleViolation
public @interface NotEmpty {

	String message() default "Empty value";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};

}

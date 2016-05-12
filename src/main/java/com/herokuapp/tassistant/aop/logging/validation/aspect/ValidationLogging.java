package com.herokuapp.tassistant.aop.logging.validation.aspect;


import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationLogging {
	
	private final static Logger logger = Logger.getLogger(ValidationLogging.class.getName());

	@Pointcut("execution(* com.herokuapp.tassistant.util.validation.ValidationUtil.validate(*))")
	private void validate(){}
	
	@Before("validate()")
	private void beforeValidation(JoinPoint joinPoint) {
		logger.info("Validating boject, type: " + joinPoint.getArgs()[0].getClass() + ", string representation: "+ joinPoint.getArgs()[0].toString());
	}
	
	@AfterThrowing(pointcut = "validate()", throwing = "error")
	private void afterThrowing(JoinPoint joinPoint, Throwable error) {
		logger.info("Exception during validation: " + error);
	}
	
	@After("validate()")
	private void afterValidation() {
		logger.info("Validataion complete");
	}
}

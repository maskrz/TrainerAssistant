package com.herokuapp.tassistant.aop.logging.registration.aspect;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.herokuapp.tassistant.util.calendar.DateTimeUtil;

@Component
@Aspect
public class RegistrationLogging {

	private final static Logger logger = Logger.getLogger(RegistrationLogging.class.getName());

	private LocalDateTime start;
	private LocalDateTime end;

	@Autowired
	private DateTimeUtil dateTimeUtil;

	@Pointcut("execution(* com.herokuapp.tassistant.service.user.UserService.registerUser(..))")
	private void registerUser() {
	}

	@Before("registerUser()")
	private void beforeRegistration(JoinPoint joinPoint) {
		setStart(getDateTimeUtil().currentTimestamp());
		logger.info("Registration requested for: " + joinPoint.getArgs()[0].toString());
	}

	@After("registerUser()")
	private void afterRegistration(JoinPoint joinPoint) {
		setEnd(getDateTimeUtil().currentTimestamp());
		logger.info("End user registration, total time: " + (getDateTimeUtil().calculateDuration(getStart(), getEnd()))
				+ ", user: " + joinPoint.getArgs()[0].toString());
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public DateTimeUtil getDateTimeUtil() {
		return dateTimeUtil;
	}

	public void setDateTimeUtil(DateTimeUtil dateTimeUtil) {
		this.dateTimeUtil = dateTimeUtil;
	}
}

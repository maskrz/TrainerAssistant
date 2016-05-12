package com.herokuapp.tassistant.util.calendar;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component("dateTimeUtil")
public class DateTimeUtilImpl implements DateTimeUtil {

	@Override
	public boolean isExpired(LocalDateTime localDateTime) {
		return localDateTime == null || localDateTime.isAfter(LocalDateTime.now());
	}

	@Override
	public LocalDateTime currentTimestamp() {
		return LocalDateTime.now();
	}

	@Override
	public Duration calculateDuration(LocalDateTime start, LocalDateTime end) {
		return Duration.between(start, end);
	}
	
	
}

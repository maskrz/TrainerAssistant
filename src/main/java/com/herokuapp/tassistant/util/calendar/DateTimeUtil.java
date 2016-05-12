package com.herokuapp.tassistant.util.calendar;

import java.time.Duration;
import java.time.LocalDateTime;

public interface DateTimeUtil {

	public boolean isExpired(LocalDateTime localDateTime);
	
	public LocalDateTime currentTimestamp();
	
	public Duration calculateDuration(LocalDateTime start, LocalDateTime end);
}

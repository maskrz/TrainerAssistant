package com.herokuapp.tassistant.util.calendar;

import java.time.Duration;
import java.time.LocalDateTime;

public interface DateTimeUtil {

	public boolean isExpired(LocalDateTime localDateTime);
	
	public LocalDateTime currentTimestamp();
	
	public Duration calculateDuration(LocalDateTime start, LocalDateTime end);
	
	public LocalDateTime toDateTime(String date, String format);

	public boolean sameDate(LocalDateTime date1, LocalDateTime date2);
}

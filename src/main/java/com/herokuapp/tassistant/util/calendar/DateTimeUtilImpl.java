package com.herokuapp.tassistant.util.calendar;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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

	@Override
	public LocalDateTime toDateTime(String date, String format) {
		// TODO refactor
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return LocalDateTime.of(LocalDate.parse(date, formatter), createEmptyTime());
	}

	private LocalTime createEmptyTime() {
		return LocalTime.of(0, 0);
	}

	@Override
	public boolean sameDate(LocalDateTime date1, LocalDateTime date2) {
		return (date1.getDayOfMonth() == date2.getDayOfMonth()) && (date1.getMonthValue() == date2.getMonthValue())
				&& (date1.getYear() == date2.getYear());
	}

}

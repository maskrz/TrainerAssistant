package com.herokuapp.tassistant.database.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;

import com.herokuapp.tassistant.util.calendar.LocalDateTimeConverter;

@MappedSuperclass
public abstract class Tracker extends Logger {
	
	/** time stamp of entity update */
	@Column(name="refresh_timestamp")
	@Convert(converter=LocalDateTimeConverter.class)
	private LocalDateTime refreshTimestamp;

	@Override
	public String toString() {
		return "Tracker [refreshTimestamp=" + refreshTimestamp + ", "
				+ super.toString() + "]";
	}

	public LocalDateTime getRefreshTimestamp() {
		return refreshTimestamp;
	}

	public void setRefreshTimestamp(LocalDateTime refreshTimestamp) {
		this.refreshTimestamp = refreshTimestamp;
	}
}

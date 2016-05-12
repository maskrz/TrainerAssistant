package com.herokuapp.tassistant.database.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;

import com.herokuapp.tassistant.util.calendar.LocalDateTimeConverter;

@MappedSuperclass
public abstract class Logger {

	/** time stamp of entity creation */
	@Column(name="create_timestamp")
	@Convert(converter=LocalDateTimeConverter.class)
	private LocalDateTime createTimestamp;

	@Override
	public String toString() {
		return "Logger [createTimestamp=" + createTimestamp + "]";
	}

	public LocalDateTime getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(LocalDateTime createTimestamp) {
		this.createTimestamp = createTimestamp;
	}
}

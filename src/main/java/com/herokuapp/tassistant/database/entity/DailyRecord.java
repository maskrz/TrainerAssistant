package com.herokuapp.tassistant.database.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DAILY_RECORD")
public class DailyRecord {

	@EmbeddedId
	private DailyRecordId dailyRecordId;

	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false, insertable=false, updatable = false)
	private User user;
	
	@Column(name = "T1")
	private Integer t1;
	
	@Column(name = "T2")
	private Integer t2;
	
	@Column(name = "T3")
	private Integer t3;
	
	@Column(name = "WSR")
	private Double wsr;

	public DailyRecordId getDailyRecordId() {
		return dailyRecordId;
	}

	public void setDailyRecordId(DailyRecordId dailyRecordId) {
		this.dailyRecordId = dailyRecordId;
	}

	public Integer getT1() {
		return t1;
	}

	public void setT1(Integer t1) {
		this.t1 = t1;
	}

	public Integer getT2() {
		return t2;
	}

	public void setT2(Integer t2) {
		this.t2 = t2;
	}

	public Integer getT3() {
		return t3;
	}

	public void setT3(Integer t3) {
		this.t3 = t3;
	}

	public Double getWsr() {
		return wsr;
	}

	public void setWsr(Double wsr) {
		this.wsr = wsr;
	}
}
package com.herokuapp.tassistant.database.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.herokuapp.tassistant.util.validation.user.annotation.Email;
import com.herokuapp.tassistant.util.validation.user.annotation.NotEmpty;
import com.herokuapp.tassistant.util.validation.user.annotation.UniqueValue;

@Entity
@Table(name = "USER_ACCOUNT")
public class User extends Tracker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Integer userId;

	@NotEmpty
	@Column(name = "FIRST_NAME")
	private String firstName;

	@NotEmpty
	@Column(name = "LAST_NAME")
	private String lastName;

	@NotEmpty
	@UniqueValue(message = "User with this email exists in database", columnName = "email")
	@Email
	@Column(name = "EMAIL")
	private String email;

	@NotEmpty
	@UniqueValue(message = "User with this login exists in database", columnName = "login")
	@Column(name = "LOGIN")
	private String login;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "PASSWORD_HASH")
	private String passwordHash;
	
	@Column(name = "ACTIVE")
	private Boolean active;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade=CascadeType.MERGE)
	private List<DailyRecord> dailyRecords;
	
	public User(){}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String passoword) {
		this.password = passoword;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", login=" + login + ", password=" + password + ", passwordHash=" + passwordHash + ", active="
				+ active + ", " + super.toString() + "]";
	}

	public List<DailyRecord> getDailyRecords() {
		return dailyRecords;
	}

	public void setDailyRecords(List<DailyRecord> dailyRecords) {
		this.dailyRecords = dailyRecords;
	}

}

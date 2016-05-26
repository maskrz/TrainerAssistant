package com.herokuapp.tassistant.service.user;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.herokuapp.tassistant.bean.general.DailyRecordBean;
import com.herokuapp.tassistant.bean.general.Response;
import com.herokuapp.tassistant.database.entity.DailyRecord;
import com.herokuapp.tassistant.database.entity.User;

public interface UserService {

	@Transactional
	public Response registerUser(User user);

	@Transactional
	public Response confirmRegistration(String email, String token);

	@Transactional
	public Optional<User> getUserByColumnValue(String columnName, String value);

	@Transactional
	public DailyRecord addRecord(User user, DailyRecordBean dailyRecordBean);
}

package com.herokuapp.tassistant.database.dao.user;

import java.util.Optional;

import com.herokuapp.tassistant.database.entity.User;

public interface UserDAO {

	public void createUser(User user);
	
	public void updateUser(User user);

	public Optional<User> getUserByColumnValue(String columnName, String value);
}

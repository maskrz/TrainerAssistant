package com.herokuapp.tassistant.database.dao.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.herokuapp.tassistant.database.dao.GeneralRepository;
import com.herokuapp.tassistant.database.entity.User;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

	@Autowired
	private GeneralRepository generalRepository;

	@Override
	public void createUser(User user) {
		getGeneralRepository().add(user);
	}

	@Override
	public void updateUser(User user) {
		getGeneralRepository().merge(user);
	}

	@Override
	public Optional<User> getUserByColumnValue(String columnName, String value) {
		String query = "SELECT * FROM USER_ACCOUNT WHERE " + columnName + " = :value";
		List<User> results = getGeneralRepository().createNativeQuery(query, User.class).setParameter("value", value).getResultList();
		if (results.size() == 0) {
			return Optional.empty();
		} else {
			return Optional.ofNullable(results.get(0));
		}
	}

	public GeneralRepository getGeneralRepository() {
		return generalRepository;
	}

	public void setGeneralRepository(GeneralRepository generalRepository) {
		this.generalRepository = generalRepository;
	}

}

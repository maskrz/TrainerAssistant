package com.herokuapp.tassistant.database.dao;

import java.util.Optional;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

public interface GeneralRepository {

	public <T> T add(T entity);
	@Transactional
	public <T> Optional<T> findById(Class<? extends T> entityClass, Object key);
	
	public Query createNativeQuery(String query);

	public <T> TypedQuery<T> createNativeQuery(String query, Class<? extends T> clazz);
	@Transactional
	public <T> T merge(T entity);
	
	public <T> Optional<T> find(T entity);
}

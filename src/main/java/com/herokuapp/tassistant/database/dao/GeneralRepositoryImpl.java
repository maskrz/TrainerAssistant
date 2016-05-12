package com.herokuapp.tassistant.database.dao;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.herokuapp.tassistant.database.entity.Logger;
import com.herokuapp.tassistant.database.entity.Tracker;
import com.herokuapp.tassistant.util.calendar.DateTimeUtil;

@Repository("generalRepository")
public class GeneralRepositoryImpl implements GeneralRepository {

	/** entity manager */
	@PersistenceContext(name = "taPU")
	private EntityManager entityManager;
	
	@Autowired
	private DateTimeUtil dateTimeUtil;

	@Override
	public <T> T add(T entity) {
		if (entity instanceof Logger) {
			((Logger) entity).setCreateTimestamp(getDateTimeUtil().currentTimestamp());
			((Tracker) entity).setRefreshTimestamp(getDateTimeUtil().currentTimestamp());
		}
		getEntityManager().persist(entity);
		return entity;
	}

	@Override
	public <T> Optional<T> findById(Class<? extends T> entityClass, Object key) {
		if (key != null) {
			return Optional.ofNullable(getEntityManager().find(entityClass, key));
		}
		return null;
	}

	@Override
	public Query createNativeQuery(String query) {
		return getEntityManager().createNativeQuery(query);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> TypedQuery<T> createNativeQuery(String query, Class<? extends T> clazz) {
		return (TypedQuery<T>) getEntityManager().createNativeQuery(query, clazz);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public <T> T merge(T entity) {
		if (entity != null) {
			Optional<T> existedEntity = find(entity);
			if (existedEntity.isPresent()) {
				return update(entity, existedEntity.get());
			} else {
				return create(entity);
			}
		}
		return entity;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> Optional<T> find(T entity) {
		if (entity != null) {
			Object key = getEntityManager().getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
			if (key != null) {
				return Optional.ofNullable((T) getEntityManager().find(entity.getClass(), key));
			}
		}
		return null;
	}

	protected <T> T update(T newEntity, T existedEntity) {
		if (newEntity instanceof Tracker && existedEntity instanceof Tracker) {
			((Tracker)newEntity).setRefreshTimestamp(getDateTimeUtil().currentTimestamp());
		} 
		return getEntityManager().merge(newEntity);

	}
	
	protected <T> T create(T entity) {
		if(entity instanceof Logger){	
			((Logger)entity).setCreateTimestamp(getDateTimeUtil().currentTimestamp());
		}
		getEntityManager().persist(entity);
		return entity;
	}

	public DateTimeUtil getDateTimeUtil() {
		return dateTimeUtil;
	}

	public void setDateTimeUtil(DateTimeUtil dateTimeUtil) {
		this.dateTimeUtil = dateTimeUtil;
	}

}

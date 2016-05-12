package com.herokuapp.tassistant.database.dao.configuration;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.herokuapp.tassistant.database.entity.Property;

/**
 * Data source operations for configuration. 
 * @author bplc904
 * @version 6.0
 */
public interface ConfigurationDAO {

	/**
	 * Get all properties
	 * @return all properties
	 */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public List<Property> getPropertyList();
}

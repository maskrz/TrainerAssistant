package com.herokuapp.tassistant.service.configuration;

import java.util.List;

import com.herokuapp.tassistant.database.entity.Property;

public interface ConfigurationService {

	public String getProperty(PropertyName propertyName);
	
	public String getProperty(PropertyName propertyName, String... bundles);
	
	public boolean getBooleanProperty(PropertyName propertyName);
	
	public List<Property> getAllProperties();
}

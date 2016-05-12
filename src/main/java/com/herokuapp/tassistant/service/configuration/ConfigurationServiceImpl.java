package com.herokuapp.tassistant.service.configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herokuapp.tassistant.database.dao.configuration.ConfigurationDAO;
import com.herokuapp.tassistant.database.entity.Property;
import com.herokuapp.tassistant.util.calendar.DateTimeUtil;

@Service("configurationService")
public class ConfigurationServiceImpl implements ConfigurationService {

	public static final String BUNDLE_CHARACTER = "#";

	@Autowired
	private ConfigurationDAO configurationDAO;

	@Autowired
	private DateTimeUtil dateTimeUtil;

	// TODO @Value("${configuration.refreshMinutes}")
	private Integer configurationRefreshMinutes = 15;

	private LocalDateTime refrestDateTime;

	private Map<PropertyName, String> propertyMap;

	@Override
	public String getProperty(PropertyName propertyName) {
		return getPropertyMap().get(propertyName);
	}

	@Override
	public String getProperty(PropertyName propertyName, String... bundles) {
		String property = getPropertyMap().get(propertyName);
		for (String bundle : bundles) {
			property = property.replaceFirst(BUNDLE_CHARACTER, bundle);
		}
		return property;
	}

	@Override
	public boolean getBooleanProperty(PropertyName propertyName) {
		return Boolean.valueOf(getProperty(propertyName));
	}

	@Override
	public List<Property> getAllProperties() {
		List<Property> properties = new ArrayList<Property>();
		for (Map.Entry<PropertyName, String> prop : getPropertyMap().entrySet()) {
			Property property = new Property();
			property.setPropertyName(prop.getKey());
			property.setPropertyValue(prop.getValue());
			properties.add(property);
		}
		return properties;
	}

	protected synchronized Map<PropertyName, String> getPropertyMap() {
		if (propertyMap == null || getDateTimeUtil().isExpired(getRefreshDateTime())) {
			setPropertyMap(null);
			setRefreshDateTime(null);
			List<Property> propertyList = getConfigurationDAO().getPropertyList();
			setPropertyMap(propertyList.stream().collect(
					Collectors.toMap(property -> property.getPropertyName(), property -> property.getPropertyValue())));
			setRefreshDateTime(LocalDateTime.now().plusMinutes(getConfigurationRefreshMinutes()));
		}
		return propertyMap;
	}

	public synchronized void setPropertyMap(Map<PropertyName, String> propertyMap) {
		this.propertyMap = propertyMap;
	}

	public synchronized LocalDateTime getRefreshDateTime() {
		return refrestDateTime;
	}

	public synchronized void setRefreshDateTime(LocalDateTime refrestDateTime) {
		this.refrestDateTime = refrestDateTime;
	}

	public ConfigurationDAO getConfigurationDAO() {
		return configurationDAO;
	}

	public void setConfigurationDAO(ConfigurationDAO configurationDAO) {
		this.configurationDAO = configurationDAO;
	}

	public Integer getConfigurationRefreshMinutes() {
		return configurationRefreshMinutes;
	}

	public void setConfigurationRefreshMinutes(Integer configurationRefreshMinutes) {
		this.configurationRefreshMinutes = configurationRefreshMinutes;
	}

	public DateTimeUtil getDateTimeUtil() {
		return dateTimeUtil;
	}

	public void setDateTimeUtil(DateTimeUtil dateTimeUtil) {
		this.dateTimeUtil = dateTimeUtil;
	}
}

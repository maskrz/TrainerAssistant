package com.herokuapp.tassistant.database.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import com.herokuapp.tassistant.service.configuration.PropertyName;

@Entity(name="PROPERTY")
public class Property implements Serializable{

	/** serial id */
	private static final long serialVersionUID = -1495803621865792279L;

	@Id 
	@Column(name="NAME", nullable=false) 
	@Enumerated(EnumType.STRING)
	private PropertyName propertyName;
	
	@Column(name="VALUE")	
	@Size(max=3800)
	private String propertyValue;

	public PropertyName getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(PropertyName propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}
}

package com.herokuapp.tassistant.util.validation;

public interface ValidationUtil {
	
	public <T> void validate(T obj) throws ValidationException;

}

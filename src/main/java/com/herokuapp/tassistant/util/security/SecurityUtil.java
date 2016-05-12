package com.herokuapp.tassistant.util.security;

public interface SecurityUtil {

	public String generateHash(String input);
	
	public String generateHash(String input, String method);
}

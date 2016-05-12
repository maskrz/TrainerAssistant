package com.herokuapp.tassistant.util.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herokuapp.tassistant.service.configuration.ConfigurationService;
import com.herokuapp.tassistant.service.configuration.PropertyName;

@Service("securityUtil")
public class SecurityUtilImpl implements SecurityUtil {
	
	final private static char[] hexArray = "0123456789ABCDEF".toCharArray();

	@Autowired
	private ConfigurationService configurationService;

	@Override
	public String generateHash(String input) {
		return generateHash(input, getConfigurationService().getProperty(PropertyName.HASH_METHOD));
	}

	@Override
	// TODO - collections (maybe in the future - Arrays.stream(bytes) - no method);
	public String generateHash(String input, String method) {
//		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
//		return encoder.encodePassword(input, null);
		// TODO clean!
		try {
			byte[] bytes = input.getBytes();
			MessageDigest md = MessageDigest
					.getInstance(method);
			byte[] byteData = md.digest(bytes);
			return bytesToString(byteData);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	private String bytesToString(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}

	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

}

package com.herokuapp.tassistant.bean.general;

public class Response {

	private String message;
	private ResponseCode responseCode;

	public Response() {
	}

	public Response(String message) {
		setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResponseCode getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(ResponseCode responseCode) {
		this.responseCode = responseCode;
	}

}

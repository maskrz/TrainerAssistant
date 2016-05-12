package com.herokuapp.tassistant.util.validation;

public class ValidationException extends RuntimeException{

	/** serial id */
	private static final long serialVersionUID = 4573984530257118662L;

	public ValidationException() {
		super();
	}
	
	public ValidationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ValidationException(String arg0) {
		super(arg0);
	}

	public ValidationException(Throwable arg0) {
		super(arg0);
	}
}

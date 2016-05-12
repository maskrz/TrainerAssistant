package com.herokuapp.tassistant.util.reflection;

/**
 * Exception for reflection tools
 * @author bplc904
 * @version 1.0
 */
public class ReflectionException extends RuntimeException{

	/** serial id */
	private static final long serialVersionUID = 4573984530257118662L;

	public ReflectionException() {
		super();
	}
	
	public ReflectionException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ReflectionException(String arg0) {
		super(arg0);
	}

	public ReflectionException(Throwable arg0) {
		super(arg0);
	}
}

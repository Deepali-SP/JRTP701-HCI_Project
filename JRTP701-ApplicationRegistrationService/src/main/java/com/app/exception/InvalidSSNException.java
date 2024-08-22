package com.app.exception;

public class InvalidSSNException extends Exception{
 
	private static final long serialVersionUID = 1L;
	public InvalidSSNException() {
		super();
	};
	public InvalidSSNException(String msg) {
		super(msg);
	};
}

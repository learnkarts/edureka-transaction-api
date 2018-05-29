package com.edureka.github.exceptions;

public class InvalidSwaggerFileException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidSwaggerFileException(String message) {
		super(message);
	}

	public InvalidSwaggerFileException(String message, Throwable t) {
		super(message, t);
	}
}

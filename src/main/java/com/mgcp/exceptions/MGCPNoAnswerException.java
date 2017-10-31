package com.mgcp.exceptions;

public class MGCPNoAnswerException extends Exception {
	private static final long serialVersionUID = 1L;

	public MGCPNoAnswerException(String exception) {
		super(exception);
	}
}

package de.proglabor.aufgabe4.exceptions;

@SuppressWarnings("serial")
public class WrongValueException extends Exception {
	public WrongValueException(String msg) {
		super(msg);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}

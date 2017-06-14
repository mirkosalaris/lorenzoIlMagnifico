package it.polimi.ingsw.GC_36.exception;

public class InsufficientResourcesException extends Exception {
	public InsufficientResourcesException() {
		super();
	}

	public InsufficientResourcesException(String s) {
		super(s);
	}

	public InsufficientResourcesException(String s, Throwable throwable) {
		super(s, throwable);
	}
}

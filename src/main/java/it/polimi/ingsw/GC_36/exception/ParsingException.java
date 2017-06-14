package it.polimi.ingsw.GC_36.exception;

public class ParsingException extends RuntimeException {
	public ParsingException() {
	}

	public ParsingException(String s) {
		super(s);
	}

	public ParsingException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public ParsingException(Throwable throwable) {
		super(throwable);
	}
}

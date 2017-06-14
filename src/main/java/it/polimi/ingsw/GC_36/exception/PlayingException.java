package it.polimi.ingsw.GC_36.exception;

/**
 * Called when an exception is raised during a turn
 */
public class PlayingException extends Exception {
	public PlayingException() {
		super();
	}

	public PlayingException(String message) {
		super(message);
	}

	public PlayingException(String message, Throwable throwable) {
		super(message, throwable);
	}
}

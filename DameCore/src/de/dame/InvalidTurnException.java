package de.dame;

/**
 * Zeigt an, dass ein Spielzug ungültig ist.
 */
public class InvalidTurnException extends Exception {

	private static final long serialVersionUID = -8694888855718093858L;

	public InvalidTurnException(String message) {
		super(message);
	}

}

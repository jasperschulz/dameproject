package de.dame;

import java.util.Vector;

public abstract class Player {

	public abstract Vector<Turn> getNextTurn(Vector<Turn> lastTurn)
			throws InvalidTurnException;

}

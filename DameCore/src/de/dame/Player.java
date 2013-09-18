package de.dame;

import java.util.Vector;

public abstract class Player {

	public static final String WHITE = "white";

	public static final String BLACK = "black";

	private String color;

	 

	public abstract Vector<Turn> getNextTurn(Vector<Turn> lastTurn)

	throws InvalidTurnException;

	 

	public abstract void setColor(String color);

}

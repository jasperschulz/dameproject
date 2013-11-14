package de.dame;

import java.util.Vector;

public abstract class Player {

	public static final String WHITE = "white";
	public static final String BLACK = "black";
	protected String color;

	public abstract Vector<Turn> getNextTurn(Vector<Turn> lastTurn)
			throws InvalidTurnException;

	public abstract void setColorInternal();

	public void setColor(String color) {
		this.color = color;
		setColorInternal();
	}

	public String getColor() {
		return color;
	}

}

package de.dame;

public class Turn {

	private Field source, destination, removedStone;
	private boolean victoryTurn;

	public Field getSource() {
		return source;
	}

	public void setSource(Field source) {
		this.source = source;
	}

	public Field getDestination() {
		return destination;
	}

	public void setDestination(Field destination) {
		this.destination = destination;
	}

	public Field getRemovedStone() {
		return removedStone;
	}

	public void setRemovedStone(Field removedStone) {
		this.removedStone = removedStone;
	}

	public boolean isVictoryTurn() {
		return victoryTurn;
	}

	public void setVictoryTurn(boolean victoryTurn) {
		this.victoryTurn = victoryTurn;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getClass().getSimpleName());
		builder.append(": ");

		if (source != null) {
			builder.append(source);
		}
		builder.append(" -> ");
		builder.append(destination);
		if (removedStone != null) {
			builder.append(", removed: ");
			builder.append(removedStone);
		}
		if (isVictoryTurn()) {
			builder.append(", VICTORY!");
		}

		return builder.toString();
	}

}

package de.claussen.threading;

import java.util.Vector;
import java.util.concurrent.Callable;

import de.dame.InvalidTurnException;
import de.dame.Player;
import de.dame.Turn;

public class TurnCalculator implements Callable<Vector<Turn>> {

	Vector<Turn> oldTurn;
	Player actualPlayer;

	public TurnCalculator(Vector<Turn> oldTurn, Player actualPlayer) {
		this.oldTurn = oldTurn;
		this.actualPlayer = actualPlayer;
	}

	public void setOldTurn(Vector<Turn> oldTurn) {
		this.oldTurn = oldTurn;

	}

	public void setActualPlayer(Player actualPlayer) {
		this.actualPlayer = actualPlayer;
	}

	@Override
	public Vector<Turn> call() throws InvalidTurnException {
		return actualPlayer.getNextTurn(oldTurn);
	}
}

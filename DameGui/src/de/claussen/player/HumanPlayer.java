package de.claussen.player;

import java.util.Scanner;
import java.util.Vector;

import de.dame.Field;
import de.dame.InvalidTurnException;
import de.dame.Player;
import de.dame.Turn;

public class HumanPlayer extends Player {
	// TODO theoretisch müsste die Win Methode implementiert bzw. von Hand
	// eingegeben werden

	/**
	 * Erzeugt einen menschlichen Spieler
	 */
	public HumanPlayer() {
	}

	@Override
	public Vector<Turn> getNextTurn(Vector<Turn> oldTurn)
			throws InvalidTurnException {
		Scanner scanner = new Scanner(System.in);

		Vector<Turn> listOfTurns = new Vector<Turn>();
		Field source = null, destination = null, removedStone = null;

		Turn turn = new Turn();

		System.out.print("Bitte Zug eingeben: ");
		String input = scanner.next();
		if (input.length() >= 4) {
			source = new Field(
					Integer.parseInt(String.valueOf(input.charAt(1))),
					input.charAt(0));
			destination = new Field(Integer.parseInt(String.valueOf(input
					.charAt(3))), input.charAt(2));

		}
		if (input.length() == 6) {
			removedStone = new Field(Integer.parseInt(String.valueOf(input
					.charAt(5))), input.charAt(4));
		}
		// // Zum Werfen einer InvalidTurnException
		// else if (input.charAt(0) == 'z') {
		// throw new InvalidTurnException();
		// }

		turn.setDestination(destination);
		turn.setSource(source);
		turn.setRemovedStone(removedStone);
		turn.setVictoryTurn(false);
		listOfTurns.add(turn);
		return listOfTurns;
	}

	@Override
	public void setColorInternal() {
		// TODO Auto-generated method stub

	}
}

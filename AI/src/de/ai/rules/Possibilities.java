package de.ai.rules;

import java.util.Iterator;
import java.util.Vector;

import de.ai.factories.ExtendedFieldFactory;
import de.ai.storage.ExtendedField;
import de.ai.storage.Playfield;
import de.ai.storage.Token;
import de.dame.Field;
import de.dame.InvalidTurnException;
import de.dame.Turn;

public class Possibilities {
	
	public static Vector<Vector<Turn>> getPossibleTurns(Field field) throws InvalidTurnException{
		
		int singleTurnAnzahl = 1;
		
		ExtendedField source = ExtendedFieldFactory.TransformFieldToExtendedField(field);
		Token token = Playfield.getInstance().getToken(source.getXPos(), source.getYPos());
		Vector<ExtendedField> possibleMoves = token.getPossibleMoves();
		Vector<Vector<Turn>> turnVectors = new Vector<Vector<Turn>>();
		
		Vector<Turn> singleTurn1 = new Vector<Turn>();
		Vector<Turn> singleTurn2 = new Vector<Turn>();
		Vector<Turn> singleTurn3 = new Vector<Turn>();
		Vector<Turn> singleTurn4 = new Vector<Turn>();
		
		if(possibleMoves.size() == 1){
			
		} else {
			
			Iterator<ExtendedField> itr = possibleMoves.iterator();
			
			while (itr.hasNext()) {
				
				ExtendedField currentPossibility = itr.next();
				Turn turn = new Turn();
				turn.setSource(source.getSuperClass());
				turn.setDestination(currentPossibility.getSuperClass());
				
				switch (singleTurnAnzahl) {
					case 1:
						singleTurn1.add(turn);
						break;
					case 2:
						singleTurn2.add(turn);
						break;
					case 3:
						singleTurn3.add(turn);
						break;
					case 4:
						singleTurn4.add(turn);
						break;
				}
				
				singleTurnAnzahl++;
				
			}
			
		}
		
		switch(singleTurnAnzahl) {
			case 1:
				turnVectors.add(singleTurn1);
				break;
			case 2:
				turnVectors.add(singleTurn1);
				turnVectors.add(singleTurn2);
				break;
			case 3:
				turnVectors.add(singleTurn1);
				turnVectors.add(singleTurn2);
				turnVectors.add(singleTurn3);
				break;
			case 4:
				turnVectors.add(singleTurn1);
				turnVectors.add(singleTurn2);
				turnVectors.add(singleTurn3);
				turnVectors.add(singleTurn4);
				break;
		}
		
		return turnVectors;
	}

}
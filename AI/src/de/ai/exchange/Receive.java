package de.ai.exchange;

import java.util.Iterator;
import java.util.Vector;

import de.ai.factories.ExtendedFieldFactory;
import de.ai.storage.Playfield;
import de.ai.storage.Token;
import de.dame.InvalidTurnException;
import de.dame.Turn;

public abstract class Receive {
	
	public static void checkLastTurn(Vector<Turn> lastTurn) throws InvalidTurnException{
		
		Iterator<Turn> itr = lastTurn.iterator();
		Turn currentTurn = new Turn();
		int anzahlDerTurns = lastTurn.size();
		
		if(anzahlDerTurns == 1){
			
			currentTurn = lastTurn.firstElement();
			Token token = Playfield.getInstance().getToken(ExtendedFieldFactory.TransformFieldToExtendedField(currentTurn.getSource()).getXPos(),ExtendedFieldFactory.TransformFieldToExtendedField(currentTurn.getSource()).getYPos());
			token.move(ExtendedFieldFactory.TransformFieldToExtendedField(currentTurn.getDestination()).getXPos(), ExtendedFieldFactory.TransformFieldToExtendedField(currentTurn.getDestination()).getYPos());
			
		} else {
			
			while (itr.hasNext()) {
					
			}
			
		}
		
	}

}

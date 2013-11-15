package de.ai.exchange;

import java.util.Iterator;
import java.util.Vector;

import de.dame.Turn;

public abstract class Receive {
	
	public static void checkLastTurn(Vector<Turn> lastTurn) {
		
		Iterator itr = lastTurn.iterator();
		
		Turn currentTurn = new Turn();
		
		
		int anzahlDerTurns = lastTurn.size();
		
		if(anzahlDerTurns == 1){
			
			currentTurn = lastTurn.firstElement();
			ExtendedTurn convertedTurn = currentTurn;	
			
		} else {
			
			while (itr.hasNext()) {
					
			}
			
		}
		
	}

}

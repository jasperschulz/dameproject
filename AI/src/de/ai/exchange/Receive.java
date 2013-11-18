package de.ai.exchange;

import java.util.Iterator;
import java.util.Vector;


import de.dame.Turn;

public abstract class Receive {
	
	public static void checkLastTurn(Vector<Turn> lastTurn) {
		
		Iterator<Turn> itr = lastTurn.iterator();
		Turn currentTurn = new Turn();
		int anzahlDerTurns = lastTurn.size();
		
		if(anzahlDerTurns == 1){
			
			currentTurn = lastTurn.firstElement();
			
		} else {
			
			while (itr.hasNext()) {
					
			}
			
		}
		
	}

}

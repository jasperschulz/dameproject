package de.ai;

import java.util.Vector;

import de.ai.exchange.Receive;
import de.ai.storage.Playfield;
import de.dame.InvalidTurnException;
import de.dame.Player;
import de.dame.Turn;


public class Main extends Player{

	@Override
	public Vector<Turn> getNextTurn(Vector<Turn> lastTurn){
		try{
			if(lastTurn.size() == 0){
				Playfield.getInstance().setMyTeam(true);
			} else Playfield.getInstance().setMyTeam(false);
			
			Receive.checkLastTurn(lastTurn);
		}
		catch (InvalidTurnException e) {
			
		}		
		return null;
	}

	@Override
	public void setColor(String color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setColorInternal() {
		
		super.getColor();
		
	}
	
}

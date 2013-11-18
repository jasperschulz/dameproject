package de.ai;

import java.util.Vector;

import de.ai.exchange.Receive;
import de.dame.InvalidTurnException;
import de.dame.Player;
import de.dame.Turn;


public class Main extends Player{

	@Override
	public Vector<Turn> getNextTurn(Vector<Turn> lastTurn){
		try{
			
			Receive.checkLastTurn(lastTurn);
			throw new InvalidTurnException("exception wird hier nur temporaer geworfen!");
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

package main;

import java.util.Vector;

import de.dame.InvalidTurnException;
import de.dame.Player;
import de.dame.Turn;

public abstract class GJplay extends Player{
	
	private boolean color=false;
	private Vector<PlayField> fields;

	@Override
	public Vector<Turn> getNextTurn(Vector<Turn> lastTurn)
			throws InvalidTurnException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setColor(String color){
		if(color == WHITE) {
			this.color = true;
		}
		else {
			this.color = false;
		}
	}
	
	public PlayField fillField(int line, char column, boolean ocupied, boolean color, boolean dame){
		PlayField field = new PlayField(line,column,ocupied,color,dame);
		return field;
	}
	
	public void fillFields(int line, char column, boolean ocupied, boolean color, boolean dame){
		PlayField field = fillField(line,column,ocupied,color,dame);
		fields.add(field);
	}

}

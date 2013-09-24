package main;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import units.Feld;
import units.Spielfeld;
import units.Token;
import de.dame.InvalidTurnException;
import de.dame.Player;
import de.dame.Turn;

public class GJplay extends Player{
	
	private boolean color=false;
	private Vector<PlayField> fields = new Vector<PlayField>();
	private Vector<Feld> jspielfeld;
	private Spielfeld jspielfeldobj;
	
	public GJplay(Spielfeld spielfeldobj){
		this.jspielfeldobj=spielfeldobj;
		this.jspielfeld=this.jspielfeldobj.getFields();
		getData();
	}

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
	
	public void getData() {
		
		Iterator<Feld> itr = this.jspielfeld.iterator();
	
		while(itr.hasNext()) {
			fillFields( itr.next().getY() , transformColumn(itr.next().getX()) , itr.next().isOccupied() , transformColor(itr.next().getColor()) , itr.next().isDame());
		}
	}
	
	public char transformColumn(int x){
		char column = ' ';
		if(x==1)
			column='A';
		if(x==2)
			column='B';
		if(x==3)
			column='C';
		if(x==4)
			column='D';
		if(x==5)
			column='E';
		if(x==6)
			column='F';
		if(x==7)
			column='G';
		if(x==8)
			column='H';
		return column;
	}
	
	public boolean transformColor(String color){
		boolean colo = false;
		if(color=="white")
			colo=true;
		if(color=="black")
			colo=false;
		return colo;
	}
	
	public Vector<PlayField> getPlayfield(){
		return this.fields;
	}

}

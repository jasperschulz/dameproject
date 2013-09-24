package units;

import java.util.Iterator;
import java.util.Vector;

public class Spielfeld {
	
	Vector<Feld> fields = new Vector<Feld>();
	Spieler spieler1;
	Spieler spieler2;
	
	public Spielfeld(Spieler spieler1, Spieler spieler2) {
		
		int x = 1;
		int y = 8;
		
		this.spieler1 = spieler1;
		this.spieler2 = spieler2;
		
		while(x != 9 && y != 0) {
			
			addField(x,y);
			
			if(x == 8){
				
				x = 1;
				y--;
				
			} else {
				
				x++;
				
			}
			
		}
		
	}
	
	public void addField(int x, int y) {
		
		Feld newField = new Feld(x , y);
		this.fields.add(newField);
		
	}
	
	public void generateDisplayFields() {
	}
	
	public Feld getField(int x, int y) {
		
		Iterator<Feld> itr = this.fields.iterator();
		
	    while(itr.hasNext()) {
	    	if(itr.next().x == x && itr.next().y == y){
	    		break;
	    	}
	    }
	    
	    return itr.next();
		
	}
	
	public void putTokensOnField (Vector<Token> tokens){
		
		Iterator<Token> itr = tokens.iterator();
		
	    while(itr.hasNext()) {
	    	this.getField(itr.next().x, itr.next().y).token = itr.next();
	    }
		
	}
	
	public Vector <Feld> getFields() {
		return this.fields;
	}

}

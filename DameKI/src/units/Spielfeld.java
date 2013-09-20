package units;

import java.util.Iterator;
import java.util.Vector;

public class Spielfeld {
	
	Vector<Feld> fields = new Vector<Feld>();
	
	public Spielfeld() {
		
		int x = 1;
		int y = 8;
		
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
		fields.add(newField);
		
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

}
package units;

import java.util.Iterator;
import java.util.Vector;

public class Playfield {
	
	Vector<field> fields = new Vector<field>();
	Vector<field> displayFields = new Vector<field>();
	
	public Playfield() {
		
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
		
		field newField = new field(x , y);
		fields.add(newField);
		
	}
	
	public void generateDisplayFields() {
	}
	
	public field getField(int x, int y) {
		
		Iterator<field> itr = this.fields.iterator();
		
	    while(itr.hasNext()) {
	    	if(itr.next().x == x && itr.next().y == y){
	    		break;
	    	}
	    }
	    
	    return itr.next();
		
	}
	
	public void putTokensOnField (Vector<Token> tokens){
		
	}

}

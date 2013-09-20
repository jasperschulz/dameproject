package units;

import java.util.Vector;

public class Playfield {
	
	Vector<field> fields = new Vector<field>();
	Vector<field> displayFields = new Vector<field>();
	
	public Playfield() {
		
		addField(1,8);
		
		
		
		
	}
	
	public void addField(int x, int y) {
		
		field newField = new field(x , y);
		fields.add(newField);
		
	}
	
	public void generateDisplayFields() {
		
		int x = 1;
		int y = 8;
		
		while(x != 8 && y != 1) {
			
			addField(x,y);
			
			if(x == 8){
				
				x = 1;
				y--;
				
			} else {
				
				x++;
				
			}
			
		}
	}

}

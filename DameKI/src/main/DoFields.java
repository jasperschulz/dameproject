package main;

import java.util.Iterator;
import java.util.Vector;

import units.Feld;

public class DoFields {	    		
	
	public void drawFields(Vector<PlayField> fields){
		Iterator<PlayField> itr = fields.iterator();
		
		/*for (int i = 1;i <= 8; ++i) {
			
			while(itr.hasNext()) {
				if(itr.next().getColumn() == x && itr.next().getLine() == i){
					break;
		    	}
			}
		}*/
		
		System.out.println("   A B C D E F G H ");
		
	    while(itr.hasNext()) {
	    	PlayField field = itr.next();
	    	char column = field.getColumn();
	    	int line = field.getLine();
	    	
	    	if (column == 'A')
	    	{
	    		System.out.println("  -----------------");
	    		System.out.print(line + " ");
	    	}
	    	
	    	System.out.print ("|");
	    	
	    	//belegtes Feld- switch
	    	if (field.getColor() && field.getOcupied())
	    	{
	    		if(field.getDame())
	    		{
	    			System.out.print ("o");
	    		}
	    		else {
	    			System.out.print ("O");
	    		}
	    	}

	    	if (!field.getColor() && field.getOcupied())
	    	{
	    		if(field.getDame())
	    		{
	    			System.out.print ("x");
	    		}
	    		else {
	    			System.out.print ("X");
	    		}
	    	}
	    	
	    	if (!field.getOcupied()) {
	    		System.out.print (" ");
	    	}
	    	
	    	//Abfrage für den Zeilenumbruch
	    	if (column == 'H')
	    	{
	    		System.out.print ("|\n");
	    	}
	    }
	    
	    System.out.println("  -----------------");
	}
	
}

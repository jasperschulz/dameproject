package main;

import java.util.Vector;

public class DoFields {
	
	public void drawFields(Vector<PlayField> fields){
		Vector<PlayField> itr = fields;
		
		System.out.println("   A B C D E F G H ");
		
	    while(itr.iterator().hasNext()) {
	    	PlayField field = itr.iterator().next();
	    	char column = field.getColumn();
	    	int line = field.getLine();
	    	
	    	System.out.println("  -----------------");
	    	
	    	if (column == 'A')
	    	{
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
	    	
	    	System.out.print ("|");
	    	//Abfrage für den Zeilenumbruch
	    	if (column == 'H')
	    	{
	    		System.out.print("\n");
	    	}
	    }
	    
	    System.out.println("  -----------------");
	}
	
}

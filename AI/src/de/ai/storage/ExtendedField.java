package de.ai.storage;

import de.dame.Field;

public class ExtendedField extends Field{
	
	private int line;
	private char column;
	
	private int x;
	private int y;
	
	private Field superclass = null;
	
	public ExtendedField (int linie, char spalte) {
		
		super(linie, spalte);
		
		superclass = new Field(linie, spalte);
		
		this.line = superclass.getLine();
		this.column = superclass.getColumnLowerCase();
		
		this.y = line;
		
		switch (column){
		case 'a':
			this.x = 1;
			break;
		case 'b':
			this.x = 2;
			break;
		case 'c':
			this.x = 3;
			break;
		case 'd':
			this.x = 4;
			break;
		case 'e':
			this.x = 5;
			break;
		case 'f':
			this.x = 6;
			break;
		case 'g':
			this.x = 7;
			break;
		case 'h':
			this.x = 8;
			break;
		default:
			break;
		}
	
	}
	
	public Field getSuperClass() {
		return superclass;
	}
	
	public int getXPos() {
		return this.x;
	}
	
	public int getYPos() {
		return this.y;
	}
}

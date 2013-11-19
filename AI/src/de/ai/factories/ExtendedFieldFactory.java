package de.ai.factories;

import de.ai.storage.ExtendedField;
import de.dame.Field;
import de.dame.InvalidTurnException;

public abstract class ExtendedFieldFactory {
	
	public static ExtendedField TransformFieldToExtendedField(Field field) throws InvalidTurnException{
		ExtendedField extendedfield = new ExtendedField(field.getLine(),field.getColumnLowerCase());
		return extendedfield;
	}
	
	public static ExtendedField ExtendedFieldCreate(int x, int y) throws InvalidTurnException{
		
		char spalte = 'a';
		
		switch (x){
			case 1:
				spalte = 'a';
				break;
			case 2:
				spalte = 'b';
				break;
			case 3:
				spalte = 'c';
				break;
			case 4:
				spalte = 'd';
				break;
			case 5:
				spalte = 'e';
				break;
			case 6:
				spalte = 'f';
				break;
			case 7:
				spalte = 'g';
				break;
			case 8:
				spalte = 'h';
				break;
		}
		
		ExtendedField extendedField = new ExtendedField( y, spalte);
				
		return extendedField;
	}

}
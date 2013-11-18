package de.ai.factories;

import de.ai.storage.ExtendedField;
import de.dame.Field;
import de.dame.InvalidTurnException;

public abstract class ExtendedFieldFactory {
	
	public static ExtendedField TransformFieldToExtendedField(Field field) throws InvalidTurnException{
		ExtendedField extendedfield = new ExtendedField(field.getLine(),field.getColumnLowerCase());
		return extendedfield;
	}

}
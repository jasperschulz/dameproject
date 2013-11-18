package de.ai.factories;

import de.ai.storage.ExtendedField;
import de.dame.Field;

public abstract class ExtendedFieldFactory {
	
	public ExtendedField TransformFieldToExtendedField(Field field) {
		ExtendedField extendedfield = new ExtendedField(field.getLine(),field.getColumnLowerCase());
		return extendedfield;
	}

}
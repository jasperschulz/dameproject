package de.ai.rules;

import java.util.Vector;

import de.ai.factories.ExtendedFieldFactory;
import de.ai.storage.ExtendedField;
import de.ai.storage.Playfield;
import de.ai.storage.Token;
import de.dame.Field;
import de.dame.InvalidTurnException;
import de.dame.Turn;

public class Possibilities {
	
	public static Vector<Vector<Turn>> getPossibleTurns(Field field) throws InvalidTurnException{
		
		ExtendedField source = ExtendedFieldFactory.TransformFieldToExtendedField(field);
		Token token = Playfield.getInstance().getToken(source.getXPos(), source.getYPos());
		
		
		
		return null;
	}

}
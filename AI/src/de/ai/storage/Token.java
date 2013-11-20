package de.ai.storage;

import java.util.Iterator;
import java.util.Vector;

import de.ai.factories.ExtendedFieldFactory;
import de.dame.InvalidTurnException;

public class Token {

	private boolean dame;
	private final boolean team;

	public Token(boolean team) {
		this.team = team;
	}

	public int getXPos() {

		int foundX = 0;

		for (int x = 1; x < 9; x++) {

			for (int y = 1; y < 9; y++) {

				if (Playfield.getInstance().field[x][y] == this) {
					
					foundX = x;
					break;
					
				}
				
				if (foundX != 0) break;

			}

		}

		return foundX;
	}

	public int getYPos() {

		int foundY = 0;

		for (int x = 1; x < 9; x++) {

			for (int y = 1; y < 9; y++) {

				if (Playfield.getInstance().field[x][y] == this){
					foundY = y;
					break;
				}
				
				if (foundY != 0) break;

			}

		}

		return foundY;

	}

	public boolean isDame() {
		return dame;
	}

	public void setDame() {
		this.dame = true;
	}
	
	public boolean getTeam(){
		return this.team;
	}
	
	public void move(int x, int y) throws InvalidTurnException{
		Playfield.getInstance().field[this.getXPos()][this.getYPos()] = null;
		if(!Playfield.getInstance().isOccupied(x,y)){
			Playfield.getInstance().field[x][y] = this;
		} else {
			throw new InvalidTurnException("MoveError: destination is occupied");
		}
	}
	
	public Vector<ExtendedField> getPossibleMoves() throws InvalidTurnException {
		
		Vector<ExtendedField> fieldsAround = this.getFieldsAround();
		Vector<ExtendedField> possibleFields = new Vector<ExtendedField>();
		
		Iterator<ExtendedField> itr = fieldsAround.iterator();
		
		ExtendedField currentField = ExtendedFieldFactory.ExtendedFieldCreate(this.getXPos(), this.getYPos());
		ExtendedField checkingField;
		
		while (itr.hasNext()) {
			
			checkingField = itr.next();
			
			if(this.team == false && this.dame == false && checkingField.getYPos() < currentField.getYPos() && this.isOccupiedByMate(checkingField.getXPos(),checkingField.getYPos()) == false){
				if(Playfield.getInstance().isOccupied(checkingField.getXPos(),checkingField.getYPos())){
					
					if (Playfield.getInstance().getJumpField(currentField, checkingField) != null) {
						
						ExtendedField jumpField = Playfield.getInstance().getJumpField(currentField, checkingField);
						
						if (!Playfield.getInstance().isOccupied(jumpField.getXPos(), jumpField.getYPos())){
							possibleFields.clear();
							possibleFields.add(jumpField);
							break;
						}
						
					}

				} else {
					possibleFields.add(checkingField);
				}
			}
			
			if(this.dame == true && this.isOccupiedByMate(checkingField.getXPos(),checkingField.getYPos()) == false){
				if(Playfield.getInstance().isOccupied(checkingField.getXPos(),checkingField.getYPos())){
					
					if (Playfield.getInstance().getJumpField(currentField, checkingField) != null) {
						
						ExtendedField jumpField = Playfield.getInstance().getJumpField(currentField, checkingField);
						
						if (!Playfield.getInstance().isOccupied(jumpField.getXPos(), jumpField.getYPos())){
							possibleFields.clear();
							possibleFields.add(jumpField);
							break;
						}
						
					}

				} else {
					possibleFields.add(checkingField);
				}
			}
			
			if(this.team == true && this.dame == false && checkingField.getYPos() > currentField.getYPos() && this.isOccupiedByMate(checkingField.getXPos(),checkingField.getYPos()) == false){
				if(Playfield.getInstance().isOccupied(checkingField.getXPos(),checkingField.getYPos())){
					
					if (Playfield.getInstance().getJumpField(currentField, checkingField) != null) {
						
						ExtendedField jumpField = Playfield.getInstance().getJumpField(currentField, checkingField);
						
						if (!Playfield.getInstance().isOccupied(jumpField.getXPos(), jumpField.getYPos())){
							possibleFields.clear();
							possibleFields.add(jumpField);
							break;
						}
						
					}

				} else {
					possibleFields.add(checkingField);
				}
			}
			
		}
		
		return possibleFields;
	}
	
	private Vector<ExtendedField> getFieldsAround() throws InvalidTurnException {
		
		Vector<ExtendedField> fieldsAround = new Vector<ExtendedField>();
		
		int currentX = this.getXPos();
		int currentY = this.getYPos();
		
		int calculatedX;
		int calculatedY;

		calculatedY = currentY+1;
		calculatedX = currentX-1;
		if((calculatedY > 0 && calculatedY < 9) && (calculatedX > 0 && calculatedX < 9)) fieldsAround.add(ExtendedFieldFactory.ExtendedFieldCreate(calculatedX, calculatedY));

		calculatedY = currentY+1;
		calculatedX = currentX+1;
		if((calculatedY > 0 && calculatedY < 9) && (calculatedX > 0 && calculatedX < 9)) fieldsAround.add(ExtendedFieldFactory.ExtendedFieldCreate(calculatedX, calculatedY));

		calculatedY = currentY-1;
		calculatedX = currentX+1;
		if((calculatedY > 0 && calculatedY < 9) && (calculatedX > 0 && calculatedX < 9)) fieldsAround.add(ExtendedFieldFactory.ExtendedFieldCreate(calculatedX, calculatedY));

		calculatedY = currentY-1;
		calculatedX = currentX-1;
		if((calculatedY > 0 && calculatedY < 9) && (calculatedX > 0 && calculatedX < 9)) fieldsAround.add(ExtendedFieldFactory.ExtendedFieldCreate(calculatedX, calculatedY));
		
		
		return fieldsAround;
	}
	
	public boolean isOccupiedByMate(int x, int y) {
		boolean occupied = false;
		if(Playfield.getInstance().field[x][y] != null){
			if(Playfield.getInstance().field[x][y].getTeam() == this.team) {
				occupied = true;
			}
		}
		return occupied;
	}

}
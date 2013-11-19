package de.ai.storage;

import de.dame.InvalidTurnException;

public class Token {

	private boolean dame;
	private boolean team;

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
		if(Playfield.getInstance().field[x][y] == null){
			Playfield.getInstance().field[x][y] = this;
		} else {
			throw new InvalidTurnException("MoveError: destination is occupied");
		}
	}

}
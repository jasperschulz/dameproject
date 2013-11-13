package de.ai.storage;

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

				}

			}

		}

		return foundX;
	}

	public int getYPos() {

		int foundY = 0;

		for (int x = 1; x < 9; x++) {

			for (int y = 1; y < 9; y++) {

				if (Playfield.getInstance().field[x][y] == this) {

					foundY = y;

				}

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

}

package de.ai.storage;

import de.ai.factories.ExtendedFieldFactory;
import de.dame.InvalidTurnException;

public class Playfield {
	
	private static boolean myTeam;
	private static Playfield instance = null;
	
	Token[][] field = new Token [9][9];
	
	private Playfield() {
		initializeNewPlayfield();
	}
	
	public static Playfield getInstance() {
		if (instance == null) {
			instance = new Playfield();
		}
	        return instance;
	}
	
	private void initializeNewPlayfield(){
		
		//WEISS:
		addToken(1,1,true);
		addToken(3,1,true);
		addToken(5,1,true);
		addToken(7,1,true);
		addToken(2,2,true);
		addToken(4,2,true);
		addToken(6,2,true);
		addToken(8,2,true);
		//
		//SCHWARZ:
		addToken(1,7,false);
		addToken(3,7,false);
		addToken(5,7,false);
		addToken(7,7,false);
		addToken(2,8,false);
		addToken(4,8,false);
		addToken(6,8,false);
		addToken(8,8,false);
		//
		
	}
	
	private void addToken(int x, int y, boolean team){
		Token newToken = new Token(team);
		this.field[x][y] = newToken;
	}
	
	public boolean isOccupied(int x, int y) {
		boolean occupied = true;
		if(this.field[x][y] == null) occupied = false;
		return occupied;
	}
	
	public Token getToken (int x, int y) throws InvalidTurnException{
		if (!isOccupied(x,y)) throw new InvalidTurnException("getToken: No Token found on field");
		Token token = field[x][y];
		return token;
	}
	
	
	/**
	 Methode gibt anhand des Source- und EnemyFields das SpringField, sofern es innerhalb des Feldes ist (sonst return-> null)
	*/
	public ExtendedField getJumpField(ExtendedField sourceField, ExtendedField enemyField) throws InvalidTurnException{
		
		int sourceX = sourceField.getXPos();
		int sourceY = sourceField.getYPos();
		
		int enemyX = enemyField.getXPos();
		int enemyY = enemyField.getYPos();
		
		int jumpX;
		int jumpY;
		
		if(sourceX - enemyX < 0) {
				jumpX = enemyX + 1;
		} else {
			jumpX = enemyX - 1;
		}
		
		if(sourceY - enemyY < 0) {
			jumpY = enemyY + 1;
		} else {
			jumpY = enemyY - 1;
		}
		
		if(jumpX > 0 && jumpX < 9 && jumpY > 0 && jumpY < 9) {
			return ExtendedFieldFactory.ExtendedFieldCreate(jumpX, jumpY);
		} else {
			return null;
		}
	}
	
	public boolean getMyTeam() {
		return myTeam;
	}
	
	public void setMyTeam(boolean team){
		myTeam = team;
	}

}
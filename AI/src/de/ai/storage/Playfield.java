package de.ai.storage;

public class Playfield {
	
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

}
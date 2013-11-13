package de.ai.storage;

public class Playfield {
	
	private static Playfield instance = null;
	
	Token[][] field = new Token [9][9];
	
	private Playfield() {}
	
	public static Playfield getInstance() {
		if (instance == null) {
			instance = new Playfield();
		}
	        return instance;
	}

}

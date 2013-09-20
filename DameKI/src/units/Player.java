package units;

import java.util.Vector;


public class Player {
	
	public static final String WHITE = "white";
	public static final String BLACK = "black";
	
	public static final String FORWARDS = "forwards";
	public static final String BACKWARDS = "backwards";
	
	String tokenColor;
	String playDirection;
	
	Vector<Token> tokens = new Vector<Token>();

	
	public Player (String color){
		if (color == WHITE){
			
			tokenColor = WHITE;
			playDirection = FORWARDS;
			
			this.addToken(1, 1);
			this.addToken(3, 1);
			this.addToken(5, 1);
			this.addToken(7, 1);
			
			this.addToken(2, 2);
			this.addToken(4, 2);
			this.addToken(6, 2);
			this.addToken(8, 2);
			
			this.addToken(1, 3);
			this.addToken(3, 3);
			this.addToken(5, 3);
			this.addToken(7, 3);
			
			
		} else {
			
			tokenColor = BLACK;
			playDirection = BACKWARDS;
			
			this.addToken(1, 8);
			this.addToken(3, 8);
			this.addToken(5, 8);
			this.addToken(7, 8);
			
			this.addToken(2, 7);
			this.addToken(4, 7);
			this.addToken(6, 7);
			this.addToken(8, 7);
			
			this.addToken(1, 6);
			this.addToken(3, 6);
			this.addToken(5, 6);
			this.addToken(7, 6);
			
		}
		
	}
	
	
	public void addToken(int x, int y) {
		
		Token newToken = new Token(x, y);
		this.tokens.add(newToken);
		
	}
	
	public String getColor() {
		return this.tokenColor;
	}
	
	public Vector<Token> getTokens() {
		return this.tokens;
	}
	
	public Vector<Token> getDamen() {
		
		Vector<Token> damen = new Vector<Token>();
		
	    while(tokens.iterator().hasNext()) {
	    	if(tokens.iterator().next().dame = true){
	    		damen.add(tokens.iterator().next());
	    	}
	    }
	    return damen;
	}
	
	public int countTokens(){
		return this.tokens.size();
	}
	

}

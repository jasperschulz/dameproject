package units;

import java.util.Vector;


public class Spieler {
	
	public static final String WHITE = "white";
	public static final String BLACK = "black";
	
	public static final String FORWARDS = "forwards";
	public static final String BACKWARDS = "backwards";
	
	String tokenColor;
	String playDirection;
	
	public Vector<Token> tokens = new Vector<Token>();

	
	public Spieler (String color){
		if (color == WHITE){
			
			tokenColor = WHITE;
			playDirection = FORWARDS;
			
			this.addToken(1, 1, tokenColor);
			this.addToken(3, 1, tokenColor);
			this.addToken(5, 1, tokenColor);
			this.addToken(7, 1, tokenColor);
			
			this.addToken(2, 2, tokenColor);
			this.addToken(4, 2, tokenColor);
			this.addToken(6, 2, tokenColor);
			this.addToken(8, 2, tokenColor);
			
			this.addToken(1, 3, tokenColor);
			this.addToken(3, 3, tokenColor);
			this.addToken(5, 3, tokenColor);
			this.addToken(7, 3, tokenColor);
			
		} else {
			
			tokenColor = BLACK;
			playDirection = BACKWARDS;
			
			this.addToken(1, 8, tokenColor);
			this.addToken(3, 8, tokenColor);
			this.addToken(5, 8, tokenColor);
			this.addToken(7, 8, tokenColor);
			
			this.addToken(2, 7, tokenColor);
			this.addToken(4, 7, tokenColor);
			this.addToken(6, 7, tokenColor);
			this.addToken(8, 7, tokenColor);
			
			this.addToken(1, 6, tokenColor);
			this.addToken(3, 6, tokenColor);
			this.addToken(5, 6, tokenColor);
			this.addToken(7, 6, tokenColor);
			
		}
		
	}
	
	public void addToken(int x, int y, String color) {
		
		Token newToken = new Token(x, y);
		newToken.color = color;
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

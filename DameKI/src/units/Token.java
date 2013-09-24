package units;

import de.dame.Player;

public class Token{
	
	int x;
	int y;
	boolean dame;
	String color;
	Player player;
	
	public Token (int x, int y){
		
		this.x = x;
		this.y = y;
		
	}
	
	public int getX () {
		return this.x;
	}
	
	public int getY () {
		return this.x;
	}
	
	public boolean isDame () {
		return this.dame;
	}
	
	public String getColor () {
		return this.color;
	}
	
	public void setPlayer (Player player) {
		this.player = player;
	}
	
	public void setPos (int x, int y) {
		this.x = x;
		this.y = y;
	}

}

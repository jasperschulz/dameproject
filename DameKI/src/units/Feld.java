package units;


public class Feld {
	
	int x;
	int y;
	
	boolean occupied;
	
	Token token = null;
	
	public Feld (int x, int y){
		
		this.x = x;
		this.y = y;
		
	}
	
	public void setToken(Token token) {
		this.token = token;
		this.occupied = true;
	}
	
	public void removeToken() {
		this.token = null;
		this.occupied = false;
	}
	
	public int getX () {
		return this.x;
	}
	
	public int getY () {
		return this.y;
	}

}

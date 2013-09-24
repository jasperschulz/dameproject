package units;


public class Feld {
	
	int x;
	int y;
	
	boolean occupied = false;
	
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
	
	public String getColor() {
		if(this.isOccupied() == false) {
			return "black";
		}
		else {
			return this.token.color;
		}
	}
	
	public boolean isOccupied () {
		if(this.token != null) this.occupied = true;
		return this.occupied;
	}
	
	public boolean isDame () {
		if(this.isOccupied() == false) {
			return false;
		}
		else {
			return this.token.dame;
		}
	}

}

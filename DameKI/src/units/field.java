package units;


public class field {
	
	int x;
	int y;
	
	boolean occupied;
	
	Token token = null;
	
	public field (int x, int y){
		
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

}

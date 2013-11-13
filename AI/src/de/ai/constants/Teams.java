package de.ai.constants;

public enum Teams {

	WHITE("positive"),
	BLACK("negative");
	
	private final String direction;
	
	Teams (String direction) {
		this.direction = direction;
	}
	
	public String getDirection(){
		return this.direction;
	}
}

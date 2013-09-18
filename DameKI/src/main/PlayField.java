package main;

import de.dame.Field;

public class PlayField extends Field {
	
	private boolean ocupied= false;
	private boolean color=false;
	
	public PlayField(int line, char column, boolean ocu, boolean color) {
		super(line, column);
		ocupieField(ocu);
		setColor(color);
	}
	
	public void ocupieField(boolean ocupied) {
		this.ocupied=ocupied;
	}
	
	public void setColor(boolean color) {
		if(ocupied) {
			this.color=color;
		}
	}
	
	public void drawPlayField(){
		
	}
	
	public void createPlayField(){

	}
	
}

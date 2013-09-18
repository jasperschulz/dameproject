package main;

import de.dame.Field;

public class PlayField extends Field {
	
	private boolean ocupied= false;
	private boolean color=false;
	private boolean dame=false;
	
	public PlayField(int line, char column, boolean ocu, boolean color, boolean dame) {
		super(line, column);
		ocupieField(ocu);
		setColor(color);
		setDame(dame);
	}
	
	private void setDame(boolean dame) {
		this.dame=dame;
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

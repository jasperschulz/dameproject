package main;

import java.util.Iterator;
import java.util.Vector;

public class DoFields {
	
	public Vector<PlayField> fields;
	
	public DoFields(Vector<PlayField> fields) {
		this.fields=fields;
	}
	
	public void setField(Vector<PlayField> fields) {
		this.fields=fields;
	}
	
	public Vector<PlayField> getFields() {
		return this.fields;
	}
	
	private void drawFields(){
		Vector<PlayField> itr = this.fields;
	    while(itr.iterator().hasNext()) {
	    	PlayField field = itr.iterator().next();
	    	int line = field.getLine();
	    	
	    	
	    }
	}
	
}

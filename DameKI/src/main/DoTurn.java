package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import de.dame.Field;
import de.dame.InvalidTurnException;
import de.dame.Turn;

public class DoTurn {
	
	private String sourceX;
	private String sourceY;
	private String destinationX;
	private String destinationY;
	private Turn turn = new Turn();
	private Field source;
	private Field destination;
	private GJplay gjplay;
	private Vector<Turn> lastTurn = new Vector<Turn>();
	
	public DoTurn(GJplay gjplay) {
		this.gjplay=gjplay;
	}
	
	
	public void typeTurn() {
		
		boolean right = false;
			
		while(!right ){
			
			sourceY = setPositionY();
			sourceX = setPositionX();
			
			destinationY = setDestinationY();
			destinationX = setDestinationX();
			
			source = new Field(Integer.parseInt(sourceX),sourceY.charAt(0));
			destination = new Field(Integer.parseInt(destinationX),destinationY.charAt(0));
			
			turn.setSource(source);
			turn.setDestination(destination);
			
			lastTurn.add(turn);
			
			try {
				gjplay.getNextTurn(lastTurn);
			} catch (InvalidTurnException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			gjplay.setZug();
		
			if (!pruefZug()) {
				System.out.print("Der Zug war ungültig!");
				}
			else {
				right = true;
			}
			
		}
	}
	
	private String setPositionX(){
		
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		String zeile = null;
		System.out.print("Geben sie die Spalte des Spielstein ein: ");
		try {
			zeile += console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return zeile;
		
	}
	
	private String setPositionY(){
		
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Geben sie die Zeile des Spielstein ein: ");
		String zeile = null;
		try {
			zeile = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return zeile;
		
	}
	
	private String setDestinationX(){
		
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		String zeile = null;
		System.out.print("Geben sie die Zielspalte für den Stein ein: ");
		try {
			zeile += console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return zeile;
	}
	
	private String setDestinationY(){
		
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Geben sie die Zielzeile für den Stein ein: ");
		String zeile = null;
		try {
			zeile = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return zeile;
	}

	private boolean pruefZug() {
		return (Boolean) null;
	}

}

package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DoTurn {
	
	public void typeTurn() {
		
		boolean right = false;
		while(!right ){
			
			String zeile = setPosition();
		
			if (!pruefStein(zeile)) {
			System.out.print("Du musst ein Feld w�hlen auf dem ein Stein von dir liegt!");
			}
			else {
				right = true;
			}
			
		}
		
		right = false;
		while(!right ){
			
			String zeile = setDestination();
		
			if (!pruefZug(zeile)) {
				System.out.print("Du musst ein Feld w�hlen auf das du mit deinem Stein ziehen darfst!");
				}
			else {
				right = true;
			}
			
		}
	}
	
	private String setPosition(){
		
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Geben sie die Zeile des Spielstein ein: ");
		String zeile = null;
		try {
			zeile = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.print("Geben sie die Spalte des Spielstein ein: ");
		try {
			zeile += console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return zeile;
	}
	
	private String setDestination(){
		
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Geben sie die Zielzeile f�r den Stein ein: ");
		String zeile = null;
		try {
			zeile = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.print("Geben sie die Zielspalte f�r den Stein ein: ");
		try {
			zeile += console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return zeile;
	}

	private boolean pruefZug(String zeile) {
		return (Boolean) null;
	}

	private boolean pruefStein(String zeile) {
		return (Boolean) null;
	}

}

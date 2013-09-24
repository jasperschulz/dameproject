package match;

import main.DoFields;
import main.GJplay;
import units.Spieler;
import units.Spielfeld;

public class Game {
	
	public Game() {
		
		Spieler spieler1 = new Spieler("white");
		Spieler spieler2 = new Spieler("black");
		
		Spielfeld spielfeld = new Spielfeld(spieler1,spieler2);
		
		spielfeld.putTokensOnField(spieler1.tokens);
		spielfeld.putTokensOnField(spieler2.tokens);
		System.out.println("Spiel wurde initialisiert.");
		
		GJplay play = new GJplay(spielfeld);
		DoFields draw = new DoFields();
		draw.drawFields(play.getPlayfield());
		
	}

}

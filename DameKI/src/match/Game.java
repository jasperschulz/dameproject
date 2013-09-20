package match;

import units.Spieler;
import units.Spielfeld;

public class Game {
	
	Spieler spieler1 = new Spieler("white");
	Spieler spieler2 = new Spieler("black");
	
	Spielfeld spielfeld = new Spielfeld(spieler1,spieler2);
	
	
	public void startGame() {
		spielfeld.putTokensOnField(spieler1.tokens);
		spielfeld.putTokensOnField(spieler2.tokens);
	}

}

package de.claussen.gui;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.claussen.gui.GameStone.PlayerColor;
import de.claussen.player.HumanPlayer;
import de.claussen.threading.TurnCalculator;
import de.claussen.tools.Helper;
import de.dame.InvalidTurnException;
import de.dame.Player;
import de.dame.Turn;

public class GameWindow {

	private Display display;
	private Shell shell;

	private Composite statusBar;

	private Composite lowerPanel;
	private Composite gamePanel;

	private final int WIDTH = 700;

	private HashMap<String, Point> coords;
	private HashMap<String, GameStone> stones;
	private HashMap<PlayerColor, Player> playerMap;

	private Player firstPlayer;
	private Player secondPlayer;

	/**
	 * Erzeugt die GUI samt Komponenten
	 * 
	 * @param display
	 * @param shell
	 */
	public GameWindow(Display display, Shell shell) {
		this.display = display;
		this.shell = shell;
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		shell.setLayout(new GridLayout(1, false));

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 100;
		statusBar = new Composite(shell, SWT.BORDER);
		statusBar.setLayoutData(gd);
		statusBar.setBackground(display.getSystemColor(SWT.COLOR_BLACK));

		statusBar.setLayout(new GridLayout(3, false));

		lowerPanel = new Composite(shell, SWT.BORDER);
		gd = new GridData();
		gd.heightHint = WIDTH;
		gd.widthHint = WIDTH;
		lowerPanel.setLayoutData(gd);
		lowerPanel.setLayout(new GridLayout(3, false));
		lowerPanel.setBackgroundMode(SWT.INHERIT_FORCE);

		// Spielfeld
		gamePanel = new Composite(lowerPanel, SWT.BORDER | SWT.INHERIT_DEFAULT);

		coords = Helper.getInstance().fillHashMap();
		stones = new HashMap<>(24);
		playerMap = new HashMap<>(2);

		Image background = Helper.getInstance().resize(
				WIDTH - 10,
				WIDTH - 10,
				new Image(display, GameWindow.class
						.getResourceAsStream("spielfeld.jpg")));

		gd = new GridData();
		gd.heightHint = WIDTH;
		gd.widthHint = WIDTH;
		gamePanel.setLayoutData(gd);

		gamePanel.setBackgroundImage(background);
		gamePanel.setBackgroundMode(SWT.INHERIT_FORCE);
		gamePanel.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));

		// Zeichnet die Feldnamen als Text auf das Spielfeld
		// gamePanel.addPaintListener(new PaintListener() {
		//
		// @Override
		// public void paintControl(PaintEvent e) {
		// for (Map.Entry<String, Point> entry : coords.entrySet()) {
		// e.gc.setForeground(Display.getCurrent().getSystemColor(
		// SWT.COLOR_WHITE));
		// e.gc.setBackground(Display.getCurrent().getSystemColor(
		// SWT.COLOR_BLACK));
		// e.gc.drawString(entry.getKey(), entry.getValue().x + 15,
		// entry.getValue().y + 12);
		// }
		// }
		// });

		// Gamestone Map initialisieren
		stones = Helper.getInstance().fillStoneMap(coords, shell, display,
				gamePanel);

		// zeigt die Mauskoordinaten in der Console an
		// gamepanel.addMouseMoveListener(new MouseMoveListener() {
		//
		// @Override
		// public void mouseMove(MouseEvent e) {
		// System.out.println(e.x + "/" + e.y);
		// }
		// });

	}

	/**
	 * startet das Spiel
	 */
	public void run() {
		// Öffnet zwei File-Dialoge, um Jars auszuwählen. In diesen Jars wird
		// dynamisch nach Klassen gesucht, die unsere abstrakte Klasse
		// Player extended
		// firstPlayer = Helper.getInstance().loadKI(shell);
		// secondPlayer = Helper.getInstance().loadKI(shell);
		firstPlayer = new HumanPlayer();
		secondPlayer = new HumanPlayer();

		// Per "Zufall" bestimmen wer anfängt
		Player temp;
		int glaskugel = (int) (Math.random() * 2);
		if (glaskugel == 1) {
			System.out.println("Switching Player");
			temp = firstPlayer;
			firstPlayer = secondPlayer;
			secondPlayer = temp;
		}

		firstPlayer.setColor(Player.WHITE);
		secondPlayer.setColor(Player.BLACK);

		// Style für den ersten Spieler (weiß)
		StyledText pl1 = new StyledText(statusBar, SWT.NONE);
		pl1.setText("First Player");
		StyleRange style1 = new StyleRange();
		style1.start = 0;
		style1.length = pl1.getText().length();
		style1.fontStyle = SWT.BOLD;
		style1.background = display.getSystemColor(SWT.COLOR_BLACK);
		style1.foreground = display.getSystemColor(SWT.COLOR_WHITE);
		pl1.setStyleRange(style1);

		// Versus Label
		StyledText vs = new StyledText(statusBar, SWT.NONE);
		// vs.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
		vs.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
		vs.setText("VS");

		// Style für den zweiten Player
		StyledText player2 = new StyledText(statusBar, SWT.NONE);
		player2.setText("Second Player");
		StyleRange style2 = new StyleRange();
		style2.start = 0;
		style2.length = player2.getText().length();
		style2.fontStyle = SWT.BOLD;
		style2.foreground = new Color(display, new RGB(135, 116, 72));
		style2.background = display.getSystemColor(SWT.COLOR_BLACK);
		player2.setStyleRange(style2);

		playerMap.put(PlayerColor.WHITE, firstPlayer);
		playerMap.put(PlayerColor.BLACK, secondPlayer);

		Vector<Turn> oldTurn = null;
		PlayerColor actualPlayer = PlayerColor.WHITE;
		boolean win = false;

		// Spielzüge solange bis jemand gewinnt
		while (!win) {
			try {

				/**
				 * Nächsten Spielerzug berechnen, die Ausführungszeit wird
				 * begrenzt, Variable siehe Helper-Klasse
				 */
				ExecutorService pool = Executors.newSingleThreadExecutor();
				TurnCalculator tc = new TurnCalculator(oldTurn,
						playerMap.get(actualPlayer));

				Future<Vector<Turn>> future = pool.submit(tc);
				oldTurn = future.get(Helper.getInstance().TIME_TO_CALCULATE,
						Helper.getInstance().TIME_UNIT);

				// StringBuilder sb = new StringBuilder();
				// sb.append("Turn: ");
				// if (oldTurn == null) {
				// // stopping game
				// // ToDo
				// }
				// if (oldTurn.getSource() != null) {
				// sb.append(oldTurn.getSource().getColumn());
				// sb.append(oldTurn.getSource().getLine());
				// }
				// sb.append(oldTurn.getDestination().getColumn());
				// sb.append(oldTurn.getDestination().getLine());
				// if (oldTurn.getRemovedStone() != null) {
				// sb.append(oldTurn.getRemovedStone().getColumn());
				// sb.append(oldTurn.getRemovedStone().getLine());
				// }
				// System.out.println(sb.toString());
				// Nächsten Zug auf dem Spielfeld anzeigen
				performTurn(oldTurn, actualPlayer);
				// Spielerwechsel
				actualPlayer = actualPlayer.toggle();
			} catch (InterruptedException e) {
				// tritt niemals ein -.-
				e.printStackTrace();
			} catch (ExecutionException e) {
				if (e.getCause() instanceof InvalidTurnException) {
					// Spieler behauptet, dass der Zug ungültig war
					System.out.println(actualPlayer + " says Turn was invalid");
					shell.setText(actualPlayer + " says Turn was invalid");
					shell.update();
					Scanner scanner = new Scanner(System.in);
					scanner.nextLine();
					win = true;
				}
			} catch (TimeoutException e) {
				// Spieler hat zu lange überlegt
				System.out.println("TIME UP");
				Scanner scanner = new Scanner(System.in);
				scanner.nextLine();
				win = true;
			}
		}
		System.out.println("Game Ended");
	}

	/**
	 * Zeigt den Spielzug an
	 * 
	 * @param listOfTurn
	 *            - aktueller zug
	 * @param player
	 *            - aktueller Spieler
	 */
	private void performTurn(Vector<Turn> listOfTurn, PlayerColor player) {

		for (Turn t : listOfTurn) {

			String destination = Helper.getInstance().getStringFromField(
					t.getDestination());

			// Spieler bewegt den Stein von einem Feld "source" zu einem anderen
			// "destination"
			if (t.getSource() != null && t.getDestination() != null) {

				GameStone gs = stones.get(Helper.getInstance()
						.getStringFromField(t.getSource()));
				String source = Helper.getInstance().getStringFromField(
						t.getSource());

				if (gs.getPlayer() == PlayerColor.BLACK
						&& t.getDestination().getLine() == 1) {
					gs.setDame(true);
				} else if (gs.getPlayer() == PlayerColor.WHITE
						&& t.getDestination().getLine() == 8) {
					gs.setDame(true);
				}

				System.out.println("Moving " + gs + " from " + source + " to "
						+ destination);
				gs.move(coords.get(source), coords.get(destination));
				gamePanel.update();
				// den Stein von der alten Position löschen
				stones.remove(source);
				// neue Koordinaten des Steines sichern
				stones.put(destination, gs);
			}
			// Spieler will einen Stein entfernen
			if (t.getRemovedStone() != null) {
				// Player wants to remove Stone
				String removeString = Helper.getInstance().getStringFromField(
						t.getRemovedStone());
				// Den Stein aus der HashMap laden
				GameStone toRemove = stones.get(removeString);
				System.out.println("Player: " + player + " removed: "
						+ toRemove);
				// den Stein grafisch vom Spielfeld entfernen
				toRemove.removeStone();
				gamePanel.update();
				// den Stein aus der HashMap löschen
				stones.remove(removeString);
			}
		}
	}
}

package de.claussen.tools;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import de.claussen.gui.GameStone;
import de.claussen.gui.GameStone.PlayerColor;
import de.dame.Field;
import de.dame.Player;

public class Helper {

	private static Helper instance = null;

	private static HashMap<Point, String> pointToCoords;

	protected Helper() {
		// Exists only to defeat instantiation.
	}

	public final int IMAGE_SIZE = 55;

	public final int FIELD_SIZE = 72;

	public final int OFFSET = (FIELD_SIZE - IMAGE_SIZE) / 3;

	public final int START_X = 58;
	public final int START_Y = 58;

	public final int TIME_TO_CALCULATE = 1;
	public final TimeUnit TIME_UNIT = TimeUnit.MINUTES;

	/**
	 * Erzeugt die Singleton Instanz des Helpers oder liefert diese zurück
	 * 
	 * @return
	 */
	public static Helper getInstance() {
		if (instance == null) {
			System.out.println("Instantiating Helper");
			instance = new Helper();
		}
		return instance;
	}

	/**
	 * Skaliert ein Bild
	 * 
	 * @param w
	 * @param h
	 * @param img
	 * @return
	 */
	public Image resize(int w, int h, Image img) {
		Image newImage = new Image(Display.getDefault(), w, h);
		GC gc = new GC(newImage);
		gc.setAntialias(SWT.ON);
		gc.setInterpolation(SWT.HIGH);
		gc.drawImage(img, 0, 0, img.getBounds().width, img.getBounds().height,
				0, 0, w, h);
		gc.dispose();
		img.dispose();
		return newImage;
	}

	/**
	 * wandelt ein Field Objekt in einen String um
	 * 
	 * @param field
	 * @return
	 */
	public String getStringFromField(Field field) {
		return field.toString();
	}

	/**
	 * Methode zum dynamischen Laden von KI's
	 * 
	 * @return eine Instanz der KI
	 */
	public Player loadKI(Shell shell) {
		Player p = null;
		try {

			FileDialog f = new FileDialog(shell);
			f.open();
			String result = f.getFilterPath() + "/" + f.getFileName();

			System.out.println(result);

			p = Loader.loadClassFrom(result, Player.class.getSimpleName());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return p;

	}

	/**
	 * HashMap mit festgelegten Spots füllen
	 * 
	 * @return
	 */
	public HashMap<String, Point> fillHashMap() {
		HashMap<String, Point> coords = new HashMap<>(32);
		pointToCoords = new HashMap<>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i % 2 == 0 && j % 2 == 1) {
					// Feld ist schwarz
					String s = String.valueOf(((char) (i + 65)))
							+ String.valueOf((8 - j));
					Point p = new Point(START_X + i * FIELD_SIZE, START_Y + j
							* FIELD_SIZE);
					coords.put(s, p);
					pointToCoords.put(p, s);
				} else if (i % 2 == 1 && j % 2 == 0) {
					// Feld ist schwarz
					String s = String.valueOf(((char) (i + 65)))
							+ String.valueOf((8 - j));
					Point p = new Point(START_X + i * FIELD_SIZE, START_Y + j
							* FIELD_SIZE);
					coords.put(s, p);
					pointToCoords.put(p, s);
				}
			}
		}
		return coords;
	}

	public HashMap<String, GameStone> fillStoneMap(
			HashMap<String, Point> fields, Shell shell, Display display,
			Composite parent) {
		HashMap<String, GameStone> stones = new HashMap<>(24);

		Set<String> setOfFields = fields.keySet();
		for (Iterator<String> iter = setOfFields.iterator(); iter.hasNext();) {
			String next = iter.next();
			if (next.contains("1") || next.contains("2") || next.contains("3")) {
				GameStone gs = new GameStone(parent, PlayerColor.WHITE,
						fields.get(next), display);
				stones.put(next, gs);
			} else if (next.contains("6") || next.contains("7")
					|| next.contains("8")) {
				GameStone gs = new GameStone(parent, PlayerColor.BLACK,
						fields.get(next), display);
				stones.put(next, gs);
			}
		}

		return stones;
	}

	public static HashMap<Point, String> getCoords() {
		return pointToCoords;
	}
}

package de.claussen.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import de.claussen.tools.Helper;

/**
 * 
 * @author Sven Claussen
 * 
 */
public class GameStone extends Canvas {

	private PaintListener stonePainter;

	private boolean isDame;

	private boolean isDameInternal;

	public void setDame(boolean isDame) {
		this.isDame = isDame;
	}

	/*
	 * Aufzählung der Spielerfarben mit Methode für einen Farbwechsel
	 */
	public enum PlayerColor {
		BLACK, WHITE;

		public PlayerColor toggle() {
			switch (this) {
			case BLACK:
				return WHITE;
			case WHITE:
				return BLACK;
			}
			return null;
		}
	}

	private Point location;

	private int directionX = 0;
	private int directionY = 0;

	private PlayerColor player;

	public PlayerColor getPlayer() {
		return player;
	}

	public GameStone(Composite parent, PlayerColor p, Point location,
			Display display) {
		super(parent, SWT.NO_BACKGROUND);

		this.player = p;
		this.location = new Point(location.x + Helper.getInstance().OFFSET,
				location.y + Helper.getInstance().OFFSET);

		this.isDame = false;
		this.isDameInternal = false;
		addListenerForPainting();
		setPosition(location);
	}

	private void setPosition(Point location) {
		setSize(Helper.getInstance().FIELD_SIZE,
				Helper.getInstance().FIELD_SIZE);
		setBounds(location.x, location.y, Helper.getInstance().FIELD_SIZE,
				Helper.getInstance().FIELD_SIZE);
		redraw();
		update();
	}

	/**
	 * Führt die Animation der Bewegung in einem Thread aus
	 * 
	 * @param source
	 * @param destination
	 */
	public void move(final Point source, final Point destination) {
		// Bewegung in eigenen Thread auslagern
		Display.getCurrent().syncExec(new Runnable() {

			@Override
			public void run() {
				move_intern(source, destination);
			}
		});
	}

	/**
	 * Erzeugt die Animation zum Bewegen eines Steines von einem Feld zum
	 * anderen
	 * 
	 * @param source
	 *            ist das Startfeld
	 * @param destination
	 *            ist das Zielfeld
	 */
	public void move_intern(Point source, Point destination) {

		// destination.x += Helper.getInstance().OFFSET;
		// destination.y += Helper.getInstance().OFFSET;

		directionX = 0;
		directionY = 0;

		// Zug nach rechts
		if (source.x < destination.x) {
			directionX = 1;
		}
		// Zug nach links
		else if (source.x > destination.x) {
			directionX = -1;
		}

		// Zug nach unten
		if (source.y < destination.y) {
			directionY = 1;
		}
		// Zug nach oben
		else if (source.y > destination.y) {
			directionY = -1;
		}

		// Verschiebt den Stein solange um einen Pixel, bis er am Ziel ankommt
		while (location.x != destination.x || location.y != destination.y) {
			if (location.x != destination.x) {
				location.x += directionX;
			}
			if (location.y != destination.y) {
				location.y += directionY;
			}

			setPosition(location);
		}

		if (isDame) {
			System.out.println("Stein: " + this + " wird zur Dame");
			isDameInternal = isDame;
			redraw();
		}

		setPosition(location);

	}

	/**
	 * Erzeugt den PaintListener für den Spielstein
	 */
	public void addListenerForPainting() {

		stonePainter = new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {
				switch (player) {
				case BLACK:
					e.gc.setBackground(new Color(Display.getCurrent(), new RGB(
							135, 116, 72)));
					e.gc.setForeground(new Color(Display.getCurrent(), new RGB(
							112, 89, 36)));
					break;
				case WHITE:
					e.gc.setBackground(Display.getCurrent().getSystemColor(
							SWT.COLOR_WHITE));
					e.gc.setForeground(new Color(Display.getCurrent(), new RGB(
							214, 208, 195)));
					break;
				}
				e.gc.fillOval(Helper.getInstance().OFFSET,
						Helper.getInstance().OFFSET,
						Helper.getInstance().IMAGE_SIZE,
						Helper.getInstance().IMAGE_SIZE);
				e.gc.setLineWidth(2);
				e.gc.setLineJoin(SWT.JOIN_ROUND);
				e.gc.drawOval(Helper.getInstance().OFFSET + 5,
						Helper.getInstance().OFFSET + 5,
						Helper.getInstance().IMAGE_SIZE - 10,
						Helper.getInstance().IMAGE_SIZE - 10);
				if (isDameInternal) {
					switch (player) {
					case WHITE:
						e.gc.setBackground(new Color(Display.getCurrent(),
								new RGB(135, 116, 72)));
						e.gc.setForeground(new Color(Display.getCurrent(),
								new RGB(112, 89, 36)));
						break;
					case BLACK:
						e.gc.setBackground(Display.getCurrent().getSystemColor(
								SWT.COLOR_WHITE));
						e.gc.setForeground(new Color(Display.getCurrent(),
								new RGB(214, 208, 195)));
						break;
					}

				}
				e.gc.drawOval(Helper.getInstance().OFFSET + 16,
						Helper.getInstance().OFFSET + 16,
						Helper.getInstance().IMAGE_SIZE - 32,
						Helper.getInstance().IMAGE_SIZE - 32);
				e.gc.fillOval(Helper.getInstance().OFFSET + 18,
						Helper.getInstance().OFFSET + 18,
						Helper.getInstance().IMAGE_SIZE - 36,
						Helper.getInstance().IMAGE_SIZE - 36);

			}
		};
		addPaintListener(stonePainter);
	}

	public Point computeSize(int wHint, int hHint, boolean changed) {
		return new Point(Helper.getInstance().FIELD_SIZE,
				Helper.getInstance().FIELD_SIZE);
	}

	public Point computeSize(int wHint, int hHint) {
		return new Point(Helper.getInstance().FIELD_SIZE,
				Helper.getInstance().FIELD_SIZE);
	}

	/**
	 * Entfernt den Stein vom Spielfeld, indem der PaintListener entfernt wird
	 * und das Objekt disposed wird Ein Update auf der Parent Komponente ist
	 * erforderlich
	 * 
	 */
	public void removeStone() {
		this.removePaintListener(stonePainter);
		stonePainter = null;
		this.dispose();
	}

	public String toString() {
		return "Stone:" + player + "["
				+ Helper.getInstance().getCoords().get(location) + "]" + "["
				+ location.x + "," + location.y + "]";
	}
}

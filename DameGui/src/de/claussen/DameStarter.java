package de.claussen;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.claussen.gui.GameWindow;

public class DameStarter {

	private static DameStarter instance = null;

	protected DameStarter() {
		// Exists only to defeat instantiation.
	}

	public static DameStarter getInstance() {
		if (instance == null) {
			instance = new DameStarter();
		}
		return instance;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		final Display display = new Display();
		Shell shell = new Shell(display);

		// Erzeugt eine Instanz des grafischen Spielfeldes und initialisiert
		// zwei KI's
		GameWindow gw = new GameWindow(display, shell);

		shell.setText("Dame Wrapper");

		shell.pack();
		shell.open();

		// startet das Spiel
		gw.run();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

}

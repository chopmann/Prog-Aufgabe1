package de.proglabor.aufgabe3.gui;

import de.proglabor.aufgabe3.Welt;
import de.proglabor.aufgabe3.controllers.Controller;

public interface SimView {
	public void addController(Controller controller);

	public void update(Controller controller);
}

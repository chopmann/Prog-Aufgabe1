package de.proglabor.aufgabe3.controllers;

import java.util.HashMap;
import java.util.Observer;

import de.proglabor.aufgabe3.WeltConfig;
import de.proglabor.aufgabe3.gui.SimulationViewer;

public interface SimControlerInterface {

	public void neu(HashMap<WeltConfig, Integer> parameters);
	public void addView(SimulationViewer v);
	public void simulate(int days);
	public int born();
	public int planted();
	public int eaten();
	public int deaths();
}

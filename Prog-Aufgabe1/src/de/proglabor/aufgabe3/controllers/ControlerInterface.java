package de.proglabor.aufgabe3.controllers;

import java.util.HashMap;

import de.proglabor.aufgabe3.config.WeltConfig;

public interface ControlerInterface {

	void neu(HashMap<WeltConfig, Integer> parameters);

	void clear();

}

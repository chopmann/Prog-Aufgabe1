package de.proglabor.aufgabe3.controllers;

import java.util.HashMap;

import de.proglabor.aufgabe3.config.WeltConfig;

/**
 * @author id261708
 *
 */
public interface ControlerInterface {

	/**
	 * Something
	 * @param parameters 
	 */
	void neu(HashMap<WeltConfig, Integer> parameters);

	/**
	 *  Something
	 */
	void clear();
	
	/**
	 * Something
	 * @param message 
	 */
	void setStatus(String message);

}

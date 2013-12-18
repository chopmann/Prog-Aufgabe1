package de.proglabor.aufgabe4.main;

import de.proglabor.aufgabe4.Welt;

import java.util.logging.Level;
import java.util.logging.Logger;

import de.proglabor.aufgabe4.console.Console;
import de.proglabor.aufgabe4.controllers.Controller;
import de.proglabor.aufgabe4.exceptions.UnknownKeywordException;
import de.proglabor.aufgabe4.exceptions.WrongValueException;

/**
 * @author id261708
 *
 */
public class Main {

	public static Logger LOGGER = Logger.getLogger("application");
	/**
	 * @param args 
	 */
	public static void main(String[] args) {
		
//    	Welt model 	= new Welt();
//    	@SuppressWarnings("unused") //WELL SORT OF
//		Controller controler = new Controller(model);
		LOGGER.info("Application Started");
		Console console = new Console();
		try {
			console.start(args);
		} catch (WrongValueException | UnknownKeywordException e) {
			LOGGER.log(Level.SEVERE, e.getClass().getSimpleName() + ": " + e.getMessage());
		}
	}

}

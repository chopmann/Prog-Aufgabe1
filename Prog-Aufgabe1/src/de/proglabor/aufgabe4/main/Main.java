package de.proglabor.aufgabe4.main;

import de.proglabor.aufgabe4.Welt;
import de.proglabor.aufgabe4.console.Console;
import de.proglabor.aufgabe4.controllers.Controller;
import de.proglabor.aufgabe4.exceptions.UnknownKeywordException;
import de.proglabor.aufgabe4.exceptions.WrongValueException;

/**
 * @author id261708
 *
 */
public class Main {

	/**
	 * @param args 
	 */
	public static void main(String[] args) {
		
//    	Welt model 	= new Welt();
//    	@SuppressWarnings("unused") //WELL SORT OF
//		Controller controler = new Controller(model);
		
		Console console = new Console();
		try {
			console.start(args);
		} catch (WrongValueException | UnknownKeywordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

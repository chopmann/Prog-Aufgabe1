package de.proglabor.aufgabe3.main;

import de.proglabor.aufgabe3.Welt;
import de.proglabor.aufgabe3.controllers.Controller;

/**
 * @author id261708
 *
 */
public class Main {

	/**
	 * @param args 
	 */
	public static void main(String[] args) {
		
    	Welt model 	= new Welt();
    	@SuppressWarnings("unused") //WELL SORT OF
		Controller controler = new Controller(model);
	}

}

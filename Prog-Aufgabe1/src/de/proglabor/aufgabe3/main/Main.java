package de.proglabor.aufgabe3.main;

import de.proglabor.aufgabe3.Welt;
import de.proglabor.aufgabe3.controllers.Controller;

public class Main {

	public static void main(String[] args) {
		
    	Welt model 	= new Welt();
    	Controller controler = new Controller(model);
	}

}

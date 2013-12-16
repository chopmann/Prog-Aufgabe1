package de.proglabor.aufgabe3.main;

import javax.swing.SwingUtilities;

import de.proglabor.aufgabe3.Welt;
import de.proglabor.aufgabe3.controllers.Controller;
import de.proglabor.aufgabe3.gui.MainWindow;

public class Main {

	public static void main(String[] args) {
		
    	Welt model 	= new Welt();
    	Controller controler = new Controller(model);
        
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
	}

}

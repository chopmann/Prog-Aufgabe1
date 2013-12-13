package de.proglabor.aufgabe3.main;

import javax.swing.SwingUtilities;

import de.proglabor.aufgabe3.Welt;
import de.proglabor.aufgabe3.controllers.Controller;
import de.proglabor.aufgabe3.gui.MainWindow;

public class Main {

	public static void main(String[] args) {
		
		//create Model and SimView
		Welt model 	= new Welt();
		final MainWindow view = new MainWindow();
		Controller controller = new Controller(model, view);
		
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                view.setVisible(true);
            }
        });
	}

}

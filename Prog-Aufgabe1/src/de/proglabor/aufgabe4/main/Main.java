package de.proglabor.aufgabe4.main;

import de.proglabor.aufgabe4.Welt;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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
	 * One logger to rule them all
	 */
	public static final Logger LOGGER = Logger.getLogger("application");
	static FileHandler fh;

	/**
	 * @param args to run this thing
	 */
	public static void main(String[] args) {
		try {

			// This block configure the logger with handler and formatter
			fh = new FileHandler("application.log");
			LOGGER.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		LOGGER.info("Application Started");
		Console console = new Console();
		try {
			console.start(args);
			Welt model = new Welt();
			// WELL SORT OF
			if (console.getReportTiere()) {
				model.setReportTiere(true);
			}
			if (console.getReportPflanze()) {
				model.setReportPlants(true);
			}
			if (console.getReportBewegung()) {
				model.setReportBewegung(true);
			}
			if (console.getSimulation()) {
				@SuppressWarnings("unused")
				Controller controler = new Controller(model);
			} else {
				model.runSim(console.getSimulationCount());
			}

		} catch (WrongValueException | UnknownKeywordException e) {
			LOGGER.log(Level.SEVERE,
					e.getClass().getSimpleName() + ": " + e.getMessage());
		}
	}

}

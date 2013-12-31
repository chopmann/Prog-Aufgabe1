package de.proglabor.aufgabe4.main;

import de.proglabor.aufgabe4.console.ArgumentParser;
import de.proglabor.aufgabe4.helper.Helper;
import de.proglabor.aufgabe4.modell.Welt;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

import de.proglabor.aufgabe4.controller.Controller;
import de.proglabor.aufgabe4.exceptions.UnknownKeywordException;
import de.proglabor.aufgabe4.exceptions.WrongValueException;

/**
 * @author id261708
 */
public class Main {

    static FileHandler fh;

    public static void main(String[] args) {
        try {
            // This block configure the logger with handler and formatter
            fh = new FileHandler("simulation.log");
            Helper.LOGGER.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Helper.LOGGER.info("Application Started");
        ArgumentParser argumentParser = new ArgumentParser();
        try {
            argumentParser.parse(args);
            Welt model = new Welt();
            // WELL SORT OF
            if (argumentParser.getReportTiere()) {
                model.setReportTiere(true);
            }
            if (argumentParser.getReportPflanze()) {
                model.setReportPlants(true);
            }
            if (argumentParser.getReportBewegung()) {
                model.setReportBewegung(true);
            }
            if (argumentParser.getSimulation()) {
                @SuppressWarnings("unused")
                Controller controler = new Controller(model);
            } else {
                model.runSim(argumentParser.getSimulationCount());
            }

        } catch (WrongValueException | UnknownKeywordException e) {
            Helper.LOGGER.log(Level.SEVERE, e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

}

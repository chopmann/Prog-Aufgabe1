package de.proglabor.aufgabe4.utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.SimpleFormatter;

/**
 * Created by Benni on 31.12.13.
 */
public class Logger {

    public static String LOG_TAG = "Simulation";
    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(LOG_TAG);

    private Logger() {
        try {
            // This block configure the logger with handler and formatter
            FileHandler fh = new FileHandler("simulation.log");
            Logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("Application Started");
    }

    public static void info(String msg) {
        log.info(msg);
    }

    public static void severe(String msg) {
        log.severe(msg);
    }

    public static void addHandler(Handler handler) throws SecurityException {
        log.addHandler(handler);
    }
}

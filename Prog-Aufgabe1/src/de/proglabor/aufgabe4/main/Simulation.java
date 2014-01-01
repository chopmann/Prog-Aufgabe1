package de.proglabor.aufgabe4.main;

import de.proglabor.aufgabe4.console.RuntimeConfiguration;
import de.proglabor.aufgabe4.exceptions.UnknownKeywordException;
import de.proglabor.aufgabe4.exceptions.WrongValueException;
import de.proglabor.aufgabe4.modell.World;

/**
 * @author Cornelio Hopmann (hopmann@gmail.com)
 *
 * Laboraufgabe Programmieren. WS 2013/2014 an der Ostfalia Hochschule für angewandte Wissenschaften (www.ostfalia.de)
 */
public class Simulation {

    public static void main(String[] args) throws WrongValueException, UnknownKeywordException {
        World world = new World();
        world.setRuntimeConfiguration(RuntimeConfiguration.getInstance().parse(args));
        world.runSim();
    }

}

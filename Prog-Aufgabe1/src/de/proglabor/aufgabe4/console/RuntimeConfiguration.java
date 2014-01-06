package de.proglabor.aufgabe4.console;

import de.proglabor.aufgabe4.exceptions.UnknownKeywordException;
import de.proglabor.aufgabe4.exceptions.WrongValueException;

/**
 * @author SirMonkey
 *
 */
public class RuntimeConfiguration {

	private boolean reportPlants = false;
	private boolean reportTiere = false;
	private boolean reportBewegung = false;
	private boolean reportReproduce = false;
	private boolean displayGUI = false;
	private Integer daysToSim = -1;
	private boolean simCountPresent;
	private int indexOfSimcount;
	private boolean showHelp;

    private RuntimeConfiguration() {
    }

    public static RuntimeConfiguration getInstance() {
        return new RuntimeConfiguration();
    }

    /**
	 * Prueft die Parameter, die auf der RuntimeConfiguration ubergeben wurden und setzt die Attribute.
	 * @param args Parameters to be checked and set.
	 * @throws WrongValueException Simulation Days are Wrong.
	 * @throws UnknownKeywordException Unknown Parameter Dude!
	 */
	public RuntimeConfiguration parse(String[] args) throws WrongValueException, UnknownKeywordException {
		indexOfSimcount = -1;
		simCountPresent = false;
		showHelp = false;
		for (int i = 0; i < args.length; i++) {
			if (showHelp) {
				break;
			}
			switch (args[i].toLowerCase()) {
			case "-help":
				//Break the Loop
				showHelp = true;
				printHelp();
				break;
			case "-pflanze":
				reportPlants = true;
				break;
			case "-tiere":
				reportTiere = true;
				break;
			case "-bewegung":
				reportBewegung = true;
				break;
			case "-reproduce":
				reportReproduce = true;
				break;
			case "-simulation":
				displayGUI = true;
				break;
			case "-simcount":
				simCountPresent = true;
				try {
					int value = Integer.parseInt(args[i + 1]);
					if (value >= 1) {
						daysToSim = value;
						indexOfSimcount = i + 1;
					} else {
						throw new WrongValueException("-simcount must be a postive Integer greater than 0 an it was: " + value);
					}
				} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
						throw new WrongValueException("-simcount --> days missing");
				}
				break;
			default:
				if (indexOfSimcount != i ) {
					throw new UnknownKeywordException("Unknown Keyword " + args[i]);
				} else {
					break;
				}

			}
		} // Prolly not getting called...
		if (!displayGUI && !simCountPresent && !showHelp) {
			throw new UnknownKeywordException("No parameters use -help to display Usage");
		}

        return this;
	}
	
	
	

	/**
	 * @return Report Plant wird erzeugt falls true
	 */
	public boolean createPlantReport() {
		return reportPlants;
	}
	
	/**
	 * @return Report der Tiere wird erzeugt falls true
	 */
	public boolean createAnimalReport() {
		return reportTiere;
	}
	
	/**
	 * @return	Report der Bewegung der Tiere wird erzeugt falls true
	 */
	public boolean createMovementReport() {
		return reportBewegung;
	}
	
	/**
	 * @return Report der Vermaehrung der Tiere wird erzeugt falls true
	 */
	public boolean getReportReproduce() {
		return reportReproduce;
	}
	
	/**
	 * @return Die GUI wird angezeigt falls true
	 */
	public boolean showSimulation() {
		return displayGUI;
	}
	
	/**
	 * @return Anzahl der Tage die simuliert werden sollen
	 */
	public Integer getSimulationCount() {
		return daysToSim;
	}
	
	private void printHelp() {
		String msg = "Simple Simulation \n"
				+ "Parameters \n"
				+ "-help Print this Message \n"
				+ "-simulation Display GUI (Simulation does NOT start automatically)"
				+ "<<OPTIONAL>> \n"
				+ "-simcount [days] How many [days] are going to be simulated \n"
				+ "[days] Must be a postive Integer greater than 1 \n"
				+ "-pflanze Generate a PflanzenReport \n"
				+ "-tiere Gerate a TiereReport \n"
				+ "-bewegung Geneate a Bewegung Report \n"
				+ "-reproduce Generate a Reproduce Report \n";
		System.out.println(msg);
	}
}

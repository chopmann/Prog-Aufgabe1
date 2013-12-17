package de.proglabor.aufgabe4.console;

import de.proglabor.aufgabe4.exceptions.UnknownKeywordException;
import de.proglabor.aufgabe4.exceptions.WrongValueException;

/**
 * @author SirMonkey
 *
 */
public class Console {

	private boolean reportPlants = false;
	private boolean reportTiere = false;
	private boolean reportBewegung = false;
	private boolean reportReproduce = false;
	private boolean displayGUI = false;
	private Integer daysToSim = -1;
	private boolean simCountPresent;
	private int indexOfSimcount;
	private boolean showHelp;

	/**
	 * Prueft die Parameter, die auf der Console ubergeben wurden und setzt die Attribute.
	 * @param args Parameters to be checked and set.
	 * @throws WrongValueException Simulation Days are Wrong.
	 * @throws UnknownKeywordException Unknown Parameter Dude!
	 */
	public void start(String[] args) throws WrongValueException, UnknownKeywordException {
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
				//Display Help
				System.out.println("DISPLAY HELP");
				break;
			case "-pflanze":
				reportPlants = true;
				System.out.println("Plants");
				break;
			case "-tiere":
				System.out.println("Tiere");
				reportTiere = true;
				break;
			case "-bewegung":
				System.out.println("Bewegung");
				reportBewegung = true;
				break;
			case "-reproduce":
				System.out.println("Reproduce");
				reportReproduce = true;
				break;
			case "-simulation":
				System.out.println("Simulation");
				displayGUI = true;
				break;
			case "-simcount":
				simCountPresent = true;
				try {
					int value = Integer.parseInt(args[i + 1]);
					if (value >= 1) {
						daysToSim = value;
						indexOfSimcount = i + 1;
						System.out.println("Days to Sim: " + value);
					} else {
						System.out.println("Wrong Value dude!");
						throw new WrongValueException();
					}
				} catch (Exception e) {
					// Send to log
					System.out.println("Something went wrong: " + e.getMessage());
					throw new WrongValueException();
				}
				break;
			default:
				if (indexOfSimcount != i ) {
					System.out.println("Stop Smonking!--> " + args[i]);
					throw new UnknownKeywordException();
				} else {
					break;
				}

			}
		}
		if (!simCountPresent && !showHelp) {
			throw new UnknownKeywordException();
		}
	}
	
	
	

	/**
	 * @return Report Pflanze wird erzeugt falls true
	 */
	public boolean getReportPflanze() {
		return reportPlants;
	}
	
	/**
	 * @return Report der Tiere wird erzeugt falls true
	 */
	public boolean getReportTiere() {
		return reportTiere;
	}
	
	/**
	 * @return	Report der Bewegung der Tiere wird erzeugt falls true
	 */
	public boolean getReportBewegung() {
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
	public boolean getSimulation() {
		return displayGUI;
	}
	
	/**
	 * @return Anzahl der Tage die simuliert werden sollen
	 */
	public Integer getSimulationCount() {
		return daysToSim;
	}
}

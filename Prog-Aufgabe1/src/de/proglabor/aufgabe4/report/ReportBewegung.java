package de.proglabor.aufgabe4.report;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;

import de.proglabor.aufgabe4.modell.Animal;

public class ReportBewegung extends Report {

	private Animal forest;
	private int comulatedDistance;

	public ReportBewegung(int days) {
		super(days);
	}

	@Override
	public void reportHeader() {
		Date currentDate = getCalendar().getTime();
		String timestamp = String.format("Simulationsdatum: %tc", currentDate);
		reportOutput.add("Simulation Tierbewegung");
		reportOutput.add("");
		reportOutput.add(timestamp);
		reportOutput.add("Anzahl Durchläufe: " + this.days);
		reportOutput.add("");
		reportOutput.add("TierId	Schritte	Distanz	Energie");
	}

	@Override
	public void reportContent(Object obj) {
		comulatedDistance = 0;
		@SuppressWarnings("unchecked")
		LinkedList<Animal> animalContainer = (LinkedList<Animal>) obj;
		Collections.sort(animalContainer, new AnimalComparatorSteps());
		forest = animalContainer.getFirst();
		for (Animal animal : animalContainer) {
			String tmp = animal.getID() + "	" + animal.getSteps() + "	"
					+ animal.getDistanceToStart() + "	" + animal.getEnergy();
			reportOutput.add(tmp);
			if (forest.getDistanceToStart() < animal.getDistanceToStart()) {
				forest = animal;
			}
			comulatedDistance += animal.getDistanceToStart();
		}
		

	}

	@Override
	public void reportFooter(Object obj) {
		@SuppressWarnings("unchecked")
		LinkedList<Animal> animalContainer = (LinkedList<Animal>) obj;
		if (forest == null) {
			comulatedDistance = 0;
			forest = animalContainer.getFirst();
			for (Animal animal : animalContainer) {
				if (forest.getDistanceToStart() < animal.getDistanceToStart()) {
					forest = animal;
				}
				comulatedDistance += animal.getDistanceToStart();
			}
		}
		reportOutput.add("");
		reportOutput.add("TierId:	" + forest.getID());
		reportOutput.add("Lebensenergie:	" + forest.getEnergy());
		reportOutput.add("Maximale Distanz eines Tieres zum Startpunkt:	" + forest.getDistanceToStart());
		double average = (double) comulatedDistance / (double) animalContainer.size();
		reportOutput.add("Durchschnittliche Distanz aller Tiere zu ihren Startpunkten: " + average);


	}

}

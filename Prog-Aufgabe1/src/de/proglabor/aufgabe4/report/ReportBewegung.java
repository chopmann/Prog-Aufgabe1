package de.proglabor.aufgabe4.report;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;

import de.proglabor.aufgabe4.Tier;

public class ReportBewegung extends Report {

	private Tier forest;
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
		LinkedList<Tier> animalContainer = (LinkedList<Tier>) obj;
		Collections.sort(animalContainer, new AnimalComparatorSteps());
		forest = animalContainer.getFirst();
		for (Tier tier : animalContainer) {
			String tmp = tier.getID() + "	" + tier.getSteps() + "	"
					+ tier.getDistanceToStart() + "	" + tier.getEnergy();
			reportOutput.add(tmp);
			if (forest.getDistanceToStart() < tier.getDistanceToStart()) {
				forest = tier;
			}
			comulatedDistance += tier.getDistanceToStart();
		}
		

	}

	@Override
	public void reportFooter(Object obj) {
		@SuppressWarnings("unchecked")
		LinkedList<Tier> animalContainer = (LinkedList<Tier>) obj;
		if (forest == null) {
			comulatedDistance = 0;
			forest = animalContainer.getFirst();
			for (Tier tier : animalContainer) {
				if (forest.getDistanceToStart() < tier.getDistanceToStart()) {
					forest = tier;
				}
				comulatedDistance += tier.getDistanceToStart();
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

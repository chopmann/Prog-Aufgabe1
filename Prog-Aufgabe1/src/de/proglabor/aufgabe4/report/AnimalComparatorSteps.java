package de.proglabor.aufgabe4.report;

import java.util.Comparator;

import de.proglabor.aufgabe4.modell.Tier;

/**
 * @author sirmonkey
 *
 */
public class AnimalComparatorSteps implements Comparator<Tier> {

	@Override
	public int compare(Tier t1, Tier t2) {
		if (t1.getSteps() < t2.getSteps()) {
			return 1;
		} else {
			return -1;
		}
	}

}
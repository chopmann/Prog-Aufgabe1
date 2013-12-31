package de.proglabor.aufgabe4.report;

import java.util.Comparator;

import de.proglabor.aufgabe4.modell.Animal;

/**
 * @author sirmonkey
 *
 */
public class AnimalComparatorSteps implements Comparator<Animal> {

	@Override
	public int compare(Animal t1, Animal t2) {
		if (t1.getSteps() < t2.getSteps()) {
			return 1;
		} else {
			return -1;
		}
	}

}

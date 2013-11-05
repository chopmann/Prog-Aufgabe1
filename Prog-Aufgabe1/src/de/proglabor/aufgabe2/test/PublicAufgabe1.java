package de.proglabor.aufgabe2.test;

import static org.junit.Assert.*;

import org.junit.Test;

import de.proglabor.aufgabe2.SimArray;
import de.proglabor.aufgabe2.SimArrayInterface;

public class PublicAufgabe1 {

	private static final int MAXIMUM_WORLD_X = 40;
	private static final int MAXIMUM_WORLD_Y = 30;
	
	private int getAnimalCount(SimArrayInterface sim, int xmax, int ymax) {
		int result = 0;
		for (int x = 0; x < xmax; x++) {
			for (int y = 0; y < ymax; y++) {
				result += sim.getAnzahlTiere(x, y);
			}
		}
		return result;
	}
	
	/**
	 * This test checks if there are two animals after one day.
	 */
	@Test(timeout=1000)
	public void animalReproduce() {
		SimArrayInterface sim = new SimArray();
		sim.day(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
		int animalCount = getAnimalCount(sim, MAXIMUM_WORLD_X, MAXIMUM_WORLD_Y);
		assertEquals("After one round there should be two animals. " + animalCount + " where found.", 2, animalCount);
	}
	
	/**
	 * This test checks if there is one Animal after initialization.
	 */
	@Test(timeout=1000)
	public void animalInitialCountTest() {
		SimArrayInterface sim = new SimArray();
		assertEquals(1, getAnimalCount(sim, MAXIMUM_WORLD_X, MAXIMUM_WORLD_Y));
	}
	
	/**
	 * This test checks if the animal is in the correct start position.
	 */
	@Test(timeout=1000)
	public void animalInitialPosition() {
		SimArrayInterface sim = new SimArray();
		assertEquals(1, sim.getAnzahlTiere(MAXIMUM_WORLD_X/2, MAXIMUM_WORLD_Y/2));
	}
}

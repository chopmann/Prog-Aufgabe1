package de.proglabor.aufgabe3.test;

import static org.junit.Assert.*;

import org.junit.Test;

import de.proglabor.aufgabe3.SimCollections;
import de.proglabor.aufgabe3.Tier;

public class SimulationIntegrationTest {
	
	private static final int MAXIMUM_WORLD_X = 40;
	private static final int MAXIMUM_WORLD_Y = 30;
	private static final int JUNGLE_WIDTH = 10;
	private static final int JUNGLE_HEIGHT = 10;
	private static final int PLANT_ENERGY = 80;
	private static final int INITIAL_ENERGY = 1000;
	private static final int PLANT_ENERGY_DEAD = 0;
	private static final int REPRODUCTION_ENERGY = 200;


	@Test
	public void simulateThree() {
		SimCollections simulation = new SimCollections(MAXIMUM_WORLD_X, MAXIMUM_WORLD_Y, JUNGLE_WIDTH, JUNGLE_HEIGHT, PLANT_ENERGY, INITIAL_ENERGY, REPRODUCTION_ENERGY);
		Tier dolly = new Tier( MAXIMUM_WORLD_X-1, MAXIMUM_WORLD_Y-1, INITIAL_ENERGY);
		simulation.setTier(dolly);
		
		/*
		 * Zero Day Check.
		 */
		int anzahlTiere = simulation.getTiere().size();
		int anzahlPflanzen = simulation.getPflanzen().size();
		assertEquals(1, anzahlTiere);
		assertEquals(0, anzahlPflanzen);
		
		/*
		 * After Three Days there should be:
		 * 	8 Animals
		 *  6 Plants should have been planted
		 */
		int expectedAnimals = 8;
		int expectedPlants = 6;
		for (int i = 0; i < 3; i++) {
			simulation.day();
		}
		
		anzahlTiere = simulation.getTiere().size();
		assertEquals(expectedAnimals, anzahlTiere);
		assertEquals(expectedPlants, simulation.planted());
	}
	
	@Test
	public void simulateDead() {
		/*
		 * All Animals should be dead
		 */
		SimCollections simulation = new SimCollections(MAXIMUM_WORLD_X, MAXIMUM_WORLD_Y, JUNGLE_WIDTH, JUNGLE_HEIGHT, PLANT_ENERGY_DEAD, INITIAL_ENERGY, REPRODUCTION_ENERGY);
		Tier dolly = new Tier(MAXIMUM_WORLD_X-1, MAXIMUM_WORLD_Y-1, INITIAL_ENERGY);
		simulation.setTier(dolly);
		for (int i = 0; i < INITIAL_ENERGY; i++) {
			simulation.day();
		}
		assertEquals(simulation.born(), simulation.killed());
	}

}

package de.proglabor.aufgabe1.test;

import static org.junit.Assert.*;
import org.junit.Test;

import de.proglabor.aufgabe1.World;

public class WorldTest {

	@Test
	public void testAddPlant() {
		int width = 30;
		int height = 40;
		World dieWelt = new World(width, height);
		dieWelt.addPlant(15, 20);
		dieWelt.addPlant(20, 15);
		dieWelt.addPlant(20, 15);
		int[][] container = dieWelt.getPlantContainer();
		assertEquals(0, container[0][0]);
		assertEquals(1, container[15][20]);
		assertEquals(2, container[20][15]);
	}
	
	@Test
	public void testTotalPlants() {
		int width = 30;
		int height = 40;
		World dieWelt = new World(width, height);
		//Add 5 Plants
		dieWelt.addPlant(15, 20);
		dieWelt.addPlant(15, 20);
		dieWelt.addPlant(15, 20);
		dieWelt.addPlant(20, 15);
		dieWelt.addPlant(20, 15);
		assertEquals(5, dieWelt.totalPlants());
	}

}

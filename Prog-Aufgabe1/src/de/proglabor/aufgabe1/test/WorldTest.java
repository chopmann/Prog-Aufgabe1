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
		int[][] container = dieWelt.getPlantContainer();
		assertEquals(1, container[15][20]);
	}

}

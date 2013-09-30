package de.proglabor.aufgabe1.test;

import static org.junit.Assert.*;
import org.junit.Test;

import de.proglabor.aufgabe1.World;

public class WorldTest {
	
	@Test
	public void testSetJungleLimits(){
		int width = 40;
		int height = 30;
		int widthJungle = 10;
		int heightJungle = 10;
		World dieWelt = new World(width, height, widthJungle,heightJungle);
		dieWelt.setJungleLimits();
		assertEquals(10, dieWelt.getJungleLimitY1());
		assertEquals(10 + heightJungle, dieWelt.getJungleLimitY2());
		assertEquals(15, dieWelt.getJungleLimitX1());
		assertEquals(15 + widthJungle, dieWelt.getJungleLimitX2());
	}

	@Test
	public void testAddPlant() {
		int width = 40;
		int height = 30;
		int widthJungle = 10;
		int heightJungle = 10;
		World dieWelt = new World(width, height, widthJungle,heightJungle);
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
		int width = 40;
		int height = 30;
		int widthJungle = 10;
		int heightJungle = 10;
		World dieWelt = new World(width, height, widthJungle,heightJungle);
		//Add 5 Plants
		dieWelt.addPlant(15, 20);
		dieWelt.addPlant(15, 20);
		dieWelt.addPlant(15, 20);
		dieWelt.addPlant(20, 15);
		dieWelt.addPlant(20, 15);
		assertEquals(5, dieWelt.totalPlants());
	}

}

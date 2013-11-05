package de.proglabor.aufgabe2.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.proglabor.aufgabe2.Pflanze;
import de.proglabor.aufgabe2.Tier;
import de.proglabor.aufgabe2.Welt;

@RunWith(Parameterized.class)
public class WorldTest {

	Welt dieWelt;
	int widthJungle;
	int heightJungle;
	int expectedX1;
	int expectedY1;

	public WorldTest(int width, int height, int widthJungle, int heightJungle,
			int expectedX1, int expectedY1) {

		this.widthJungle = widthJungle;
		this.heightJungle = heightJungle;
		this.expectedX1 = expectedX1;
		this.expectedY1 = expectedY1;
		dieWelt = new Welt(width, height, widthJungle, heightJungle);
	}

	@Parameters
	public static Collection<Object[]> createTestParams() {
		return Arrays.asList(new Object[][] { { 40, 30, 10, 10, 15, 10 },
				{ 40, 27, 10, 10, 15, 8 }, { 40, 30, 11, 10, 14, 10 },
				{ 40, 30, 10, 11, 15, 9 }, { 40, 30, 11, 11, 14, 9 } });
	}
	@Before
	public void resetWorld() {
		dieWelt.initAnimalContainer();
	}

	@Test
	public void testSetJungleLimits() {
		dieWelt.setJungleLimits();
		assertEquals("getJungleLimitY1", expectedY1, dieWelt.getJungleLimitY1());
		assertEquals("getJungleLimitY2", expectedY1 + heightJungle,
				dieWelt.getJungleLimitY2());
		assertEquals("getJungleLimitX1", expectedX1, dieWelt.getJungleLimitX1());
		assertEquals("getJungleLimitX2", expectedX1 + widthJungle,
				dieWelt.getJungleLimitX2());
	}

	@Test
	public void testAddPlant() {
		dieWelt.addPlant(15, 20);
		dieWelt.addPlant(20, 15);
		dieWelt.addPlant(20, 15);
		assertEquals(0, dieWelt.countPlants(0, 0));
		assertEquals(1, dieWelt.countPlants(15, 20));
		assertEquals(2, dieWelt.countPlants(20, 15));
	}
	@Test
	public void testRemovePlant() {
		dieWelt.addPlant(15, 20);
		dieWelt.addPlant(20, 15);
		dieWelt.addPlant(20, 15);
		dieWelt.addPlant(20, 15);
		dieWelt.removePlant(20, 15);
		assertEquals(0, dieWelt.countPlants(0, 0));
		assertEquals(1, dieWelt.countPlants(15, 20));
		assertEquals(2, dieWelt.countPlants(20, 15));
	}

	@Test
	public void testTotalPlants() {
		// Add 5 Plants
		dieWelt.randomAddPlant();
		dieWelt.randomAddPlant();
		dieWelt.randomAddPlant();
		dieWelt.randomAddPlant();
		dieWelt.randomAddPlant();
		assertEquals(5, dieWelt.totalPlants());
	}
	
	@Test
	public void testAddAnimal() {
		dieWelt.addAnimal(15, 20);
		CopyOnWriteArrayList<Tier>[][] container = dieWelt.getAnimalContainer();
		assertNull(container[0][0]);
		assertNotNull(container[15][20]);
		assertEquals(1, container[15][20].size());
	}
	@Test
	public void testAddAnimalsSpread() {
		dieWelt.randomAddAnimal();
		dieWelt.randomAddAnimal();
		dieWelt.randomAddAnimal();
		dieWelt.randomAddAnimal();
		dieWelt.randomAddAnimal();
		assertEquals("Total Animals",5, dieWelt.totalAnimals());
	}
	
	@Test
	public void testAddAnimalSpot() {
		dieWelt.addAnimal(15, 20);
		dieWelt.addAnimal(15, 20);
		dieWelt.addAnimal(15, 20);
		dieWelt.addAnimal(15, 20);
		dieWelt.addAnimal(15, 20);
		assertEquals("Total Animals",5, dieWelt.totalAnimals());
	}
}

package de.proglabor.aufgabe3.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.proglabor.aufgabe3.Tier;
import de.proglabor.aufgabe3.Welt;

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
		return Arrays.asList(new Object[][] {{40, 30, 10, 10, 15, 10 },
				{40, 27, 10, 10, 15, 8 }, {40, 30, 11, 10, 14, 10 },
				{40, 30, 10, 11, 15, 9 }, {40, 30, 11, 11, 14, 9 }});
	}
	@Before
	public void resetWorld() {
		dieWelt.initAnimalContainer();
	}

	@Test
	public void testSetJungleLimits() {
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
		Tier tier = new Tier(100, 15, 20);
		dieWelt.addAnimal(tier);
		LinkedList<Tier> container = dieWelt.getAnimalContainer();
		assertEquals(0 , dieWelt.countAnimals(0, 0));
		assertNotNull(dieWelt.countAnimals(15, 20));
		assertEquals(1, container.size());
	}
	@Test
	public void testAddAnimalsSpread() {
		dieWelt.randomAddAnimal();
		dieWelt.randomAddAnimal();
		dieWelt.randomAddAnimal();
		dieWelt.randomAddAnimal();
		dieWelt.randomAddAnimal();
		assertEquals("Total Animals", 5 , dieWelt.totalAnimals());
	}
	
	@Test
	public void testAddAnimalSpot() {
		Tier tier = new Tier(100, 15, 20);
		dieWelt.addAnimal(tier);
		dieWelt.addAnimal(tier);
		dieWelt.addAnimal(tier);
		dieWelt.addAnimal(tier);
		dieWelt.addAnimal(tier);
		assertEquals("Total Animals", 5 , dieWelt.totalAnimals());
	}
}

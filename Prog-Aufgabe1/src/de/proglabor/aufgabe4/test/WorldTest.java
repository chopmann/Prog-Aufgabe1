package de.proglabor.aufgabe4.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.proglabor.aufgabe4.Tier;
import de.proglabor.aufgabe4.Welt;

@RunWith(Parameterized.class)
public class WorldTest {

	private static final int WIDTH = 40;
	private static final int HEIGHT = 30;
	private static final int WIDTH_JUNGLE = 10;
	private static final int HEIGHT_JUNGLE = 10;
	private static final int PLANT_ENERGY = 80;
	private static final int INITIAL_ENERGY = 200;
	private static final int REPRODUCTION_ENERGY = 200;
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
		dieWelt = new Welt(width, height, widthJungle, heightJungle,
				PLANT_ENERGY, INITIAL_ENERGY, REPRODUCTION_ENERGY);
	}

	@Parameters
	public static Collection<Object[]> createTestParams() {
		return Arrays
				.asList(new Object[][] {
						{ WIDTH, HEIGHT, WIDTH_JUNGLE, HEIGHT_JUNGLE, 15, 10 },
						{ WIDTH, HEIGHT - 3, WIDTH_JUNGLE, WIDTH_JUNGLE, 15, 8 },
						{ WIDTH, HEIGHT, WIDTH_JUNGLE + 1, HEIGHT_JUNGLE, 14,
								10 },
						{ WIDTH, HEIGHT, WIDTH_JUNGLE, HEIGHT_JUNGLE + 1, 15, 9 },
						{ WIDTH, HEIGHT, WIDTH_JUNGLE + 1, HEIGHT_JUNGLE + 1,
								14, 9 } });
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
		assertEquals(0, dieWelt.totalPlantsAt(0, 0));
		assertEquals(1, dieWelt.totalPlantsAt(15, 20));
		assertEquals(2, dieWelt.totalPlantsAt(20, 15));
	}

	@Test
	public void testRemovePlant() {
		dieWelt.addPlant(15, 20);
		dieWelt.addPlant(20, 15);
		dieWelt.addPlant(20, 15);
		dieWelt.addPlant(20, 15);
		dieWelt.removePlant(20, 15);
		assertEquals(0, dieWelt.totalPlantsAt(0, 0));
		assertEquals(1, dieWelt.totalPlantsAt(15, 20));
		assertEquals(2, dieWelt.totalPlantsAt(20, 15));
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
		Tier tier = new Tier(15, 20, 100);
		dieWelt.addAnimal(tier);
		LinkedList<Tier> container = dieWelt.getContainerAnimals();
		assertEquals(0, dieWelt.totalAnimalsAt(0, 0));
		assertNotNull(dieWelt.totalAnimalsAt(15, 20));
		assertEquals(1, container.size());
	}

	@Test
	public void testAddAnimalsSpread() {
		dieWelt.randomAddAnimal();
		dieWelt.randomAddAnimal();
		dieWelt.randomAddAnimal();
		dieWelt.randomAddAnimal();
		dieWelt.randomAddAnimal();
		assertEquals("Total Animals", 5, dieWelt.totalAnimals());
	}

	@Test
	public void testAddAnimalSpot() {
		Tier tier = new Tier(15, 20, 100);
		dieWelt.addAnimal(tier);
		dieWelt.addAnimal(tier);
		dieWelt.addAnimal(tier);
		dieWelt.addAnimal(tier);
		dieWelt.addAnimal(tier);
		assertEquals("Total Animals", 5, dieWelt.totalAnimals());
	}
}

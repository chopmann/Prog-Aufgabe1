package de.proglabor.aufgabe4.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import de.proglabor.aufgabe4.modell.Animal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.proglabor.aufgabe4.modell.World;

@RunWith(Parameterized.class)
public class WorldTest {

	private static final int WIDTH = 40;
	private static final int HEIGHT = 30;
	private static final int WIDTH_JUNGLE = 10;
	private static final int HEIGHT_JUNGLE = 10;
	private static final int PLANT_ENERGY = 80;
	private static final int INITIAL_ENERGY = 200;
	private static final int REPRODUCTION_ENERGY = 200;
	World dieWorld;
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
		dieWorld = new World(width, height, widthJungle, heightJungle,
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
		dieWorld.initAnimalContainer();
	}

	@Test
	public void testSetJungleLimits() {
		assertEquals("getJungleLimitY1", expectedY1, dieWorld.getJungleLimitY1());
		assertEquals("getJungleLimitY2", expectedY1 + heightJungle,
				dieWorld.getJungleLimitY2());
		assertEquals("getJungleLimitX1", expectedX1, dieWorld.getJungleLimitX1());
		assertEquals("getJungleLimitX2", expectedX1 + widthJungle,
				dieWorld.getJungleLimitX2());
	}

	@Test
	public void testAddPlant() {
		dieWorld.addPlant(15, 20);
		dieWorld.addPlant(20, 15);
		dieWorld.addPlant(20, 15);
		assertEquals(0, dieWorld.totalPlantsAt(0, 0));
		assertEquals(1, dieWorld.totalPlantsAt(15, 20));
		assertEquals(2, dieWorld.totalPlantsAt(20, 15));
	}

	@Test
	public void testRemovePlant() {
		dieWorld.addPlant(15, 20);
		dieWorld.addPlant(20, 15);
		dieWorld.addPlant(20, 15);
		dieWorld.addPlant(20, 15);
		dieWorld.removePlant(20, 15);
		assertEquals(0, dieWorld.totalPlantsAt(0, 0));
		assertEquals(1, dieWorld.totalPlantsAt(15, 20));
		assertEquals(2, dieWorld.totalPlantsAt(20, 15));
		assertEquals(3, dieWorld.totalPlants());
	}

	@Test
	public void testTotalPlants() {
		// Add 5 Plants 
		dieWorld.randomAddPlant();
		dieWorld.randomAddPlant();
		dieWorld.randomAddPlant();
		dieWorld.randomAddPlant();
		dieWorld.randomAddPlant();
		assertEquals(5, dieWorld.totalPlants());
	}
	
	@Test
	public void testTotalPlantsJungle() {
		// Add 3 Plants to the Jungle
		dieWorld.randomAddPlantJungle();
		dieWorld.randomAddPlantJungle();
		dieWorld.randomAddPlantJungle();
		assertEquals(3, dieWorld.totalPlantsJungle());
	}

	@Test
	public void testAddAnimal() {
		Animal animal = new Animal(15, 20, 100);
		dieWorld.addAnimal(animal);
		LinkedList<Animal> container = dieWorld.getContainerAnimals();
		assertEquals(0, dieWorld.totalAnimalsAt(0, 0));
		assertNotNull(dieWorld.totalAnimalsAt(15, 20));
		assertEquals(1, container.size());
	}

	@Test
	public void testAddAnimalsSpread() {
		dieWorld.randomAddAnimal();
		dieWorld.randomAddAnimal();
		dieWorld.randomAddAnimal();
		dieWorld.randomAddAnimal();
		dieWorld.randomAddAnimal();
		assertEquals("Total Animals", 5, dieWorld.totalAnimals());
	}

	@Test
	public void testAddAnimalSpot() {
		Animal animal = new Animal(15, 20, 100);
		dieWorld.addAnimal(animal);
		dieWorld.addAnimal(animal);
		dieWorld.addAnimal(animal);
		dieWorld.addAnimal(animal);
		dieWorld.addAnimal(animal);
		assertEquals("Total Animals", 5, dieWorld.totalAnimals());
	}
	
	@Test
	public void testTotalAnimalsJungle() {
		Animal animal = new Animal(WIDTH / 2, HEIGHT / 2, 100);
		Animal animal2 = new Animal(1, 1, 100);
		dieWorld.addAnimal(animal);
		dieWorld.addAnimal(animal);
		dieWorld.addAnimal(animal);
		dieWorld.addAnimal(animal);
		dieWorld.addAnimal(animal);
		dieWorld.addAnimal(animal2);
		dieWorld.addAnimal(animal2);
		assertEquals("Total Animals", 7, dieWorld.totalAnimals());
		assertEquals("Total Animals Jungle", 5, dieWorld.totalAnimalsJungle());
	}
}

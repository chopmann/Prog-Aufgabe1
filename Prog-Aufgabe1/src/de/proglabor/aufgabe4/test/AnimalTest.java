package de.proglabor.aufgabe4.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.proglabor.aufgabe4.Tier;
import de.proglabor.aufgabe4.Welt;

public class AnimalTest {
	Tier dolly = new Tier(1, 1, 10);
	int[] dollyGenes = {1, 2, 3, 4, 5, 6, 7, 8};
	private static final int WIDTH = 40;
	private static final int HEIGHT = 30;
	private static final int PLANT_ENERGY = 80;
	private static final int INITIAL_ENERGY = 200;
	private static final int MUTATION_MINUS = 0;
	private static final int MUTATION_NONE = 1;
	private static final int MUTATION_PLUS = 2;
	private static final int REPRODUCTION_ENERGY = 200;
	Welt dieWelt;
	
	@Before
	public void setUp() {
		dolly.setGenes(dollyGenes);
		dieWelt = new Welt(WIDTH, HEIGHT, 10, 10, PLANT_ENERGY, INITIAL_ENERGY, REPRODUCTION_ENERGY);
		
	}

	@Test
	public void testReproduceInt() {
		Tier dolly2 = dolly.reproduce(1, MUTATION_MINUS);
		Tier dolly3 = dolly.reproduce(2, MUTATION_NONE);
		Tier dolly4 = dolly.reproduce(3, MUTATION_PLUS);
		assertEquals(dollyGenes[1] - 1, dolly2.getGenes()[1]);
		assertEquals(dollyGenes[2], dolly3.getGenes()[2]);
		assertEquals(dollyGenes[3] + 1, dolly4.getGenes()[3]);
	}
	@Test
	public void testMoveNorthEast() {
		dolly.turn(0);
		dolly.setPos(0, 0, HEIGHT, WIDTH);
		dolly.move(HEIGHT, WIDTH);
		assertEquals(WIDTH - 1, dolly.getX());
		assertEquals(HEIGHT - 1, dolly.getY());
	}
	
	@Test
	public void testMoveSouthEast() {
		dolly.turn(6);
		dolly.setPos(0, HEIGHT -1, HEIGHT, WIDTH);
		dolly.move(HEIGHT, WIDTH);
		assertEquals(WIDTH - 1, dolly.getX());
		assertEquals(0, dolly.getY());
	}
	
	@Test
	public void testMoveNorthWest() {
		dolly.turn(2);
		dolly.setPos(WIDTH - 1, 0, HEIGHT, WIDTH);
		dolly.move(HEIGHT, WIDTH);
		assertEquals(0, dolly.getX());
		assertEquals(HEIGHT - 1, dolly.getY());
	}
	
	@Test
	public void testMoveSouthWest() {
		dolly.turn(4);
		dolly.setPos(WIDTH - 1, HEIGHT - 1, HEIGHT, WIDTH);
		dolly.move(HEIGHT,WIDTH);
		assertEquals(0, dolly.getX());
		assertEquals(0, dolly.getY());
	}
}

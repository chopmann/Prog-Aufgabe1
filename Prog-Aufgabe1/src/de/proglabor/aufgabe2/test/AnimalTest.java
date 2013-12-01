package de.proglabor.aufgabe2.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.proglabor.aufgabe2.Tier;
import de.proglabor.aufgabe2.Welt;

public class AnimalTest {
	Tier dolly = new Tier(10, 1, 1);
	int[] dollyGenes = {1, 2, 3, 4, 5, 6, 7, 8};
	final int WIDTH = 40;
	final int HEIGHT = 30;
	final int MUTATION_MINUS = 0;
	final int MUTATION_NONE = 1;
	final int MUTATION_PLUS = 2;
	Welt dieWelt;
	
	@Before
	public void setUp() {
		dolly.setGenes(dollyGenes);
		dieWelt = new Welt(WIDTH, HEIGHT, 10, 10);
		
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
		dolly.setDir(0);
		dolly.setPos(0, 0, HEIGHT, WIDTH);
		dolly.move(HEIGHT, WIDTH);
		assertEquals(WIDTH - 1, dolly.getX());
		assertEquals(HEIGHT - 1, dolly.getY());
	}
	
	@Test
	public void testMoveSouthEast() {
		dolly.setDir(6);
		dolly.setPos(0, HEIGHT -1, HEIGHT, WIDTH);
		dolly.move(HEIGHT, WIDTH);
		assertEquals(WIDTH - 1, dolly.getX());
		assertEquals(0, dolly.getY());
	}
	
	@Test
	public void testMoveNorthWest() {
		dolly.setDir(2);
		dolly.setPos(WIDTH - 1, 0, HEIGHT, WIDTH);
		dolly.move(HEIGHT, WIDTH);
		assertEquals(0, dolly.getX());
		assertEquals(HEIGHT - 1, dolly.getY());
	}
	
	@Test
	public void testMoveSouthWest() {
		dolly.setDir(4);
		dolly.setPos(WIDTH - 1, HEIGHT - 1, HEIGHT, WIDTH);
		dolly.move(HEIGHT,WIDTH);
		assertEquals(0, dolly.getX());
		assertEquals(0, dolly.getY());
	}
}

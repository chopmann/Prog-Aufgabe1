package de.proglabor.aufgabe1.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.proglabor.aufgabe1.Tier;

public class AnimalTest {
	Tier dolly = new Tier(10, 1, 1);
	int[] dollyGenes = {0, 1, 2, 3, 4, 5, 6, 7};
	
	@Before
	public void setUp() {
		dolly.setGenes(dollyGenes);
	}

	@Test
	public void testReproduceInt() {
		Tier dolly2 = dolly.reproduce(5, 1);
		Tier dolly3 = dolly.reproduce(0, -1);
		Tier dolly4 = dolly.reproduce(7, -1);
		int[] expectedDolly = {0, 1, 2, 3, 4, 5, 6, 7};
		assertArrayEquals(expectedDolly, dolly.getGenes());
		assertEquals(dollyGenes[5]+1, dolly2.getGenes()[5]);
		assertEquals(dollyGenes[0], dolly3.getGenes()[0]);
		assertEquals(dollyGenes[7]-1, dolly4.getGenes()[7]);
	}
	

}

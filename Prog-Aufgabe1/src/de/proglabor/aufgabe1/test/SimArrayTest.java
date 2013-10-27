package de.proglabor.aufgabe1.test;

import de.proglabor.aufgabe1.SimArray;
import de.proglabor.aufgabe1.SimArrayInterface;
import static org.junit.Assert.*;

import org.junit.Test;

public class SimArrayTest {

	@Test
	public void test() {
		SimArray sim = new SimArray(12, 12, 3 , 3, 50, 200);
		System.out.println("Day Z");
		sim.consoleOut();
		
		for (int i = 1; i <= 10000; i++) {
//			System.out.println("Day" + i);
			sim.day();
			
		}
		sim.consoleOut();
		
	}

}

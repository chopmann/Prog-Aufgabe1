package de.proglabor.aufgabe2.test;

import static org.junit.Assert.*;

import org.junit.Test;

import de.proglabor.aufgabe2.Helper;

public class ParamCleanerTest {

	@Test
	public void testCleaner() {
		int maxAllowedValued = 7;
		int cleanValue = 4;
		int dirtyValue = -1;
		assertEquals(cleanValue, Helper.cleaner(cleanValue, maxAllowedValued));
		assertEquals(maxAllowedValued, Helper.cleaner(maxAllowedValued, maxAllowedValued));
	}

}

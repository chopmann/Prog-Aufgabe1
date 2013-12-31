package de.proglabor.aufgabe4.test;

import static org.junit.Assert.*;

import org.junit.Test;

import de.proglabor.aufgabe4.helper.Helper;

public class ParamCleanerTest {

	@Test
	public void testCleaner() {
		int maxAllowedValued = 7;
		int cleanValue = 4;
		assertEquals(cleanValue, Helper.cleaner(cleanValue, maxAllowedValued));
		assertEquals(maxAllowedValued, Helper.cleaner(maxAllowedValued, maxAllowedValued));
	}

}

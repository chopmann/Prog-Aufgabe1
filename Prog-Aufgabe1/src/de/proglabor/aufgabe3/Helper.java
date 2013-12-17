package de.proglabor.aufgabe3;

import java.util.Random;

/**
 * @author id261708
 *
 */
public final class Helper {
	/**
	 *  
	 */
	public static final int ARRAY_OFFSET = 1;
	
	/**
	 * http://stackoverflow.com/questions/363681/generating-random-numbers-in-a-
	 * range-with-java Returns a psuedo-random number between min and max,
	 * inclusive. The difference between min and max can be at most
	 * <code>Integer.MAX_VALUE - 1</code>.
	 * 
	 * @param min
	 *            Minimim value
	 * @param max
	 *            Maximim value. Must be greater than min.
	 * @param rand
	 *            Random Generator.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random#nextInt(int)
	 */
	public static int randInt(int min, int max, Random rand) {

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	
	/**
	 * spiegelt die Welt,sodass das Tier �ber den Rand laufen kann.
	 * @param position 
	 * @param maxPositionValue Rand Maximum Koordinate
	 * @return einen Wert, der in der Welt liegt.
	 */
	public static int mirror(int position, int maxPositionValue) {
		if (0 <= position && position <= maxPositionValue) {
			return position;
		} else if (position < 0) {
			return maxPositionValue + position + ARRAY_OFFSET;
		} else {
			return position - maxPositionValue - ARRAY_OFFSET;
		}
	}
	
	/**
	 * Parameterbereiniger
	 * @param eingabe 
	 * @param n Intervall
	 * @return bereinigter Parameter im Intervall
	 */
	public static int cleaner(int eingabe, int n) {
		
		int result = eingabe;
		if ( eingabe < 0 ||  eingabe > n) {
			result = Math.abs(eingabe) % n;
		}
		return result;
	}
}

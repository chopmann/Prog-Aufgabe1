package de.proglabor.aufgabe2;

import java.util.Random;

public final class Helper {
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
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random#nextInt(int)
	 */
	public static int randInt(int min, int max, Random rand) {

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	
	public static int mirror(int position, int length) {

		if (position < 0) {
			// Wenn links der Rand erreicht ist, springe nach ganz rechts
			int tmp = length + position;
			return tmp;
		} else if (position >= length) {
			// Wenn rechts der Rand erreicht ist, springe nach ganz links
			int tmp = position - length;
			return tmp;
		} else {
			// Unveraenderte Position
			return position;
		}
	}
	
	public static int cleaner(int eingabe, int n) {
		int result = Math.abs(eingabe) % n;
		return result;
	}
}

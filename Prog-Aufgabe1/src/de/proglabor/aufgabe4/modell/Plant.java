package de.proglabor.aufgabe4.modell;

/**
 * @author SirMonkey
 *
 */
public class Plant implements Comparable<Plant> {

	private int x;
	private int y;

	/**
	 * Konstruktor
	 * @param x Koordinate	
	 * @param y Koordinate
	 */
	public Plant(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return die x-Koordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return y-Koordinate
	 */
	public int getY() {
		return y;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Plant anderePlant) {
		if (this.x == anderePlant.x && this.y == anderePlant.y) {
			return 0;
		} else if (this.x < anderePlant.x ) {
			return -1;
		} else {
			return 1;
		}
	}
	
}

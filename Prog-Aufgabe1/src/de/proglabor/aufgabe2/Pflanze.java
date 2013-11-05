package de.proglabor.aufgabe2;

/**
 * @author SirMonkey
 *
 */
public class Pflanze implements Comparable<Pflanze> {

	private int x;
	private int y;

	public Pflanze(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int compareTo(Pflanze anderePflanze) {
		if (this.x == anderePflanze.x) {
			return 0;
		} else if (this.x < anderePflanze.x) {
			return -1;
		} else {
			return 1;
		}
	}
	
}

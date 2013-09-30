/**
 * 
 */
package de.proglabor.aufgabe1;

import java.util.Random;

/**
 * @author sirmonkey
 * 
 */
public class World {

	private int width = 0;
	private int height = 0;
	private int[][] plantContainer;

	/**
	 * @param width
	 * @param height
	 */
	public World(int width, int height) {
		this.width = width;
		this.height = height;
		this.plantContainer = new int[width][height];
	}
	
	/**
	 * Set every Coordinate in the World to 0 "Plants"
	 */
	public void initPlantContainer() {
		if (plantContainer != null) {
			for(int i = 0; i<width; i++) {
				for (int j = 0; j < height; j++) {
					plantContainer[i][j] = 0;
				}
			}
		}
	}
	
	/**
	 * Add a Plant at the Coordinates
	 * @param x
	 * @param y
	 */
	public void addPlant(int x, int y) {
		plantContainer[x][y]++;
	}
	
	/**
	 * Add a Plant at a Random Location inside the World
	 */
	public void randomAddPlant() {
		Random rand = new Random();
		int x = randInt(0, width-1, rand);
		int y = randInt(0, height-1, rand);
		addPlant(x, y);

	}
	
	
	/**
	 * @param x Coordinate
	 * @param y Coordinate
	 * @return Plant count at a Given Coordinate
	 */
	public int countPlants(int x, int y) {
		return plantContainer[x][y];
	}
	
	/**
	 * @return The Total Plant Count
	 */
	public int totalPlants() {
		int total = 0;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				total += countPlants(i, j);
			}
		}
		return total;
	}

	/**
	 * @return the plantContainer
	 */
	public int[][] getPlantContainer() {
		return plantContainer;
	}
	
	/**
	 * http://stackoverflow.com/questions/363681/generating-random-numbers-in-a-range-with-java
	 * Returns a psuedo-random number between min and max, inclusive.
	 * The difference between min and max can be at most
	 * <code>Integer.MAX_VALUE - 1</code>.
	 *
	 * @param min Minimim value
	 * @param max Maximim value.  Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random#nextInt(int)
	 */
	public static int randInt(int min, int max, Random rand) {

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}

}

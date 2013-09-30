/**
 * 
 */
package de.proglabor.aufgabe1;

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
	 * @return the plantContainer
	 */
	public int[][] getPlantContainer() {
		return plantContainer;
	}

}

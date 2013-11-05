package de.proglabor.aufgabe2;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author sirmonkey
 * 
 */
public class Welt {

	private static int width = 0;
	private static int height = 0;
	private int[][] plantContainer;
	private int plantEnergy = 0;

	private int widthJungle = 0;
	private int heightJungle = 0;
	private int jungleLimitX1 = 0;
	private int jungleLimitX2 = 0;
	private int jungleLimitY1 = 0;
	private int jungleLimitY2 = 0;

	private CopyOnWriteArrayList<Tier>[][] animalContainer;
	private int initialEnergy = 0;
	private int reproductionEnergy = 0;
	
	public static int bornCount = 0;
	public static int deadCount = 0;

	/**
	 * @param width
	 * @param height
	 */
	public Welt(int width, int height, int widthJungle, int heightJungle) {
		this.width = width;
		this.height = height;
		this.widthJungle = widthJungle;
		this.heightJungle = heightJungle;
		this.plantContainer = new int[width][height];
		this.animalContainer = new CopyOnWriteArrayList[width][height];
	}

	/**
	 * @return the initialEnergy
	 */
	public int getInitialEnergy() {
		return initialEnergy;
	}

	/**
	 * @param initialEnergy
	 *            the initialEnergy to set
	 */
	public void setInitialEnergy(int initialEnergy) {
		this.initialEnergy = initialEnergy;
	}

	/**
	 * @param plantEnergy the plantEnergy to set
	 */
	public void setPlantEnergy(int plantEnergy) {
		this.plantEnergy = plantEnergy;
	}

	/**
	 * Set every Coordinate in the World to 0 "Plants"
	 */
	public void initPlantContainer() {
		if (plantContainer != null) {
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					plantContainer[i][j] = 0;
				}
			}
		}
	}

	// Plant Stuff
	/**
	 * Add a Plant at the Coordinates
	 * 
	 * @param x
	 * @param y
	 */
	public void addPlant(int x, int y) {
		plantContainer[x][y]++;
	}

	/**
	 * Removes a Plant at the Given X,Y Coordinates.
	 * @param x Coordinate
	 * @param y Coordinate
	 * @return The Energy of the Plant removed or if no plant was found 0.
	 */
	public int removePlant(int x, int y) {
		if (plantContainer[x][y] <= 0) {
			plantContainer[x][y] = 0;
			return 0;
		} else {
			plantContainer[x][y]--;
			return plantEnergy;
		}
	}

	/**
	 * Add a Plant at a Random Location inside the World
	 */
	public void randomAddPlant() {
		Random rand = new Random();
		int x = Helper.randInt(0, width - 1, rand);
		int y = Helper.randInt(0, height - 1, rand);
		addPlant(x, y);

	}
	public void randomAddPlantJungle() {
		Random rand = new Random();
		int x = Helper.randInt(jungleLimitX1, jungleLimitX2-1, rand);
		int y = Helper.randInt(jungleLimitY1, jungleLimitY2-1, rand);
		addPlant(x, y);

	}

	/**
	 * @param x
	 *            Coordinate
	 * @param y
	 *            Coordinate
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

	// Animal Stuff
	public void initAnimalContainer() {
		animalContainer = new CopyOnWriteArrayList[width][height];
	}

	/**
	 * Add an Animal at a Random Location inside the World
	 * 
	 * @param x
	 * @param y
	 */

	public void addAnimal(int x, int y) {
		if (animalContainer[x][y] != null) {
			animalContainer[x][y].add(new Tier(initialEnergy, x, y));
		} else {
			animalContainer[x][y] = new CopyOnWriteArrayList<Tier>();
			animalContainer[x][y].add(new Tier(initialEnergy, x, y));
		}

	}

	public void randomAddAnimal() {
		Random rand = new Random();
		int x = Helper.randInt(0, width - 1, rand);
		int y = Helper.randInt(0, height - 1, rand);
		addAnimal(x, y);

	}
	public void moveAnimal(CopyOnWriteArrayList<Tier> animalList, Tier milka) {
		animalList.remove(milka);
		int newX = milka.getX();
		int newY = milka.getY();
		if (animalContainer[newX][newY] != null) {
			animalContainer[newX][newY].add(milka);
		} else {
			animalContainer[newX][newY] = new CopyOnWriteArrayList<Tier>();
			animalContainer[newX][newY].add(milka);
		}
		
	}

	/**
	 * @return the reproductionEnergy
	 */
	public int getReproductionEnergy() {
		return reproductionEnergy;
	}

	/**
	 * @param reproductionEnergy
	 *            the reproductionEnergy to set
	 */
	public void setReproductionEnergy(int reproductionEnergy) {
		this.reproductionEnergy = reproductionEnergy;
	}
	public void animalAction(Tier tier) {
		//eat
		if (countPlants(tier.getX(), tier.getY()) >= 1) {
			tier.eat(plantEnergy);
			removePlant(tier.getX(), tier.posY);
		}
		tier.turn();
		//reproduce
		if (tier.energy >= reproductionEnergy) {
			Tier baby = tier.reproduce();
			int x = baby.getX();
			int y = baby.getY();
			animalContainer[x][y].add(baby);
			bornCount++;
		}
		//Move
		int x = tier.getX();
		int y = tier.getY();
		tier.move();
		tier.energyDecay(4);
		if (tier.getEnergy() == 0) {
			animalContainer[x][y].remove(tier);
			deadCount++;
		} else {
			moveAnimal(animalContainer[x][y], tier);
		}
		
		
	}

	public int countAnimals(int x, int y) {
		if (animalContainer[x][y] != null) {
			return animalContainer[x][y].size();
		} else {
			return 0;
		}
			
	}
	public int totalAnimals() {
		int total = 0;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				total += countAnimals(i, j);
			}
		}
		return total;
	}

	/**
	 * @return the animalContainer
	 */

	public CopyOnWriteArrayList<Tier>[][] getAnimalContainer() {
		return animalContainer;
	}

	// Jungle Stuff

	/**
	 * Welcome to the Jungle we have funny Games.
	 */
	public void setJungleLimits() {
		jungleLimitX1 = (width - widthJungle) / 2;
		jungleLimitX2 = (width + widthJungle) / 2;
		jungleLimitY1 = (height - heightJungle) / 2;
		jungleLimitY2 = (height + heightJungle) / 2;
	}

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}
	
	/**
	 * @return the jungleLimitX1
	 */
	public int getJungleLimitX1() {
		return jungleLimitX1;
	}

	/**
	 * @return the jungleLimitX2
	 */
	public int getJungleLimitX2() {
		return jungleLimitX2;
	}

	/**
	 * @return the jungleLimitY1
	 */
	public int getJungleLimitY1() {
		return jungleLimitY1;
	}

	/**
	 * @return the jungleLimitY2
	 */
	public int getJungleLimitY2() {
		return jungleLimitY2;
	}

}

package de.proglabor.aufgabe2;

import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author sirmonkey
 * 
 */
public class Welt {

	private static int width = 0;
	private static int height = 0;
	private TreeMap<Pflanze, Integer> plantContainer;
	private int plantEnergy = 0;

	private int widthJungle = 0;
	private int heightJungle = 0;
	private int jungleLimitX1 = 0;
	private int jungleLimitX2 = 0;
	private int jungleLimitY1 = 0;
	private int jungleLimitY2 = 0;

	private LinkedList<Tier> animalContainer;
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
		this.plantContainer = new TreeMap<Pflanze, Integer>();
		this.animalContainer = new LinkedList<Tier>();
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

	// Plant Stuff
	/**
	 * Add a Plant at the Coordinates
	 * 
	 * @param x
	 * @param y
	 */
	public void addPlant(int x, int y) {
		Pflanze key = new Pflanze(x, y);
		Integer value = plantContainer.get(key);
		if (plantContainer.get(key) == null) {
			value = 0;
		}
		value++;
		plantContainer.put(key, value);
	}

	/**
	 * Removes a Plant at the Given X,Y Coordinates.
	 * @param x Coordinate
	 * @param y Coordinate
	 * @return The Energy of the Plant removed or if no plant was found 0.
	 */
	public void removePlant(int x, int y) {
		int count = countPlants(x, y);
		if (count > 0) {
			Pflanze key = new Pflanze(x, y);
			count--;
			plantContainer.put(key, count);
			System.out.println("Remove Plant!");
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
		Pflanze key = new Pflanze(x, y);
		Integer value = plantContainer.get(key);
		if (plantContainer.get(key) == null) {
			return  0;
		} else {
			return	value;
		}
	}

	/**
	 * @return The Total Plant Count
	 */
	public int totalPlants() {
		int total = 0;
		for (Map.Entry<Pflanze, Integer> entry : plantContainer.entrySet()) {
			Integer value = entry.getValue();
			total += value;
		}
		return total;
	}

	/**
	 * @return the plantContainer
	 */
	public TreeMap<Pflanze, Integer> getPlantContainer() {
		return plantContainer;
	}

	// Animal Stuff
	/**
	 * Add an Animal at a Random Location inside the World
	 * 
	 * @param x
	 * @param y
	 */

	public void addAnimal(Tier tier) {
		animalContainer.add(tier);
	}

	public void randomAddAnimal() {
		Random rand = new Random();
		int x = Helper.randInt(0, width - 1, rand);
		int y = Helper.randInt(0, height - 1, rand);
		Tier tier = new Tier(initialEnergy, x, y);
		addAnimal(tier);

	}
	public void moveAnimal(Tier milka) {
		
		int whereIsTheCow = animalContainer.indexOf(milka);
		animalContainer.get(whereIsTheCow).move();
		
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
			animalContainer.add(baby);
			bornCount++;
		}
		//Move
		int x = tier.getX();
		int y = tier.getY();
		tier.move();
		tier.energyDecay(4);
		if (tier.getEnergy() == 0) {
			animalContainer.remove(tier);
			deadCount++;
		} else {
			moveAnimal(tier);
		}
		
		
	}

	public int countAnimals(int x, int y) {	
		int counter = 0;
		for (Tier tier : animalContainer) {
			if ( tier.getX() == x && tier.getY() == y) {
				counter++;
			}
		}
		return counter;
	}
	public int totalAnimals() {
		return animalContainer.size();
	}

	/**
	 * @return the animalContainer
	 */

	public LinkedList<Tier> getAnimalContainer() {
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

	public void initAnimalContainer() {
		this.animalContainer = new LinkedList<Tier>();
		
	}

}

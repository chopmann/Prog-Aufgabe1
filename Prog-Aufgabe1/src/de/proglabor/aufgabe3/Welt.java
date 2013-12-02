package de.proglabor.aufgabe3;

import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * @author sirmonkey
 * 
 */
public class Welt {

	private int width = 0;
	private int height = 0;
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

	/**
	 * z�hlt die geborenen Tiere
	 */
	private int bornCount = 0;
	/**
	 * z�hlt die gestorbenen Tiere
	 */
	private int deadCount = 0;

	/**
	 * z�hlt die gepflanzte Pflanzen
	 */
	private int planted = 0;
	/**
	 * z�hlt die gegessen Pflanzen
	 */
	private int eaten = 0;

	/**
	 * Konstruktor
	 * 
	 * @param height
	 *            H�he der Welt
	 * @param width
	 *            Breite der Welt
	 * @param widthJungle
	 *            Breite des Jungels
	 * @param heightJungle
	 *            H�he des Jungels
	 * @param plantEnergy
	 *            How much Energy Plants provide
	 * @param initialEnergy
	 *            of the Animals
	 * @param reproductionEnergy
	 *            Reproduction Threshold
	 */
	public Welt(int width, int height, int widthJungle, int heightJungle,
			int plantEnergy, int initialEnergy, int reproductionEnergy) {
		this.height = height;
		this.width = width;
		this.widthJungle = widthJungle;
		this.heightJungle = heightJungle;
		initJungleLimits();
		this.plantEnergy = plantEnergy;
		initPlantContainer();
		this.initialEnergy = initialEnergy;
		this.reproductionEnergy = reproductionEnergy;
		initAnimalContainer();

	}

	/**
	 * initialisiert den Pflanzen
	 * 
	 */
	public void initPlantContainer() {
		this.plantContainer = new TreeMap<Pflanze, Integer>();
	}

	/**
	 * initialisiert den Tiercontainer
	 * 
	 */
	public void initAnimalContainer() {
		this.animalContainer = new LinkedList<Tier>();

	}

	/**
	 * Welcome to the Jungle we have funny Games.
	 */
	private void initJungleLimits() {
		jungleLimitX1 = (width - widthJungle) / 2;
		jungleLimitX2 = (width + widthJungle) / 2;
		jungleLimitY1 = (height - heightJungle) / 2;
		jungleLimitY2 = (height + heightJungle) / 2;
	}

	// World Actions ADD

	/**
	 * Add a Plant at the Coordinates
	 * 
	 * @param x
	 *            Koordinate
	 * @param y
	 *            Koordinate
	 */
	public void addPlant(int x, int y) {
		Pflanze key = new Pflanze(x, y);
		addPlant(key);
	}

	/**
	 * f�gt eine Pflanze hinzu
	 * 
	 * @param pflanze
	 *            the Plant to be added
	 */
	public void addPlant(Pflanze pflanze) {
		planted++;
		Pflanze key = pflanze;
		Integer value = plantContainer.get(key);
		if (plantContainer.get(key) == null) {
			value = 0;
		}
		value++;
		plantContainer.put(key, value);
	}

	// World Actions REMOVE
	/**
	 * Add an Animal
	 * 
	 * @param tier
	 *            Tier
	 */
	public void addAnimal(Tier tier) {
		bornCount++;
		animalContainer.add(tier);
	}

	/**
	 * Removes a Plant at the Given X,Y Coordinates.
	 * 
	 * @param x
	 *            Coordinate
	 * @param y
	 *            Coordinate
	 */
	public void removePlant(int x, int y) {
		int count = totalPlantsAt(x, y);
		if (count > 0) {
			Pflanze key = new Pflanze(x, y);
			count--;
			plantContainer.put(key, count);
			eaten++;
		}
	}

	/**
	 * Kills an Animal
	 * 
	 * @param tier
	 *            to be killed
	 */
	public void removeAnimal(Tier tier) {
		animalContainer.remove(tier);
		deadCount++;
	}

	// Random ADD Actions
	/**
	 * Add a Plant at a Random Location inside the World
	 */
	public void randomAddPlant() {
		Random rand = new Random();
		int x = Helper.randInt(0, width - 1, rand);
		int y = Helper.randInt(0, height - 1, rand);
		addPlant(x, y);

	}

	/**
	 * f�gt eine Pflanze an eine beliebige Stelle in den Jungel
	 * 
	 */
	public void randomAddPlantJungle() {
		Random rand = new Random();
		int x = Helper.randInt(jungleLimitX1, jungleLimitX2 - 1, rand);
		int y = Helper.randInt(jungleLimitY1, jungleLimitY2 - 1, rand);
		addPlant(x, y);

	}

	/**
	 * f�gt ein tier an eine beliebige Stelle hinzu
	 */
	public void randomAddAnimal() {
		Random rand = new Random();
		int x = Helper.randInt(0, width - 1, rand);
		int y = Helper.randInt(0, height - 1, rand);
		Tier tier = new Tier(x, y, initialEnergy);
		addAnimal(tier);

	}

	// Animal Actions -- Getting Extracted to an Animal Controller maybe

	/**
	 * bewegt ein Tier in der Welt
	 * 
	 * @param milka
	 *            ein tier
	 */
	public void moveAnimal(Tier milka) {

		int whereIsTheCow = animalContainer.indexOf(milka);
		animalContainer.get(whereIsTheCow).move(height, width);

	}

	/**
	 * f�hrt alle Aktionen eines Tiers aus
	 * 
	 * @param tier
	 *            Tier
	 */
	public void animalAction(Tier tier) {
		// eat
		if (totalPlantsAt(tier.getX(), tier.getY()) >= 1) {
			tier.eat(plantEnergy);
			removePlant(tier.getX(), tier.getY());
		}
		tier.turn();
		// reproduce
		if (tier.getEnergy() >= reproductionEnergy) {
			Tier baby = tier.reproduce();
			addAnimal(baby);
		}
		// Move
		tier.move(height, width);
		tier.energyDecay(1);
		if (tier.getEnergy() == 0) {
			removeAnimal(tier);
		} else {
			moveAnimal(tier);
		}

	}

	/**
	 * @return the plantContainer
	 */
	public TreeMap<Pflanze, Integer> getContainerPlants() {
		return plantContainer;
	}

	/**
	 * @return the animalContainer
	 */

	public LinkedList<Tier> getContainerAnimals() {
		return animalContainer;
	}

	/**
	 * @param plantEnergy
	 *            the plantEnergy to set
	 */
	public void setPlantEnergy(int plantEnergy) {
		this.plantEnergy = plantEnergy;
	}

	/**
	 * @param reproductionEnergy
	 *            the reproductionEnergy to set
	 */
	public void setReproductionEnergy(int reproductionEnergy) {
		this.reproductionEnergy = reproductionEnergy;
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
	 * @param x
	 *            Coordinate
	 * @param y
	 *            Coordinate
	 * @return Plant count at a Given Coordinate
	 */
	public int totalPlantsAt(int x, int y) {
		Pflanze key = new Pflanze(x, y);
		Integer value = plantContainer.get(key);
		if (plantContainer.get(key) == null) {
			return 0;
		} else {
			return value;
		}
	}

	/**
	 * Gesamt Tieranzahl
	 * 
	 * @return gesamt anzahl der Tiere
	 */
	public int totalAnimals() {
		return animalContainer.size();
	}

	/**
	 * z�hlt die Tiere an einer bestimmten Stelle
	 * 
	 * @param x
	 *            x-Koordinate
	 * @param y
	 *            y-Koordinate
	 * @return Anzahl der Tiere
	 */
	public int totalAnimalsAt(int x, int y) {
		int counter = 0;
		for (Tier tier : animalContainer) {
			if (tier.getX() == x && tier.getY() == y) {
				counter++;
			}
		}
		return counter;
	}

	/**
	 * Breite
	 * 
	 * @return breite der Welt
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * H�he
	 * 
	 * @return h�he der Welt
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return the initialEnergy
	 */
	public int getInitialEnergy() {
		return initialEnergy;
	}

	/**
	 * @return the reproductionEnergy
	 */
	public int getReproductionEnergy() {
		return reproductionEnergy;
	}

	/**
	 * @return the planted
	 */
	public int getPlantedCount() {
		return planted;
	}

	/**
	 * @return the bornCount
	 */
	public int getBornCount() {
		return bornCount;
	}

	/**
	 * @return the eaten Plants
	 */
	public int getEatenCount() {
		return eaten;
	}

	/**
	 * @return the deadCount
	 */
	public int getDeadCount() {
		return deadCount;
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

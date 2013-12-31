package de.proglabor.aufgabe4;

import de.proglabor.aufgabe4.modell.Pflanze;
import de.proglabor.aufgabe4.modell.Tier;
import de.proglabor.aufgabe4.modell.Welt;

import java.util.LinkedList;
import java.util.TreeMap;

/**
 * @author id261708
 * 
 */
public class SimCollections implements SimCollectionsInterface {

	private static final int MAXIMUM_WORLD_X = 40;
	private static final int MAXIMUM_WORLD_Y = 30;
	private static final int JUNGLE_WIDTH = 10;
	private static final int JUNGLE_HEIGHT = 10;
	private static final int PLANT_ENERGY = 80;
	private static final int INITIAL_ENERGY = 1000;
	private static final int REPRODUCTION_ENERGY = 200;

	Welt dieWelt;

	/**
	 * Konstruktor
	 * @param width Weltbreite
	 * @param height Welth�he
	 * @param widthJungle Jungelbreite
	 * @param heightJungle Jungelh�he
	 * @param plantEnergy Pflanzenenergie
	 * @param initialEnergy Anfangsenergie der Tiere
	 * @param reproductionEnergy Grenze der �berschreitung, damit sich die Tiere vermehren 
	 */
	public SimCollections(int width, int height, int widthJungle,
			int heightJungle, int plantEnergy, int initialEnergy,
			int reproductionEnergy) {
		dieWelt = new Welt(width, height, widthJungle, heightJungle, plantEnergy, initialEnergy, reproductionEnergy);
	}

	/**
	 * Konstruktor
	 */
	public SimCollections() {
		this(MAXIMUM_WORLD_X, MAXIMUM_WORLD_Y, JUNGLE_WIDTH, JUNGLE_HEIGHT,
				PLANT_ENERGY, INITIAL_ENERGY, REPRODUCTION_ENERGY);

		// Monkey-Patch

		Tier weronika = new Tier( MAXIMUM_WORLD_X / 2,
				MAXIMUM_WORLD_Y / 2, INITIAL_ENERGY);

		dieWelt.addAnimal(weronika);
	}

	/* (non-Javadoc)
	 * @see de.proglabor.aufgabe3.SimCollectionsInterface#setTier(de.proglabor.aufgabe3.Tier)
	 */
	@Override
	public void setTier(Tier tier) {
		dieWelt.addAnimal(tier);

	}

	/* (non-Javadoc)
	 * @see de.proglabor.aufgabe3.SimCollectionsInterface#setPflanze(de.proglabor.aufgabe3.Pflanze)
	 */
	@Override
	public void setPflanze(Pflanze pflanze) {
		dieWelt.addPlant(pflanze);

	}

	/* (non-Javadoc)
	 * @see de.proglabor.aufgabe3.SimCollectionsInterface#getTiere()
	 */
	@Override
	public LinkedList<Tier> getTiere() {
		return dieWelt.getContainerAnimals();
	}

	/* (non-Javadoc)
	 * @see de.proglabor.aufgabe3.SimCollectionsInterface#day()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void day() {

		dieWelt.randomAddPlant();
		dieWelt.randomAddPlantJungle();
		LinkedList<Tier> tmp = (LinkedList<Tier>) dieWelt.getContainerAnimals()
				.clone();
		for (Tier tier : tmp) {
			dieWelt.animalAction(tier);
		}

	}

	/* (non-Javadoc)
	 * @see de.proglabor.aufgabe3.SimCollectionsInterface#getPflanzen()
	 */
	@Override
	public TreeMap<Pflanze, Integer> getPflanzen() {
		return dieWelt.getContainerPlants();
	}
	
	/**
	 * @return Planted
	 */
	public int planted() {
		return dieWelt.getPlantedCount();
	}
	
	/**
	 * @return  BornCount
	 */
	public int born() {
		return dieWelt.getBornCount();
	}
	
	/**
	 * @return  EatenCount
	 */
	public int eaten() {
		return dieWelt.getEatenCount();
	}
	
	/**
	 * @return deadCount
	 */
	public int killed() {
		return dieWelt.getDeadCount();
	}




}

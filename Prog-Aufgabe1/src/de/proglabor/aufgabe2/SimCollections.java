package de.proglabor.aufgabe2;

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
		dieWelt = new Welt(width, height, widthJungle, heightJungle);
		dieWelt.setPlantEnergy(plantEnergy);
		dieWelt.setInitialEnergy(initialEnergy);
		dieWelt.setReproductionEnergy(reproductionEnergy);
	}

	/**
	 * Konstruktor
	 */
	public SimCollections() {
		this(MAXIMUM_WORLD_X, MAXIMUM_WORLD_Y, JUNGLE_WIDTH, JUNGLE_HEIGHT,
				PLANT_ENERGY, INITIAL_ENERGY, REPRODUCTION_ENERGY);

		// Monkey-Patch

		Tier weronika = new Tier(INITIAL_ENERGY, MAXIMUM_WORLD_X / 2,
				MAXIMUM_WORLD_Y / 2);

		dieWelt.addAnimal(weronika);
	}

	/* (non-Javadoc)
	 * @see de.proglabor.aufgabe2.SimCollectionsInterface#setTier(de.proglabor.aufgabe2.Tier)
	 */
	@Override
	public void setTier(Tier tier) {
		dieWelt.addAnimal(tier);

	}

	/* (non-Javadoc)
	 * @see de.proglabor.aufgabe2.SimCollectionsInterface#setPflanze(de.proglabor.aufgabe2.Pflanze)
	 */
	@Override
	public void setPflanze(Pflanze pflanze) {
		dieWelt.addPlant(pflanze);

	}

	/* (non-Javadoc)
	 * @see de.proglabor.aufgabe2.SimCollectionsInterface#getTiere()
	 */
	@Override
	public LinkedList<Tier> getTiere() {
		return dieWelt.getAnimalContainer();
	}

	/* (non-Javadoc)
	 * @see de.proglabor.aufgabe2.SimCollectionsInterface#day()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void day() {

		dieWelt.randomAddPlant();
		dieWelt.randomAddPlantJungle();
		LinkedList<Tier> tmp = (LinkedList<Tier>) dieWelt.getAnimalContainer()
				.clone();
		for (Tier tier : tmp) {
			dieWelt.animalAction(tier);
		}

	}

	/* (non-Javadoc)
	 * @see de.proglabor.aufgabe2.SimCollectionsInterface#getPflanzen()
	 */
	@Override
	public TreeMap<Pflanze, Integer> getPflanzen() {
		return dieWelt.getPlantContainer();
	}
	
	public int planted() {
		return dieWelt.getPlanted();
	}
	
	public int born() {
		return dieWelt.getBornCount();
	}
	
	public int eaten() {
		return dieWelt.getEaten();
	}
	
	public int killed() {
		return dieWelt.getDeadCount();
	}




}

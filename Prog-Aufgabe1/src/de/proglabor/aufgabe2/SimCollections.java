package de.proglabor.aufgabe2;

import java.util.LinkedList;

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

	public SimCollections(int width, int height, int widthJungle,
			int heightJungle, int plantEnergy, int initialEnergy,
			int reproductionEnergy) {
		dieWelt = new Welt(width, height, widthJungle, heightJungle);
		dieWelt.setPlantEnergy(plantEnergy);
		dieWelt.setInitialEnergy(initialEnergy);
		dieWelt.setReproductionEnergy(reproductionEnergy);
	}

	public SimCollections() {
		this(MAXIMUM_WORLD_X, MAXIMUM_WORLD_Y, JUNGLE_WIDTH, JUNGLE_HEIGHT,
				PLANT_ENERGY, INITIAL_ENERGY, REPRODUCTION_ENERGY);

		// Monkey-Patch

		Tier weronika = new Tier(INITIAL_ENERGY, MAXIMUM_WORLD_X / 2,
				MAXIMUM_WORLD_Y / 2);

		dieWelt.addAnimal(weronika);
	}

	@Override
	public void setTier(Tier tier) {
		dieWelt.addAnimal(tier);

	}

	@Override
	public void setPflanze(Pflanze pflanze) {
		dieWelt.addPlant(pflanze);

	}

	public LinkedList<Tier> getTiere() {
		return dieWelt.getAnimalContainer();
	}

	@Override
	public void day() {

		dieWelt.randomAddPlant();
		// dieWelt.randomAddPlantJungle();
		LinkedList<Tier> tmp = (LinkedList<Tier>) dieWelt.getAnimalContainer()
				.clone();
		for (Tier tier : tmp) {
			dieWelt.animalAction(tier);
		}

	}

}

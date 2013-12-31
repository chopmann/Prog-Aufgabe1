package de.proglabor.aufgabe4;

import de.proglabor.aufgabe4.modell.Animal;
import de.proglabor.aufgabe4.modell.Plant;
import de.proglabor.aufgabe4.modell.World;

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

	World dieWorld;

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
		dieWorld = new World(width, height, widthJungle, heightJungle, plantEnergy, initialEnergy, reproductionEnergy);
	}

	/**
	 * Konstruktor
	 */
	public SimCollections() {
		this(MAXIMUM_WORLD_X, MAXIMUM_WORLD_Y, JUNGLE_WIDTH, JUNGLE_HEIGHT,
				PLANT_ENERGY, INITIAL_ENERGY, REPRODUCTION_ENERGY);

		// Monkey-Patch

		Animal weronika = new Animal( MAXIMUM_WORLD_X / 2,
				MAXIMUM_WORLD_Y / 2, INITIAL_ENERGY);

		dieWorld.addAnimal(weronika);
	}

	/* (non-Javadoc)
	 * @see de.proglabor.aufgabe3.SimCollectionsInterface#setTier(de.proglabor.aufgabe3.Animal)
	 */
	@Override
	public void setTier(Animal animal) {
		dieWorld.addAnimal(animal);

	}

	/* (non-Javadoc)
	 * @see de.proglabor.aufgabe3.SimCollectionsInterface#setPflanze(de.proglabor.aufgabe3.Plant)
	 */
	@Override
	public void setPflanze(Plant plant) {
		dieWorld.addPlant(plant);

	}

	/* (non-Javadoc)
	 * @see de.proglabor.aufgabe3.SimCollectionsInterface#getTiere()
	 */
	@Override
	public LinkedList<Animal> getTiere() {
		return dieWorld.getContainerAnimals();
	}

	/* (non-Javadoc)
	 * @see de.proglabor.aufgabe3.SimCollectionsInterface#day()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void day() {

		dieWorld.randomAddPlant();
		dieWorld.randomAddPlantJungle();
		LinkedList<Animal> tmp = (LinkedList<Animal>) dieWorld.getContainerAnimals()
				.clone();
		for (Animal animal : tmp) {
			dieWorld.animalAction(animal);
		}

	}

	/* (non-Javadoc)
	 * @see de.proglabor.aufgabe3.SimCollectionsInterface#getPflanzen()
	 */
	@Override
	public TreeMap<Plant, Integer> getPflanzen() {
		return dieWorld.getContainerPlants();
	}
	
	/**
	 * @return Planted
	 */
	public int planted() {
		return dieWorld.getPlantedCount();
	}
	
	/**
	 * @return  BornCount
	 */
	public int born() {
		return dieWorld.getBornCount();
	}
	
	/**
	 * @return  EatenCount
	 */
	public int eaten() {
		return dieWorld.getEatenCount();
	}
	
	/**
	 * @return deadCount
	 */
	public int killed() {
		return dieWorld.getDeadCount();
	}




}

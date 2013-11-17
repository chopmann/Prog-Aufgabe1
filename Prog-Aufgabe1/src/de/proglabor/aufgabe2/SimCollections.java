package de.proglabor.aufgabe2;

import java.util.LinkedList;

/**
 * @author id261708
 *
 */
public class SimCollections implements SimCollectionsInterface {

	Welt dieWelt ;
	
	public SimCollections(int width, int height, int widthJungle, int heightJungle, int plantEnergy, int initialEnergy, int reproductionEnergy) {
		dieWelt = new Welt(width, height, widthJungle, heightJungle);
		dieWelt.setPlantEnergy(plantEnergy);
		dieWelt.setInitialEnergy(initialEnergy);
		dieWelt.setReproductionEnergy(reproductionEnergy);
	}
	
	public SimCollections() {
		this(30, 40, 10, 10, 80, 1000, 200);
		
		//Monkey-Patch 
		Tier weronika = new Tier(1000, (40) / 2, (30) / 2);
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
//		dieWelt.randomAddPlantJungle();
		LinkedList<Tier> tmp = (LinkedList<Tier>) dieWelt.getAnimalContainer().clone();
		for (Tier tier : tmp) {
			dieWelt.animalAction(tier);
		}
		
	}

}

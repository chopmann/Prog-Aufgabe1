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
		Tier weronika = new Tier(1000, 40-1, 30-1);
		dieWelt.addAnimal(weronika);
	}

	@Override
	public void setTier(Tier tier) {
		dieWelt.addAnimal(tier);
		
	}

	@Override
	public void setPflanze(Pflanze pflanze) {
		// TODO Auto-generated method stub
		
	}

	public LinkedList<Tier> getTiere() {
		return dieWelt.getAnimalContainer();
	}
	
	@Override
	public void day() {
		// TODO Auto-generated method stub
		
	}

}

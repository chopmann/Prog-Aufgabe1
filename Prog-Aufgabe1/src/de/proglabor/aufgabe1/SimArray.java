package de.proglabor.aufgabe1;

import java.util.ArrayList;

public class SimArray implements SimArrayInterface {

	public static Welt dieWelt;

	
	public SimArray() {
		dieWelt = new Welt(40, 30, 10, 10);
		dieWelt.setInitialEnergy(1000);
		dieWelt.setReproductionEnergy(200);
		dieWelt.setJungleLimits();
		dieWelt.initAnimalContainer();
		dieWelt.initPlantContainer();
		dieWelt.randomAddPlant();
		dieWelt.randomAddPlantJungle();
		dieWelt.addAnimal((Welt.getWidth()) / 2, (Welt.getHeight()) / 2);
	}

	@Override
	public void day(int randomDirection, int randomReproduceGene,
			int randomReproduceMutation) {
		day();

	}
	public void day() {
		dieWelt.randomAddPlant();
		dieWelt.randomAddPlantJungle();
		for (int i = 0; i < dieWelt.getWidth(); i++) {
			for (int j = 0; j < dieWelt.getHeight(); j++) {
				if (dieWelt.countAnimals(i, j) > 0) {
					ArrayList<Tier> tmp = dieWelt.getAnimalContainer()[i][j];
					for (Tier tier : tmp) {
						dieWelt.reproduction(tier);
					}
				}
			}
		}
	}

	@Override
	public int getAnzahlTiere(int x, int y) {
		return dieWelt.countAnimals(x, y);
	}

	@Override
	public int getAnzahlPflanzen(int x, int y) {
		return dieWelt.countPlants(x, y);
	}
	
	public void simulate(int days) {
		for (int i = 1; i < days; i++) {
			day();
		}
	}

}

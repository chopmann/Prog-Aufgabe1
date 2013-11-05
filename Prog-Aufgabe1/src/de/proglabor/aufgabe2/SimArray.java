package de.proglabor.aufgabe2;

import java.util.concurrent.CopyOnWriteArrayList;

public class SimArray implements SimArrayInterface {

	public  Welt dieWelt;

	
	public SimArray() {
		dieWelt = new Welt(40, 30, 10, 10);
		dieWelt.setInitialEnergy(1000);
		dieWelt.setReproductionEnergy(200);
		dieWelt.setPlantEnergy(80);
		dieWelt.setJungleLimits();
		dieWelt.initAnimalContainer();
		dieWelt.addAnimal((Welt.getWidth()) / 2, (Welt.getHeight()) / 2);
	}
	
	public SimArray(int width, int height, int widthJungle, int heightJungle, int initialEnergy, int reproductionEnergy) {
		dieWelt = new Welt(width, height, widthJungle, heightJungle);
		dieWelt.bornCount = 0;
		dieWelt.deadCount = 0;
		dieWelt.setInitialEnergy(initialEnergy);
		dieWelt.setReproductionEnergy(reproductionEnergy);
		dieWelt.setPlantEnergy(80);
		dieWelt.setJungleLimits();
		dieWelt.initAnimalContainer();
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
					CopyOnWriteArrayList<Tier> tmp = dieWelt.getAnimalContainer()[i][j];
					for (Tier tier : tmp) {
						dieWelt.animalAction(tier);						
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
	
	public void consoleOut() {
		//TODO
		StringBuilder sb = new StringBuilder(25);
		for (int i = 0; i < dieWelt.getHeight(); i++) {
			boolean insideJungleX = dieWelt.getJungleLimitY1() <= i && i < dieWelt.getJungleLimitY2();
			for (int j = 0; j < dieWelt.getWidth(); j++) {
				boolean insideJungleY = dieWelt.getJungleLimitX1() <= j && j < dieWelt.getJungleLimitX2();
				
				if (dieWelt.countPlants(j, i) == 0 ) {
					sb.append("**");
				} else {
					if (insideJungleX && insideJungleY) {
						sb.append("J");
					}else {
						sb.append("P");
					}
					sb.append(dieWelt.countPlants(j, i));

				}
				if (dieWelt.countAnimals(j, i) == 0) {
					sb.append("**");
				}
				else {
					sb.append("T");
					sb.append(dieWelt.countAnimals(j, i));
				}
				sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.println("Plants:" + dieWelt.totalPlants() + " Animals:"+ dieWelt.totalAnimals() + " Born:"+dieWelt.bornCount +" Dead:"+dieWelt.deadCount );
		System.out.println(sb);
	}
	

}

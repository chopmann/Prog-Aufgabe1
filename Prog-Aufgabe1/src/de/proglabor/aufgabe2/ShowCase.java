package de.proglabor.aufgabe2;

public class ShowCase {

	public static void main(String[] args) {
		SimCollections sim = new SimCollections(12, 12, 6, 6, 80, 1000, 200);
		sim.dieWelt.randomAddAnimal();
		for (int i = 0; i < 1000; i++) {
			sim.day();
			outputer(sim);
		}

	}

	public static void outputer(SimCollections sim) {
		String header = "Born Count: " + sim.born() + " Dead Count: "
				+ sim.killed() + " Planted: " + sim.planted() + " Eaten: "
				+ sim.eaten() + " Ailve: " + sim.dieWelt.totalAnimals() + " Plants: "+ sim.dieWelt.totalPlants();
		StringBuilder builder = new StringBuilder(2000);

		System.out.println("Count Plants: " + sim.dieWelt.totalPlants());
		for (int i = 0; i < sim.dieWelt.getHeight(); i++) {
			for (int j = 0; j < sim.dieWelt.getWidth(); j++) {
				int animalCount = sim.dieWelt.countAnimals(j, i);
				int plantCount = sim.dieWelt.countPlants(j, i);
				if (animalCount > 9) {
					builder.append("T" + animalCount);
				} else if (animalCount > 0) {
					builder.append("TT" + animalCount);
				} else {
					builder.append("TTT");
				}
				if (plantCount > 9) {
					builder.append("P" + plantCount);
				} else if (plantCount > 0) {
					builder.append("PP" + plantCount);
				} else {
					builder.append("PPP");
				}
				builder.append("  ");
			}
			builder.append("\n");
		}
		System.out.println(header);
		System.out.println(builder);

	}

}

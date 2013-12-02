package de.proglabor.aufgabe3;

import java.util.Random;

/**
 * @author sirmonkey
 * 
 */
public class Tier implements TierInterface {
	static final int MAXX_GENE = 10;
	static final int STD_DIR = 0;
	static final int[] EMPTY_GENES = {0};
	private int x;
	private int y;
	private int energy;
	private int dir;
	private int[] genes;
	private int sumGenes;

	/**
	 * Konstruktor
	 * 
	 * @param x Koordinate
	 * @param y Koordinate
	 * @param animalStartEnergy
	 *            Anfangsenergie des Tieres
	 * @param dir
	 *            Blickrichtung
	 * @param genes
	 *            Gene
	 */
	public Tier(int x, int y, int animalStartEnergy, int dir, int[] genes) {
		this.x = x;
		this.y = y;
		this.energy = animalStartEnergy;
		this.dir = dir;
		this.genes = genes;
	}

	/**
	 * Konstruktor for Random
	 * 
	 * @param  animalStartEnergy
	 *            Anfangsenergie des Tieres
	 * @param x
	 *            Koordinate
	 * @param y
	 *            Koorinate
	 */
	public Tier(int x, int y, int animalStartEnergy) {
		this(x, y, animalStartEnergy, STD_DIR, EMPTY_GENES);
		initGenes();
	}

	/**
	 * setzt die Anfangsgene mit Random Werten
	 * 
	 */
	public void initGenes() {
		this.genes = new int[8];
		Random rand = new Random();
		for (int i = 0; i < genes.length; i++) {
			this.genes[i] = Helper.randInt(1, MAXX_GENE, rand);
		}
		sumGenes();
	}

	// Basic Animal Stats
	/**
	 * setzt Gene
	 * 
	 * @param genes
	 *            Gene
	 */
	public void setGenes(int[] genes) {
		this.genes = genes;
		sumGenes();
	}

	/**
	 * @param x Koordinate
	 * @param y Koordinate
	 * @param height der Welt
	 * @param width der Welt
	 */
	public void setPos(int x, int y, int height, int width) {
		this.x = Helper.mirror(x, width - Helper.ARRAY_OFFSET);
		this.y = Helper.mirror(y, height - Helper.ARRAY_OFFSET);
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int getEnergy() {
		return energy;
	}

	@Override
	public int getDir() {
		return dir;
	}

	@Override
	public int[] getGenes() {
		return genes;
	}

	/**
	 * Summe aller Gene
	 * 
	 * @return die Summe
	 * 
	 */
	public int sumGenes() {
		this.sumGenes = 0;
		for (int i = 0; i < genes.length; i++) {
			sumGenes += genes[i];
		}
		return sumGenes;
	}

	// Basic Animal Actions

	@Override
	public void eat(int plantEnergy) {
		energy += plantEnergy;
	}

	/**
	 * Reduziert Energie des Tieres
	 * 
	 * @param energy
	 *            H�he der zu reduzierenden Energie
	 */
	public void energyDecay(int energy) {
		this.energy -= energy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.proglabor.aufgabe1.TierInterface#turn(int)
	 */
	@Override
	public void turn(int randomDirection) {
		dir = Helper.cleaner(randomDirection, 8);
	}

	/**
	 * Change the Direction(Random) of the Animal
	 */
	public void turn() {
		Random rand = new Random();
		int value = Helper.randInt(0, sumGenes, rand);
		int lowerBound = 0;
		int upperBound = genes[0];

		if (lowerBound <= value && value <= upperBound) {
			turn(0);
		} else {
			for (int i = 1; i < genes.length; i++) {
				lowerBound = upperBound;
				upperBound += genes[i];
				if (lowerBound < value && value <= upperBound) {
					turn(i);
				}
			}
		}
	}

	@Override
	public void move(int height, int width) {
		switch (dir) {
		case 0: // NE
			setPos(x - 1, y - 1, height, width);
			break;
		case 1: // N
			setPos(x, y - 1, height, width);
			break;
		case 2: // NW
			setPos(x + 1, y - 1, height, width);
			break;
		case 3: // W
			setPos(x + 1, y, height, width);
			break;
		case 4: // SW
			setPos(x + 1, y + 1, height, width);
			break;
		case 5: // S
			setPos(x, y + 1, height, width);
			break;
		case 6: // SE
			setPos(x - 1, y + 1, height, width);
			break;
		case 7: // E
			setPos(x - 1, y, height, width);
			break;
		default: // DONT MOVE
			setPos(x, y, height, width);
			break;
		}
	}

	// Reproduction Stuff -- Animals can mutate if they reproduce.

	/**
	 * ver�ndert ein Gen des Tiers
	 * 
	 * @param gene Position des Gens
	 * @param mutation Art der Ver�nderung
	 */
	public void mutate(int gene, int mutation) {
		gene = Helper.cleaner(gene, 8);
		switch (Helper.cleaner(mutation, 2)) {

		case 0:
			mutation = -1;
			break;
		case 1:
			mutation = 0;
			break;
		case 2:
			mutation = 1;
			break;
		default:
			mutation = 0;
			break;
		}

		int mutatedGene = genes[gene] + mutation;
		if (mutatedGene <= 0) {
			genes[gene] = 1;
		} else {
			genes[gene] = mutatedGene;
		}
		sumGenes();
	}

	@Override
	public Tier reproduce(int randomGene, int randomMutation) {
		Tier newBorn = new Tier(this.x, this.y, this.energy / 2);
		int[] newBornGenes = this.genes.clone();
		newBorn.setGenes(newBornGenes);
		newBorn.mutate(randomGene, randomMutation);
		this.energy = (this.energy % 2) + (this.energy / 2);
		return newBorn;
	}

	/**
	 * @return a new Born Animal with Random mutated Genes
	 */
	public Tier reproduce() {
		Random rand = new Random();
		int randomGene = Helper.randInt(0, 7, rand);
		int randomMutation = Helper.randInt(-1, 1, rand);
		return reproduce(randomGene, randomMutation);
	}

}

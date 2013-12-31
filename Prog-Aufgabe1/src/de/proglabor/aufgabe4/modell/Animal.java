package de.proglabor.aufgabe4.modell;

import de.proglabor.aufgabe4.utils.Helper;
import de.proglabor.aufgabe4.TierInterface;

import java.util.Random;

/**
 * @author sirmonkey
 * 
 */
public class Animal implements TierInterface {
	private static int nextID = 1;
	private int id;
	private static final int MAXX_GENE = 10;
	private static final int STD_DIR = 0;
	private static final int[] EMPTY_GENES = {0};
	private final int startX;
	private final int startY;
	private int x;
	private int y;
	private int energy;
	private int dir;
	private int[] genes;
	private int sumGenes;
	private int reproduceCounter = 0;
	private int stepsCounter = 0;

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
	public Animal(int x, int y, int animalStartEnergy, int dir, int[] genes) {
		this.id = nextID++;
		this.x = x;
		this.y = y;
		this.startX = x;
		this.startY = y;
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
	public Animal(int x, int y, int animalStartEnergy) {
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
	 * @param xPos Koordinate
	 * @param yPos Koordinate
	 * @param heightWorld der World
	 * @param widthWorld der World
	 */
	public void setPos(int xPos, int yPos, int heightWorld, int widthWorld) {
		this.x = Helper.mirror(xPos, widthWorld - Helper.ARRAY_OFFSET);
		this.y = Helper.mirror(yPos, heightWorld - Helper.ARRAY_OFFSET);
	}
	
	/**
	 * @return the Animal ID
	 */
	public int getID() {
		return id;
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
	 * @param energyDecay
	 *            H�he der zu reduzierenden Energie
	 */
	public void energyDecay(int energyDecay) {
		this.energy -= energyDecay;
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
		stepsCounter++;
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
	
	/* (non-Javadoc)
	 * @see de.proglabor.aufgabe4.TierInterface#getDistanceToStart()
	 */
	@Override
	public int getDistanceToStart() {
		return Math.abs(startX - x) + Math.abs(startY - y);
	}

	/* (non-Javadoc)
	 * @see de.proglabor.aufgabe4.TierInterface#getSteps()
	 */
	@Override
	public int getSteps() {
		return stepsCounter;
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
	public Animal reproduce(int randomGene, int randomMutation) {
		reproduceCounter++;
		Animal newBorn = new Animal(this.x, this.y, this.energy / 2);
		int[] newBornGenes = this.genes.clone();
		newBorn.setGenes(newBornGenes);
		newBorn.mutate(randomGene, randomMutation);
		this.energy = (this.energy % 2) + (this.energy / 2);
		return newBorn;
	}

	/**
	 * @return a new Born Animal with Random mutated Genes
	 */
	public Animal reproduce() {
		Random rand = new Random();
		int randomGene = Helper.randInt(0, 7, rand);
		int randomMutation = Helper.randInt(-1, 1, rand);
		return reproduce(randomGene, randomMutation);
	}
	
	/* (non-Javadoc)
	 * @see de.proglabor.aufgabe4.TierInterface#getReproduceCounter()
	 */
	public int getReproduceCounter() {
		return reproduceCounter;
	}



}

package de.proglabor.aufgabe2;

import java.util.Random;

/**
 * @author sirmonkey
 * 
 */
public class Tier implements TierInterface{
	int posX;
	int posY;
	int energy;
	int dir;
	int[] genes;
	int sumGenes;

	public Tier(int energy, int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		this.energy = energy;
		this.dir = 0;
		initGenes();
	}

	public Tier(int posX, int posY, int animalStartEnergy, int dir, int[] genes) {
		this.posX = posX;
		this.posY = posY;
		this.energy = animalStartEnergy;
		this.dir = dir;
		this.genes = genes;
	}

	public void initGenes() {
		genes = new int[8];
		Random rand = new Random();
		for (int i = 0; i < genes.length; i++) {
			genes[i] = Helper.randInt(1, 10, rand);
		}
		sumGenes();
	}

	public void setGenes(int[] genes) {
		this.genes = genes;
		sumGenes();
	}
	@Override
	public int[] getGenes() {
		return genes;
	}
	@Override
	public void eat(int plantEnergy) {
		energy += plantEnergy;
	}

	/**
	 * @param randomGene
	 * @param randomMutation
	 * @return a new Born Animal with mutated Genes
	 */
	@Override
	public Tier reproduce(int randomGene, int randomMutation) {
		Tier newBorn = new Tier(this.energy / 2, this.posX, this.posY);
		int[] newBornGenes = this.genes.clone();
		newBorn.setGenes(newBornGenes);
		newBorn.mutate(randomGene, randomMutation);
		this.energy = (this.energy % 2) + ( this.energy / 2 );
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

	/**
	 * @param gene
	 * @param mutation
	 */
	public void mutate(int gene, int mutation) {
		int mutatedGene = genes[gene] + mutation;
		if (mutatedGene <= 0) {
			genes[gene] = 0;
		} else {
			genes[gene] = mutatedGene;
		}
		sumGenes();
	}

	
	public void setDir(int dir) {
		this.dir = dir;
	}
	
	@Override
	public int getDir() {
		return dir;
	}
	
	@Override
	public int getEnergy() {
		return energy;
	}

	@Override
	public int getX() {
		return posX;
	}

	@Override
	public int getY() {
		return posY;
	}

	/* (non-Javadoc)
	 * @see de.proglabor.aufgabe1.TierInterface#turn(int)
	 */
	@Override
	public void turn(int randomDirection) {
		dir = Helper.cleaner(randomDirection, 8);
	}
	
	/**
	 * Change the Direction of the Animal(Weighted Random)
	 */
	public void turn() {
		Random rand = new Random();
		int value = Helper.randInt(0, sumGenes, rand );
		int lowerBound = 0;
		int upperBound = genes[0];
		
		if (lowerBound <= value && value <= upperBound) {
			turn(0);
		} else {
			for (int i = 1; i < genes.length; i++) {
				lowerBound = upperBound;
				upperBound+= genes[i];
				if( lowerBound < value && value <= upperBound) {
					turn(i);
				}
			}
		}
	}

	/**
	 * @return
	 */
	public int sumGenes() {
		this.sumGenes = 0;
		for (int i = 0; i < genes.length; i++) {
			sumGenes += genes[i];
		}
		return sumGenes;
	}

	@Override
	public void move(int width , int  height) {
		posX = Helper.mirror(width, Welt.getWidth());
		posY = Helper.mirror(height, Welt.getHeight());
	}
	
	public void move() {
		switch (dir) {
		case 0:
			move(posX - 1, posY + 1);
			break;
		case 1:
			move(posX, posY + 1);
			break;
		case 2:
			move(posX + 1, posY + 1);
			break;
		case 3:
			move(posX + 1, posY);
			break;
		case 4:
			move(posX - 1, posY + 1);
		case 5:
			move(posX, posY - 1);
			break;
		case 6:
			move(posX - 1, posY - 1);
			break;
		case 7:
			move(posX - 1, posY);
			break;
		}
	}
	
	public void energyDecay(int energy) {
		this.energy -= energy;
	}

}

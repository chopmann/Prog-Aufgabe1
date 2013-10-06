package de.proglabor.aufgabe1;

import java.util.Random;

/**
 * @author sirmonkey
 * 
 */
public class Animal {
	int posX;
	int posY;
	int energy;
	int dir;
	int[] genes;

	public Animal(int energy, int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		this.energy = energy;
		this.dir = 0;
	}

	public void initGenes() {
		genes = new int[8];
		Random rand = new Random();
		for (int i = 0; i < genes.length; i++) {
			genes[i] = Helper.randInt(1, 10, rand);
		}
	}

	public void setGenes(int[] genes) {
		this.genes = genes;
	}

	public int[] getGenes() {
		return genes;
	}

	public void eat(int plantEnergy) {
		energy += plantEnergy;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public int getDir() {
		return dir;
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

	/**
	 * @param randomGene
	 * @param randomMutation
	 * @return a new Born Animal with mutated Genes
	 */
	public Animal reproduce(int randomGene, int randomMutation) {
		Animal newBorn = new Animal(this.energy / 2, this.posX, this.posY);
		int[] newBornGenes = this.genes.clone();
		newBorn.setGenes(newBornGenes);
		newBorn.mutate(randomGene, randomMutation);
		return newBorn;
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
	}

}

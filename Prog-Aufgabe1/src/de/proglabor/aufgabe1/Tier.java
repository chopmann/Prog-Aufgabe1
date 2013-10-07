package de.proglabor.aufgabe1;

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

	public Tier(int energy, int posX, int posY) {
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

	@Override
	public void turn(int randomDirection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(int height, int width) {
		// TODO Auto-generated method stub
		
	}

}

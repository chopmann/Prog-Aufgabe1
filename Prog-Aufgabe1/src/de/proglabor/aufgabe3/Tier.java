package de.proglabor.aufgabe3;

import java.util.Random;

/**
 * @author sirmonkey
 * 
 */
public class Tier implements TierInterface {
	static final int MAXX_GENE = 10;
	int posX;
	int posY;
	int energy;
	int dir;
	int[] genes;
	int sumGenes;
	

	/**
	 * Konstruktor
	 * @param energy Anfangsenergie des Tieres
	 * @param posX x-Koordinate
	 * @param posY y-Koorinate
	 */
	public Tier(int energy, int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		this.energy = energy;
		this.dir = 0;
		initGenes();
	}

	/**
	 * Konstruktor
	 * @param posX 
	 * @param posY 
	 * @param animalStartEnergy Anfangsenergie des Tieres
	 * @param dir Blickrichtung
	 * @param genes Gene 
	 */
	public Tier(int posX, int posY, int animalStartEnergy, int dir, int[] genes) {
		this.posX = posX;
		this.posY = posY;
		this.energy = animalStartEnergy;
		this.dir = dir;
		this.genes = genes;
	}

	/**
	 * setzt die Anfangsgene mit Random Werten
	 * 
	 */
	public void initGenes() {
		genes = new int[8];
		Random rand = new Random();
		for (int i = 0; i < genes.length; i++) {
			genes[i] = Helper.randInt(1, MAXX_GENE, rand);
		}
		sumGenes();
	}

	/**
	 * setzt Gene
	 * @param genes Gene
	 */
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
	 * Vermehrung der Tiere
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
	 * ver�ndert ein Gen des Tiers 
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

	
	/**
	 * setzt die Blickrichtung des Tieres
	 * @param dir Blickrichtung
	 */
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
				upperBound += genes[i];
				if ( lowerBound < value && value <= upperBound) {
					turn(i);
				}
			}
		}
	}

	/**
	 * Summe aller Gene 
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

	public void setPos(int x, int y, int height , int width) {
		posX = Helper.mirror(x, width - Helper.ARRAY_OFFSET);
		posY = Helper.mirror(y, height - Helper.ARRAY_OFFSET);
	}
	
	@Override
	public void move(int height , int  width) {
		switch (dir) {
		case 0: //NE
			setPos(posX -1, posY -1, height, width);
			break;
		case 1: //N
			setPos(posX, posY - 1, height, width);
			break;
		case 2: //NW
			setPos(posX + 1, posY - 1, height, width);
			break;
		case 3: //W
			setPos(posX + 1, posY, height, width);
			break;
		case 4: //SW
			setPos(posX + 1, posY + 1, height, width);
			break;
		case 5: //S
			setPos(posX, posY + 1, height, width);
			break;
		case 6: //SE
			setPos(posX - 1, posY + 1, height, width);
			break;
		case 7: //E
			setPos(posX - 1, posY, height, width);
			break;
		default: //DONT MOVE
			setPos(posX , posY,  height, width);
			break;
		}
	}
	
	/**
	 * Reduziert Energie des Tieres
	 * @param energy  H�he der zu reduzierenden Energie
	 */
	public void energyDecay(int energy) {
		this.energy -= energy;
	}



}

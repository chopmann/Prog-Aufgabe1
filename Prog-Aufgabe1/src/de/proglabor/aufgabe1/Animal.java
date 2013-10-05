/**
 * 
 */
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
	
	public void setDir(int dir ) {
		this.dir = dir;
	}
	
	public int getDir(){
		return dir;
	}
	
	public Animal reproduce() {
		Animal newBorn = new Animal(this.energy/2, this.posX, this.posY);
		return newBorn;
	}
	
	
	
}

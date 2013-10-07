package de.proglabor.aufgabe1;

public interface TierInterface {
	
	public int getDir();
	
	public int [] getGenes();
	
	public int getEnergy();
	
	public int getX();
	
	public int getY();
	
	public void turn(int randomDirection);
	
	public void move (int height, int width);
	
	public void eat (int plantEnergy);
	
	public TierInterface reproduce(int randomGene, int randomMutation);
	
	public String toString();

}

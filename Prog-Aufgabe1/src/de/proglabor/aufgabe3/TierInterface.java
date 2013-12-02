package de.proglabor.aufgabe3;

/**
 * @author id261708
 *
 */
public interface TierInterface {
	
	/**
	 * 
	 * @return blickrichtung
	 */
	public int getDir();
	
	/**
	 * @return gene
	 */
	public int [] getGenes();
	
	/**
	 * @return energie
	 */
	public int getEnergy();
	
	/**
	 * @return x-Koordinate
	 */
	public int getX();
	
	/**
	 * @return y-koordinate
	 */
	public int getY();
	
	/**
	 * dreht das Tier in eine zuf�llige Blickrichtung
	 * @param randomDirection Blickrichtung
	 */
	public void turn(int randomDirection);
	
	/**
	 * bewegt das Tier
	 * @param height y-Koordinate
	 * @param width x-Koordinate
	 */
	public void move (int height, int width);
	
	/**
	 * f�gt Energie hinzu
	 * @param plantEnergy Futterenergie
	 */
	public void eat (int plantEnergy);
	
	/**
	 * Fortpflanzung des tiers
	 * @param randomGene Gen
	 * @param randomMutation Ver�nderung 
	 * @return Baby
	 */
	public TierInterface reproduce(int randomGene, int randomMutation);
	
	/**
	 * gibt eine String repr�sentation des Tiers aus
	 * @return Tier als String
	 */
	public String toString();

}

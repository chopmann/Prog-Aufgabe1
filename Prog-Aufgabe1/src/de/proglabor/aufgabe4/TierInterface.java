package de.proglabor.aufgabe4;

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
	 * @param height der Welt
	 * @param width der Welt
	 */
	public void move (int height, int width);
	
	/**
	 * @return Distanz zwischen Start und aktueller Position
	 */
	public int getDistanceToStart();
	
	/**
	 * @return Zaehlt Anzahl der Schritte eines Tieres
	 */
	public int getSteps();
	
	/**
	 * f�gt Energie hinzu
	 * @param plantEnergy Futterenergie
	 */
	public void eat (int plantEnergy);
	
	/**
	 * Vermehrung der Tiere
	 * 
	 * @param randomGene Gene der Verändert wird
	 * @param randomMutation Mutationstyp
	 * @return a new Born Animal with mutated Genes
	 */
	public TierInterface reproduce(int randomGene, int randomMutation);
	
	/**
	 * @return Kumulierte Anzahl der Reproduktionen eines Tieres
	 */
	public int getReproduceCounter();
	
	/**
	 * gibt eine String repr�sentation des Tiers aus
	 * @return Tier als String
	 */
	public String toString();

}

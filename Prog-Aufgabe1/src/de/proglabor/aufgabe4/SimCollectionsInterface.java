package de.proglabor.aufgabe4;

import java.util.LinkedList;
import java.util.TreeMap;

/**
 * @author id261708
 *
 */
public interface SimCollectionsInterface {
	
	/**
	 * f�gt ein Tier hinzu
	 * @param tier 
	 */
	public void setTier(Tier tier);
	
	/**
	 * f�gt eine Pflanze hinzu
	 * @param pflanze 
	 */
	public void setPflanze(Pflanze pflanze);
	
	/**
	 * holt den Tiercontainer
	 * @return der Tiercontainer
	 */
	public LinkedList<Tier> getTiere();
	
	/**
	 * holt den Pflanzencontainer
	 * @return Pflanzencontainer
	 */
	public TreeMap<Pflanze, Integer> getPflanzen();
	
	/**
	 * ein Tag in der Simulation
	 */
	public void day();
}

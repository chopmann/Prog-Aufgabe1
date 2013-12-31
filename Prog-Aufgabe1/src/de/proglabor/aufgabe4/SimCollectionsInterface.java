package de.proglabor.aufgabe4;

import de.proglabor.aufgabe4.modell.Animal;
import de.proglabor.aufgabe4.modell.Plant;

import java.util.LinkedList;
import java.util.TreeMap;

/**
 * @author id261708
 *
 */
public interface SimCollectionsInterface {
	
	/**
	 * f�gt ein Animal hinzu
	 * @param animal
	 */
	public void setTier(Animal animal);
	
	/**
	 * f�gt eine Plant hinzu
	 * @param plant
	 */
	public void setPflanze(Plant plant);
	
	/**
	 * holt den Tiercontainer
	 * @return der Tiercontainer
	 */
	public LinkedList<Animal> getTiere();
	
	/**
	 * holt den Pflanzencontainer
	 * @return Pflanzencontainer
	 */
	public TreeMap<Plant, Integer> getPflanzen();
	
	/**
	 * ein Tag in der Simulation
	 */
	public void day();
}

package de.proglabor.aufgabe1;

public interface SimArrayInterface {

	// Ablauf eines Tages in der Simulation
	public void day(int randomDirection, int randomReproduceGene,
			int randomReproduceMutation);

	public int getAnzahlTiere(int x, int y);

	public int getAnzahlPflanzen(int x, int y);
}
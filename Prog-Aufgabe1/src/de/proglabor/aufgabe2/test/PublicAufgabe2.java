package de.proglabor.aufgabe2.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.proglabor.aufgabe2.SimCollections;
import de.proglabor.aufgabe2.Tier;
import de.proglabor.aufgabe2.TierInterface;

/**
 * Tests for the second practical work.
 * @author philipp wentscher
 *
 */
public class PublicAufgabe2 {

	private static final int TIMEOUT = 1000;
	private static final int FLOORED_HALF = 500;
	private static final int CEILD_HALF = 501;
	private static final int ANIMAL_START_ENERGY = 1000;
	private static final int UN_NORMAL_GENE = 10;
	private static final int MAXIMUM_WORLD_X = 40;
	private static final int MAXIMUM_WORLD_Y = 30;
	private static final int UNIFORM_INTERVAL = 1 + Integer.MAX_VALUE / 8;
	private static final int NORTH_EAST = 0;
	private static final int NORTH = 1;
	private static final int WEST = 3;
	private static final int SOUTH_WEST = 4;
	private SimCollections sim;
	private Tier uniformGenesAnimal;
	private Tier southWestRunningAnimal;
	private Tier oddEnergyAnimal;
	private Tier unUniformGenesAnimal;
	
	private int getAnimalCount(int xPos, int yPos) {
		int result = 0;
		for (Tier curAnimal : this.sim.getTiere()) {
			if (curAnimal.getX() == xPos
					&& curAnimal.getY() == yPos) {
				result++;
			}
		}
		return result;
	}
	
	private int getAnimalCountInSim(int xmax, int ymax) {
		int result = 0;
		for (int x = 0; x < xmax; x++) {
			for (int y = 0; y < ymax; y++) {
				result += this.getAnimalCount(x, y);
			}
		}
		return result;
	}
	
	/**
	 * This method initialize several attributes before each test-method.
	 */
	@Before
	public void setup() {
		
		this.sim = new SimCollections();
		int[] genes = {1, 1, 1, 1, 1, 1, 1, 1};
		int[] unGenes = {1, UN_NORMAL_GENE, 1, 1, 1, 1, 1, 1};
		this.uniformGenesAnimal = new Tier(MAXIMUM_WORLD_X / 2, MAXIMUM_WORLD_Y / 2, 
				ANIMAL_START_ENERGY, 0, genes);
		this.southWestRunningAnimal = new Tier(MAXIMUM_WORLD_X - 1, MAXIMUM_WORLD_Y - 1,
				ANIMAL_START_ENERGY, 0, genes);
		this.unUniformGenesAnimal = new Tier(MAXIMUM_WORLD_X / 2, MAXIMUM_WORLD_Y / 2,
				ANIMAL_START_ENERGY, 0, unGenes);
		this.oddEnergyAnimal = new Tier(MAXIMUM_WORLD_X / 2, MAXIMUM_WORLD_Y / 2,
				ANIMAL_START_ENERGY + 1, 0, unGenes);
	}
	
	/**
	 * This test checks if there are two animals after one day.
	 */
	@Test(timeout = TIMEOUT)
	public void animalReproduce() {
		sim.day();
		int animalCount = getAnimalCountInSim(MAXIMUM_WORLD_X, MAXIMUM_WORLD_Y);
		assertEquals("After one round there should be two animals. " + animalCount + " where found.", 2, animalCount);
	}
	
	/**
	 * This test checks if there is one Animal after initialization.
	 */
	@Test(timeout = TIMEOUT)
	public void animalInitialCountTest() {
		assertEquals(1, getAnimalCountInSim(MAXIMUM_WORLD_X, MAXIMUM_WORLD_Y));
	}
	
	/**
	 * This test checks if the animal is in the correct start position.
	 */
	@Test(timeout = TIMEOUT)
	public void animalInitialPosition() {
		assertEquals(1, this.getAnimalCount(MAXIMUM_WORLD_X / 2, MAXIMUM_WORLD_Y / 2));
	}
	
	/**
	 * This test test the Tier.turn(int randomDirection) Method
	 * with different Values for each direction.
	 */
	@Test(timeout = TIMEOUT)
	public void turnTest() {
		int randomNorthEast = NORTH_EAST + UNIFORM_INTERVAL;
		int randomNorth = NORTH + UNIFORM_INTERVAL;
		int randomWest = WEST + UNIFORM_INTERVAL;
		int randomSouthWest = SOUTH_WEST + UNIFORM_INTERVAL;
		
		this.uniformGenesAnimal.turn(randomNorthEast);
		assertEquals("After the turn operation the animal.getDir() should be 0." 
				+ " The turn-value was: " + randomNorthEast, 
				0, this.uniformGenesAnimal.getDir());
		
		this.uniformGenesAnimal.turn(-randomNorth);
		assertEquals("After the turn operation the animal.getDir() should be 1." 
				+ " The turn-value was: " + (-randomNorth),
				1, this.uniformGenesAnimal.getDir());
		
		this.uniformGenesAnimal.turn(-randomWest);
		assertEquals("After the turn operation the animal.getDir() should be 3." 
				+ " The turn-value was: " + (-randomWest),
				3, this.uniformGenesAnimal.getDir());
		
		this.uniformGenesAnimal.turn(randomSouthWest);
		assertEquals("After the turn operation the animal.getDir() should be 4." 
				+ " The turn-value was: " + (randomSouthWest),
				4, this.uniformGenesAnimal.getDir());
	}
	
	/**
	 * This test checks if the animal appears on the other side of the
	 * world, when it leaves the south west border.
	 */
	@Test(timeout = TIMEOUT)
	public void moveAnimalBorderSouthWestTest() {
		this.sim.setTier(this.southWestRunningAnimal);
		this.southWestRunningAnimal.turn(SOUTH_WEST);
		this.southWestRunningAnimal.move(MAXIMUM_WORLD_Y, MAXIMUM_WORLD_X);
		assertEquals("After crossing the southeast worldborder the x position, "
				+ "should be 0.",
				0, this.southWestRunningAnimal.getX());
		assertEquals("After crossing the southeast worldborder the y position, "
				+ "should be 0.",
				0, this.southWestRunningAnimal.getY());
	}
	
	/**
	 * This test checks if the mutation successfully mutate the expected gene.
	 */
	@Test(timeout = TIMEOUT)
	public void geneMutationTest() {
		this.sim.setTier(this.uniformGenesAnimal);
		TierInterface test = this.uniformGenesAnimal.reproduce(7, 2);
		assertEquals("After calling Tier.reproduce(7,2) the 7th gene should " + 
				"be increased", 2, test.getGenes()[7]);
		
		int[] expectedGenes = {1, 1, 1, 1, 1, 1, 1, 1};
		assertArrayEquals("No mutations of parent animals genes are allowed", 
				expectedGenes, uniformGenesAnimal.getGenes());
	}
	
	/**
	 * This test checks if the mutation handles correctly the limit cases.
	 * The genes of unUniformedGenesAnimals are: 
	 * {1, 10, 1, 1, 1, 1, 1, 1}
	 */
	@Test(timeout = TIMEOUT)
	public void geneLimitMutationTest() {
		this.sim.setTier(this.unUniformGenesAnimal);
		TierInterface test = this.unUniformGenesAnimal.reproduce(0, 0);
		assertEquals("The minimum of a gene value is 1",
				1, test.getGenes()[0]);
		test = this.unUniformGenesAnimal.reproduce(1, 2);
		assertEquals("There is no maximum value for a gene",
				UN_NORMAL_GENE + 1, test.getGenes()[1]);
		
		test = this.unUniformGenesAnimal.reproduce(Integer.MAX_VALUE, 2);
		assertEquals("the call of 'reproduce(Integer.MAX_VALUE, 0)' should "
				+ "be transformed that it affect gen[7]"
				+ " the test genes were {1, 10, 1, 1, 1, 1, 1, 1}",
				2, test.getGenes()[7]);
	}
	
	/**
	 * This test checks if the animals energy is divided correctly during 
	 * one reproduction.
	 */
	@Test(timeout = TIMEOUT)
	public void animalEnergySplit() {
		TierInterface test = this.oddEnergyAnimal.reproduce(1, 1);
		assertEquals("After the wirst reproduction the parent Tier.energie should be 501."
				+ " With an odd start energy.",
				CEILD_HALF, this.oddEnergyAnimal.getEnergy());
		assertEquals("After the second reproduction the child Tier.energie should be 500."
				+ " With an odd start energy.",
				FLOORED_HALF, test.getEnergy());
	}
}
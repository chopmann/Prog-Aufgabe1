package de.proglabor.aufgabe4.test;

import static org.junit.Assert.*;

import de.proglabor.aufgabe4.console.RuntimeConfiguration;
import org.junit.Before;
import org.junit.Test;

import de.proglabor.aufgabe4.exceptions.UnknownKeywordException;
import de.proglabor.aufgabe4.exceptions.WrongValueException;

public class PublicAufgabe4 {

	RuntimeConfiguration runtimeConfiguration = null;
	private static final int TIMEOUT = 1000;
	
	@Before
	public void setUp() throws Exception {
		runtimeConfiguration = RuntimeConfiguration.getInstance();
	}
	
	@Test(timeout = TIMEOUT)
	public void help() {
		String [] args = new String[6];
		args[0] = "-help";
		args[1] = "-simulation";
		args[2] = "-simcount";
		args[3] = "10";
		args[4] = "-tiere";
		args[5] = "-pflanze";
		
		try {
			runtimeConfiguration.parse(args);
			assertFalse("-pflanze", runtimeConfiguration.createPlantReport());
			assertFalse("-tiere", runtimeConfiguration.createAnimalReport());
			assertFalse("-bewegung", runtimeConfiguration.createMovementReport());
			assertFalse("-reproduce", runtimeConfiguration.getReportReproduce());
			assertFalse("-simulation", runtimeConfiguration.showSimulation());
		} 
		catch (WrongValueException | UnknownKeywordException e) {
			fail(e.toString());
		}
	}

	@Test(timeout = TIMEOUT)
	public void createReportBewegung() {
		String [] args = new String[3];
		args[0] = "-simcount";
		args[1] = "1";
		args[2] = "-bewegung";
		try {
			runtimeConfiguration.parse(args);
			assertTrue("-bewegung", runtimeConfiguration.createMovementReport());
		} 
		catch (WrongValueException | UnknownKeywordException e) {
			fail(e.toString());
		}
	}
	
	@Test(timeout = TIMEOUT)
	public void createReportPflanze(){
		String [] args = new String[3];
		args[0] = "-pflanze";
		args[1] = "-simcount";
		args[2] = "1";
		try {
			runtimeConfiguration.parse(args);
			assertTrue("-pflanze", runtimeConfiguration.createPlantReport());
		} 
		catch (WrongValueException | UnknownKeywordException e) {
			fail(e.toString());
		}
	}

	@Test(timeout = TIMEOUT)
	public void noneSimcount() {
		String [] strings = new String[2];
		strings[0] = "-tiere";
		strings[1] = "-pflanze";
		try {
			runtimeConfiguration.parse(strings);
		}
		catch (WrongValueException wve) {
			fail(wve.toString());
		} 
		catch (UnknownKeywordException uke) {
			
		}		
	}
	
}
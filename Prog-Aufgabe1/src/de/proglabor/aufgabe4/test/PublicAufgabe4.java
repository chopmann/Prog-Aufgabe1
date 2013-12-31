package de.proglabor.aufgabe4.test;

import static org.junit.Assert.*;

import de.proglabor.aufgabe4.console.ArgumentParser;
import org.junit.Before;
import org.junit.Test;

import de.proglabor.aufgabe4.exceptions.UnknownKeywordException;
import de.proglabor.aufgabe4.exceptions.WrongValueException;

public class PublicAufgabe4 {

	ArgumentParser argumentParser = null;
	private static final int TIMEOUT = 1000;
	
	@Before
	public void setUp() throws Exception {
		argumentParser = new ArgumentParser();
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
			argumentParser.parse(args);
			assertFalse("-pflanze", argumentParser.getReportPflanze());
			assertFalse("-tiere", argumentParser.getReportTiere());
			assertFalse("-bewegung", argumentParser.getReportBewegung());
			assertFalse("-reproduce", argumentParser.getReportReproduce());
			assertFalse("-simulation", argumentParser.getSimulation());
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
			argumentParser.parse(args);
			assertTrue("-bewegung", argumentParser.getReportBewegung());
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
			argumentParser.parse(args);
			assertTrue("-pflanze", argumentParser.getReportPflanze());
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
			argumentParser.parse(strings);
		}
		catch (WrongValueException wve) {
			fail(wve.toString());
		} 
		catch (UnknownKeywordException uke) {
			
		}		
	}
	
}
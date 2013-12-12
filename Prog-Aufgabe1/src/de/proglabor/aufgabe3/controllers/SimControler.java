package de.proglabor.aufgabe3.controllers;

import java.util.HashMap;
import java.util.Observer;

import de.proglabor.aufgabe3.Welt;
import de.proglabor.aufgabe3.config.WeltConfig;
import de.proglabor.aufgabe3.gui.SimulationViewer;

public class SimControler implements SimControlerInterface{
	private Welt model;
	
	public SimControler(Welt model) {
		model = new Welt();
	}
	@Override
	public void addView(SimulationViewer v) {
		v.addControler(this);
		model.addObserver((Observer) v);
	}

	@Override
	public void simulate(int days) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int born() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int planted() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int eaten() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deaths() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void neu(HashMap<WeltConfig, Integer> parameters) {
		int width = parameters.get(WeltConfig.WIDHT);
		int height = parameters.get(WeltConfig.WIDHT);
		int widthJungle = parameters.get(WeltConfig.JUNGLE_WIDTH);
		int heightJungle = parameters.get(WeltConfig.JUNGLE_HEIGHT);
		int plantEnergy = parameters.get(WeltConfig.PLANTENERGY);
		int initialEnergy = parameters.get(WeltConfig.INITIALTENERGY);
		int reproductionEnergy = parameters.get(WeltConfig.REPRODUCTIONENERGY);
		model.initAll(width, height, widthJungle, heightJungle, plantEnergy, initialEnergy, reproductionEnergy); 
		
	}


}

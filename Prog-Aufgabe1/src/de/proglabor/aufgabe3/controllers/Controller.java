package de.proglabor.aufgabe3.controllers;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.TreeMap;

import de.proglabor.aufgabe3.Pflanze;
import de.proglabor.aufgabe3.Tier;
import de.proglabor.aufgabe3.Welt;
import de.proglabor.aufgabe3.config.StatusCode;
import de.proglabor.aufgabe3.config.WeltConfig;
import de.proglabor.aufgabe3.gui.SimView;

public class Controller {

	private Welt model;
	private SimView view;
	private StatusCode status;

	public Controller(Welt model, SimView view) {
		this.model = model;
		this.view = view;
		this.view.addController(this);
		this.status = StatusCode.OK;
	}

	public void simulate(int days) {
		status = StatusCode.SIMULATING;
		view.update(this);
		for (int i = 0; i < days; i++) {
			day();
			view.update(this);
		}
		status = StatusCode.DONE;
		view.update(this);

	}

	public void day() {
		model.randomAddPlant();
		model.randomAddPlantJungle();
		@SuppressWarnings("unchecked")
		LinkedList<Tier> tmp = (LinkedList<Tier>) model.getContainerAnimals()
				.clone();
		for (Tier tier : tmp) {
			model.animalAction(tier);
		}
	}

	public String born() {
		return Integer.toString( model.getBornCount());
	}

	public String planted() {
		return Integer.toString(model.getPlantedCount());
	}

	public String eaten() {
		return Integer.toString(model.getEatenCount());
	}

	public String deaths() {
		return Integer.toString(model.getDeadCount());
	}

	public String animals() {
		return Integer.toString(model.totalAnimals());
	}
	
	public String plants() {
		return Integer.toString(model.totalPlants());
	}
	
	public HashMap<Point, Integer> animalPosAndCount() {
		HashMap<Point, Integer> values = new HashMap<Point, Integer>();
		LinkedList<Tier> tmp = model.getContainerAnimals();
		for (Tier tier : tmp) {
			Point key = new Point(tier.getX(), tier.getY());
			if (values.containsKey(key)) {
				int counter = values.get(key);
				counter++;
				values.put(key, counter);
			} else {
				values.put(key, 1);
			}
		}
		return values;
		
	}
	
	public HashMap<Point, Integer> plantsPosAndCount() {
		HashMap<Point, Integer> values = new HashMap<Point, Integer>();
		TreeMap<Pflanze, Integer> tmp = model.getContainerPlants();
		for (Entry<Pflanze, Integer> entry : tmp.entrySet()) {
			Point key = new Point(entry.getKey().getX(), entry.getKey().getY());
			values.put(key, entry.getValue());
		}
		return values;
		
	}
	public void neu(HashMap<WeltConfig, Integer> parameters) {
		int days = parameters.get(WeltConfig.DAYS);
		int width = parameters.get(WeltConfig.WIDHT);
		int height = parameters.get(WeltConfig.WIDHT);
		int widthJungle = parameters.get(WeltConfig.JUNGLE_WIDTH);
		int heightJungle = parameters.get(WeltConfig.JUNGLE_HEIGHT);
		int plantEnergy = parameters.get(WeltConfig.PLANTENERGY);
		int initialEnergy = parameters.get(WeltConfig.INITIALTENERGY);
		int reproductionEnergy = parameters.get(WeltConfig.REPRODUCTIONENERGY);
		model.initAll(width, height, widthJungle, heightJungle, plantEnergy,
				initialEnergy, reproductionEnergy);
		
		Tier weronika = new Tier( width / 2,
				height / 2, initialEnergy);

		model.addAnimal(weronika);
		simulate(days);
	}

	public void clear() {
		model.initAnimalContainer();
		model.initPlantContainer();
		view.update(this);
	}
	
	public StatusCode getStatus() {
		return status;
	}
	
	

}

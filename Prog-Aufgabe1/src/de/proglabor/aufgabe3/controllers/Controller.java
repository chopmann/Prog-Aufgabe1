package de.proglabor.aufgabe3.controllers;

import java.util.HashMap;

import de.proglabor.aufgabe3.Welt;
import de.proglabor.aufgabe3.config.StatusCode;
import de.proglabor.aufgabe3.config.WeltConfig;
import de.proglabor.aufgabe3.gui.MainWindow;


/**
 * @author id261708
 *
 */
public class Controller implements ControlerInterface {

	private Welt model;
	private MainWindow view;
	private StatusCode status;

	/**
	 * @param model 
	 */
	public Controller(Welt model) {
		this.model = model;
		this.view = new MainWindow(model, this);
		view.createView();
		this.status = StatusCode.OK;
	}

	/* (non-Javadoc)
	 * @see de.proglabor.aufgabe3.controllers.ControlerInterface#neu(java.util.HashMap)
	 */
	@Override
	public void neu(HashMap<WeltConfig, Integer> parameters) {
		try {
			int days = parameters.get(WeltConfig.DAYS);
			int width = parameters.get(WeltConfig.WIDHT);
			int height = parameters.get(WeltConfig.HEIGHT);
			int widthJungle = parameters.get(WeltConfig.JUNGLE_WIDTH);
			int heightJungle = parameters.get(WeltConfig.JUNGLE_HEIGHT);
			int plantEnergy = parameters.get(WeltConfig.PLANTENERGY);
			int initialEnergy = parameters.get(WeltConfig.INITIALTENERGY);
			int reproductionEnergy = parameters.get(WeltConfig.REPRODUCTIONENERGY);
			model.initAll(width, height, widthJungle, heightJungle, plantEnergy,
					initialEnergy, reproductionEnergy);
			model.runSim(days);
		} catch (Exception e) {
			setStatus(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see de.proglabor.aufgabe3.controllers.ControlerInterface#clear()
	 */
	@Override
	public void clear() {
		model.initAnimalContainer();
		model.initPlantContainer();
		view.clearDisplay();
	}
	
	/**
	 * @return StatusCode
	 */
	public StatusCode getStatus() {
		return status;
	}

	@Override
	public void setStatus(String message) {
		view.setStatus(message);
		
	}

}

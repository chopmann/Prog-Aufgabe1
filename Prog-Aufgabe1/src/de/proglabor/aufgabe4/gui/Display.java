package de.proglabor.aufgabe4.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Map.Entry;
import javax.swing.JPanel;

import de.proglabor.aufgabe4.modell.Animal;
import de.proglabor.aufgabe4.modell.Plant;
import de.proglabor.aufgabe4.modell.World;
import de.proglabor.aufgabe4.config.DisplayMode;
import de.proglabor.aufgabe4.config.WeltColor;

/**
 * @author id261708
 *
 */
public class Display extends JPanel {

	private static final long serialVersionUID = -7725052913976968019L;
	private static final int GRIDSIZE = 20;
	private static final int MARGIN = 10;
	private static final int DISPLAY_WIDTH = 1060;
	private static final int DISPLAY_HEIGHT = 660;
	private static final int HELP_WIDTH = 60;
	private static final int HELP_HEIGHT = 60;
	private DisplayMode displayMode = DisplayMode.HELP;
	private World model;
	private int width;
	private int height;

	/**
     *  
     */
	public Display() {
		setPreferredSize(new Dimension(DISPLAY_WIDTH, DISPLAY_HEIGHT));
		setBackground(WeltColor.BACKGORUND_DARK);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		switch (displayMode) {
		case HELP:
			drawHelp(g);
			break;
		case SIMULATION:
			drawAnimals(g);
			drawPlants(g);
		case GRID:
			drawGrid(g);
			break;
		default:
			break;
		}
	}

	/**
	 * @param g
	 */
	private void drawHelp(Graphics g) {
		g.setColor(WeltColor.LINE);
		g.drawString("Coming Soon", HELP_WIDTH, HELP_HEIGHT);
	}

	private void drawGrid(Graphics g) {
		g.setColor(WeltColor.LINE);
		g.drawRect(MARGIN, MARGIN, width, height);

		for (int i = MARGIN; i <= width; i += GRIDSIZE) {
			g.drawLine(i, MARGIN, i, height + (GRIDSIZE / 2));
		}

		for (int i = MARGIN; i <= height; i += GRIDSIZE) {
			g.drawLine(MARGIN, i, width + (GRIDSIZE / 2), i);
		}

		g.setColor(Color.green);
		int jungleX = MARGIN + (model.getJungleLimitX1() * GRIDSIZE);
		int jungleY = MARGIN + (model.getJungleLimitY1() * GRIDSIZE);
		int jungleW = GRIDSIZE
				* (model.getJungleLimitX2() - model.getJungleLimitX1());
		int jungleH = GRIDSIZE
				* (model.getJungleLimitY2() - model.getJungleLimitY1());
		g.drawRect(jungleX, jungleY, jungleW, jungleH);

	}

	private void drawAnimals(Graphics g) {
		for (Animal animal : model.getContainerAnimals()) {
			int cellX = MARGIN + animal.getX() * GRIDSIZE;
			int cellY = MARGIN + animal.getY() * GRIDSIZE;
			g.setColor(WeltColor.ANIMAL);
			g.fillRect(cellX + GRIDSIZE / 2, cellY, GRIDSIZE / 2, GRIDSIZE);
		}
	}

	private void drawPlants(Graphics g) {
		for (Entry<Plant, Integer> entry : model.getContainerPlants()
				.entrySet()) {
			int cellX = MARGIN + entry.getKey().getX() * GRIDSIZE;
			int cellY = MARGIN + entry.getKey().getY() * GRIDSIZE;
			g.setColor(WeltColor.PLANT);
			g.fillRect(cellX, cellY, GRIDSIZE / 2, GRIDSIZE);
		}
	}

	/**
	 * Redraws the Grid
	 */
	public void clear() {
		setDisplayMode(DisplayMode.GRID);
		this.repaint();

	}

	/**
	 * @param mode the Mode
	 */
	public void setDisplayMode(DisplayMode mode) {
		this.displayMode = mode;
	}

	/**
	 * Redraws the World
	 * 
	 * @param modelWorld
	 */
	public void refresh(World modelWorld) {
		setDisplayMode(DisplayMode.SIMULATION);
		this.model = modelWorld;
		this.width = modelWorld.getWidth() * GRIDSIZE;
		this.height = modelWorld.getHeight() * GRIDSIZE;
		this.repaint();
	}
}

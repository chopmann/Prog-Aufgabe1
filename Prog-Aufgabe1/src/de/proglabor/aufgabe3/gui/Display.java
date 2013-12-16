package de.proglabor.aufgabe3.gui;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import javax.swing.JPanel;

import de.proglabor.aufgabe3.Helper;
import de.proglabor.aufgabe3.Pflanze;
import de.proglabor.aufgabe3.Tier;
import de.proglabor.aufgabe3.Welt;
import de.proglabor.aufgabe3.config.DisplayMode;
import de.proglabor.aufgabe3.config.WeltColor;

public class Display extends JPanel{
	private final int GRIDSIZE = 20;
	private final int MARGIN = 10;
    private HashMap<Point, Integer> fillCellsAnimals;
    private HashMap<Point, Integer> fillCellsPlants;
    private DisplayMode displayMode = DisplayMode.HELP;
	private float maxAnimal;
	private Welt model;
	private int width;
	private int height;

    public Display() {
        setPreferredSize(new Dimension(1060, 660));
        setBackground(WeltColor.BACKGORUND_DARK);
//        setSize(new Dimension(100, 100));
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
//        if (showHelp) {
//			drawHelp(g);
//		} else {
//			drawAnimals(g);
//			drawPlants(g);
//			drawGrid(g);
//		}


    }
    private void drawHelp(Graphics g) {
    	g.setColor(WeltColor.LINE);
    	g.drawString("Coming Soon", 25, 25);
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
        int jungleW = GRIDSIZE * (model.getJungleLimitX2() - model.getJungleLimitX1());
        int jungleH = GRIDSIZE * (model.getJungleLimitY2() - model.getJungleLimitY1());
        g.drawRect(jungleX, jungleY, jungleW, jungleH);
        
    }


    private void drawAnimals(Graphics g) {
    	for (Tier tier : model.getContainerAnimals()) {
    		 int cellX = MARGIN + tier.getX() * GRIDSIZE;
    	     int cellY = MARGIN + tier.getY() * GRIDSIZE;
    	     g.setColor(WeltColor.ANIMAL);
    	     g.fillRect(cellX + GRIDSIZE / 2 , cellY, GRIDSIZE / 2, GRIDSIZE);     
		}
    }
    
    private void drawPlants(Graphics g) {
    	for (Entry<Pflanze, Integer> entry : model.getContainerPlants().entrySet()) {
   		 int cellX = MARGIN + entry.getKey().getX() * GRIDSIZE;
   	     int cellY = MARGIN + entry.getKey().getY() * GRIDSIZE;
   	     g.setColor(WeltColor.PLANT);
   	     g.fillRect(cellX, cellY, GRIDSIZE / 2, GRIDSIZE);
		}
    }
    public void clear() {
        setDisplayMode(DisplayMode.GRID);
        this.repaint();

    }

	/**
	 * @param showHelp the showHelp to set
	 */
	public void setDisplayMode(DisplayMode mode) {
		this.displayMode = mode;
	}

	public void fillCellAnimals(HashMap<Point, Integer> animalPosAndCount) {
		this.fillCellsAnimals = animalPosAndCount;
		
	}
	
	public void fillCellPlants(HashMap<Point, Integer> plantPosAndCount) {
		this.fillCellsPlants = plantPosAndCount;
		
	}

	public void setMaxAnimal(String animals) {
		maxAnimal = Float.parseFloat(animals);	
//		System.out.println(maxAnimal);
	}

	public void refresh(Welt model) {
		setDisplayMode(DisplayMode.SIMULATION);
		this.model = model;
        this.width = model.getWidth() * GRIDSIZE;
        this.height = model.getHeight() * GRIDSIZE;
		this.repaint();
	}
	
	
//  for (Point key : fillCellsPlants.keySet()) {
//	float plant = 1.0f;
//	 int cellX = 10 + (key.x * 20);
//     int cellY = 10 + (key.y * 20);
// 	g.setColor(WeltColor.PLANT);
// 	g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, plant));
//     g.fillRect(cellX, cellY, 10, 20);
//}
//
//for (Point key : fillCellsAnimals.keySet()) {
//    int cellX = 10 + (key.x * 20);
//    int cellY = 10 + (key.y * 20);
//    
////    float animal = ((float) fillCellsAnimals.get(key)) / maxAnimal;
////    System.out.println(animal);
//
//    g.setColor(WeltColor.ANIMAL);
//    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
//    g.fillRect(cellX + 10 , cellY, 10, 20);
//}

//Draw grid
}

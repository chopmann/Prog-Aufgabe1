package de.proglabor.aufgabe3.gui;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import de.proglabor.aufgabe3.Helper;
import de.proglabor.aufgabe3.config.WeltColor;

public class Display extends JPanel{

    private List<Point> fillCells;
    private boolean showHelp = true;

    public Display() {
        fillCells = new ArrayList<>(2);
        setPreferredSize(new Dimension(1060, 660));
        setBackground(WeltColor.BACKGORUND_DARK);
//        setSize(new Dimension(100, 100));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (showHelp) {
			drawHelp(g);
		} else {
			drawSim(g);
		}


    }
    private void drawHelp(Graphics g) {
    	g.setColor(WeltColor.LINE);
    	g.drawString("Coming Soon", 25, 25);
    }
    
    private void drawSim(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int width = this.getPreferredSize().width ;
        int height = this.getPreferredSize().height ;

        
        for (Point fillCell : fillCells) {
            int cellX = 10 + (fillCell.x * 20);
            int cellY = 10 + (fillCell.y * 20);
            float plant = 1.0f;
            float animal = 1.0f;     
        	g.setColor(WeltColor.PLANT);
        	g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, plant));
            g.fillRect(cellX, cellY, 10, 20);
            g.setColor(WeltColor.ANIMAL);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, animal));
            g.fillRect(cellX + 10, cellY, 10, 20);
        }
        
        //Draw grid
        g.setColor(WeltColor.LINE);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g.drawRect(10, 10, width, height);

        for (int i = 10; i <= width; i += 20) {
            g.drawLine(i, 10, i, height + 10);
        }

        for (int i = 10; i <= height; i += 20) {
            g.drawLine(10, i, width + 10, i);
        }
    }

    public void fillCell(int x, int y) {
        fillCells.add(new Point(x, y));
    }
    public void destroy(int x, int y) {
        fillCells.remove(new Point(x, y));

    }

	/**
	 * @param showHelp the showHelp to set
	 */
	public void setShowHelp(boolean showHelp) {
		this.showHelp = showHelp;
	}
}

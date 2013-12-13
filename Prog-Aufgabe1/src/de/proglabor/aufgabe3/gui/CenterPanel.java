package de.proglabor.aufgabe3.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import de.proglabor.aufgabe3.controllers.Controller;

public class CenterPanel extends JPanel {
	
	Display display = new Display();
	JPanel staticsPanel = new JPanel();
	
	JLabel animalsAlive = new JLabel("0");
	JLabel plantsAlive = new JLabel("0");
	JLabel animalsDead = new JLabel("0");
	JLabel plantsPlanted = new JLabel("0");
	JLabel plantsEaten = new JLabel("0");
	JLabel animalsBorn = new JLabel("0");
	
	Controller controller;
	
	public CenterPanel() {
		setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(984, 690));

		// Simulation Statics

		
		staticsPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
		staticsPanel.setPreferredSize(new Dimension(this.getWidth(), 32));
		staticsPanel.setLayout(new BoxLayout(staticsPanel, BoxLayout.X_AXIS));

		Border empty = BorderFactory.createEmptyBorder(0, 0, 0, 10);

		
		animalsAlive.setHorizontalAlignment(SwingConstants.LEFT);
		animalsAlive.setBorder(empty);
		animalsAlive.setToolTipText("Current Animal Count");

		plantsAlive.setHorizontalAlignment(SwingConstants.LEFT);
		plantsAlive.setBorder(empty);
		plantsAlive.setToolTipText("Current Plant Count");

	
		animalsBorn.setHorizontalAlignment(SwingConstants.LEFT);
		animalsBorn.setBorder(empty);
		animalsBorn.setToolTipText("Animals Borned");

		
		animalsDead.setHorizontalAlignment(SwingConstants.LEFT);
		animalsDead.setBorder(empty);
		animalsDead.setToolTipText("Animal Deaths");


		plantsPlanted.setHorizontalAlignment(SwingConstants.LEFT);
		plantsPlanted.setBorder(empty);
		plantsPlanted.setToolTipText("Plants planted");

		
		plantsEaten.setHorizontalAlignment(SwingConstants.LEFT);
		plantsEaten.setBorder(empty);
		plantsEaten.setToolTipText("Plants eaten");

		try {
			Image cowImg = ImageIO.read(getClass().getResource(
					"/resources/cow.png"));
			ImageIcon cowIcon = new ImageIcon(cowImg.getScaledInstance(32, 32,
					Image.SCALE_FAST));
			animalsAlive.setIcon(cowIcon);

			Image plantImg = ImageIO.read(getClass().getResource(
					"/resources/plants.png"));
			ImageIcon plantIcon = new ImageIcon(plantImg.getScaledInstance(32,
					32, Image.SCALE_FAST));
			plantsAlive.setIcon(plantIcon);

			Image animalsBornImg = ImageIO.read(getClass().getResource(
					"/resources/cow_add.png"));
			ImageIcon animalsBornIcon = new ImageIcon(
					animalsBornImg.getScaledInstance(32, 32, Image.SCALE_FAST));
			animalsBorn.setIcon(animalsBornIcon);

			Image animalsDeadImg = ImageIO.read(getClass().getResource(
					"/resources/death.png"));
			ImageIcon animalsDeadIcon = new ImageIcon(
					animalsDeadImg.getScaledInstance(32, 32, Image.SCALE_FAST));
			animalsDead.setIcon(animalsDeadIcon);

			Image plantsPlantedImg = ImageIO.read(getClass().getResource(
					"/resources/planted.png"));
			ImageIcon plantsPlantedIcon = new ImageIcon(
					plantsPlantedImg
							.getScaledInstance(32, 32, Image.SCALE_FAST));
			plantsPlanted.setIcon(plantsPlantedIcon);

			Image plantsEatenImg = ImageIO.read(getClass().getResource(
					"/resources/eaten.png"));
			ImageIcon plantsEatenIcon = new ImageIcon(
					plantsEatenImg.getScaledInstance(32, 32, Image.SCALE_FAST));
			plantsEaten.setIcon(plantsEatenIcon);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		staticsPanel.add(animalsAlive);
		staticsPanel.add(plantsAlive);
		staticsPanel.add(animalsBorn);
		staticsPanel.add(animalsDead);
		staticsPanel.add(plantsPlanted);
		staticsPanel.add(plantsEaten);

		this.add(staticsPanel, BorderLayout.NORTH);

		this.add(display, BorderLayout.CENTER);
		

		// Status Bar
		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		statusPanel.setPreferredSize(new Dimension(this.getWidth(), 16));
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		JLabel statusLabel = new JLabel("status");
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		statusPanel.add(statusLabel);
		this.add(statusPanel, BorderLayout.SOUTH);

	}

	public void update(Controller controller) {
		animalsAlive.setText(controller.animals());
		plantsAlive.setText(controller.plants());
		animalsBorn.setText(controller.born());
		animalsDead.setText(controller.deaths());
		plantsPlanted.setText(controller.planted());
		plantsEaten.setText(controller.eaten());
		display.setShowHelp(false);
//		display.clear();
		display.setMaxAnimal(controller.animals());
		display.fillCellAnimals(controller.animalPosAndCount());
		display.repaint();

	}

}

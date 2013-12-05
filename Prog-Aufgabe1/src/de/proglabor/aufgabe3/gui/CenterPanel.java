package de.proglabor.aufgabe3.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class CenterPanel extends JPanel {
	public CenterPanel() {
		setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(984, 690));

		// Simulation Statics

		JPanel staticsPanel = new JPanel();
		staticsPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
		staticsPanel.setPreferredSize(new Dimension(this.getWidth(), 32));
		staticsPanel.setLayout(new BoxLayout(staticsPanel, BoxLayout.X_AXIS));

		Border empty = BorderFactory.createEmptyBorder(0, 0, 0, 10);

		JLabel animalsAlive = new JLabel("0");
		animalsAlive.setHorizontalAlignment(SwingConstants.LEFT);
		animalsAlive.setBorder(empty);
		animalsAlive.setToolTipText("Current Animal Count");

		JLabel plantsAlive = new JLabel("0");
		plantsAlive.setHorizontalAlignment(SwingConstants.LEFT);
		plantsAlive.setBorder(empty);
		plantsAlive.setToolTipText("Current Plant Count");

		JLabel animalsBorn = new JLabel("0");
		animalsBorn.setHorizontalAlignment(SwingConstants.LEFT);
		animalsBorn.setBorder(empty);
		animalsBorn.setToolTipText("Animals Borned");

		JLabel animalsDead = new JLabel("0");
		animalsDead.setHorizontalAlignment(SwingConstants.LEFT);
		animalsDead.setBorder(empty);
		animalsDead.setToolTipText("Animal Deaths");

		JLabel plantsPlanted = new JLabel("0");
		plantsPlanted.setHorizontalAlignment(SwingConstants.LEFT);
		plantsPlanted.setBorder(empty);
		plantsPlanted.setToolTipText("Plants planted");

		JLabel plantsEaten = new JLabel("0");
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

		JLabel holder = new JLabel("DIE WELT: COMING SOON");
		holder.setBackground(Color.orange);
		holder.setOpaque(true);
		holder.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(holder, BorderLayout.CENTER);

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

}

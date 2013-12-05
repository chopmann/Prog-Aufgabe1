package de.proglabor.aufgabe3.gui;

import de.proglabor.aufgabe3.config.WeltConfig;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

public class SidePanel extends JPanel implements ActionListener {
	JButton start = new JButton("START");
	JButton clear = new JButton("CLEAR");
	HashMap<WeltConfig, JTextField> form = new HashMap<WeltConfig, JTextField>();
	WeltConfig[] labels = WeltConfig.values();

	public SidePanel() {
		setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(273, 768));

		JPanel p = new JPanel(new SpringLayout());

		for (int i = 0; i < labels.length; i++) {
			JLabel l = new JLabel(labels[i].toString(), JLabel.TRAILING);
			p.add(l);
			JTextField textField = new JTextField(10);
			l.setLabelFor(textField);
			p.add(textField);
			form.put(labels[i], textField);
		}

		SpringUtilities.makeCompactGrid(p, labels.length, 2, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad
		this.add(p);

		try {
			Image startImg = ImageIO.read(getClass().getResource(
					"/resources/simulate.png"));
			ImageIcon startIcon = new ImageIcon(startImg.getScaledInstance(32,
					32, Image.SCALE_FAST));
			start.setIcon(startIcon);
			Image resetImg = ImageIO.read(getClass().getResource(
					"/resources/reset.png"));
			ImageIcon resetIcon = new ImageIcon(resetImg.getScaledInstance(32,
					32, Image.SCALE_FAST));
			clear.setIcon(resetIcon);
		} catch (IOException e) {
			e.printStackTrace();
		}

		start.addActionListener(this);
		this.add(start);

		clear.addActionListener(this);
		this.add(clear);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == start) {
			for (int i = 0; i < labels.length; i++) {
				String text = form.get(labels[i]).getText();
				System.out.println(labels[i].toString() + " :" + text);
				//TODO Wire Up with controller
			}
		}

		if (e.getSource() == clear) {
			for (int i = 0; i < labels.length; i++) {
				form.get(labels[i]).setText("");
			}
		}

	}
}

package de.proglabor.aufgabe3.gui;


import de.proglabor.aufgabe3.config.WeltConfig;

import javax.swing.*;

import java.awt.*;

public class SidePanel extends JPanel {
	public SidePanel() {
		setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(273, 768));
		
		JPanel p = new JPanel(new SpringLayout());
		WeltConfig[] labels = WeltConfig.values();
		int labelsLength = labels.length;
		
		for (int i = 0; i < labelsLength; i++) {
		    JLabel l = new JLabel(labels[i].toString(), JLabel.TRAILING);
		    p.add(l);
		    JTextField textField = new JTextField(10);
		    l.setLabelFor(textField);
		    p.add(textField);
		}
		
		SpringUtilities.makeCompactGrid(p,
                labelsLength, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
		this.add(p);
		
		JButton start = new JButton("START");
		start.setBounds(50, 60, 80, 30);
		this.add(start);
		
		JButton reset = new JButton("RESET");
		start.setBounds(50, 60, 80, 30);
		this.add(reset);
	
	}
}

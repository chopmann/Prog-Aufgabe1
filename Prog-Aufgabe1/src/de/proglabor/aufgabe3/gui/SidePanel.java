package de.proglabor.aufgabe3.gui;


import de.proglabor.aufgabe3.config.WeltConfig;




import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.io.IOException;

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
		JButton reset = new JButton("RESET");
//		start.setSize(100, 32);
		try {
			Image startImg = ImageIO.read(getClass().getResource("/resources/simulate.png"));			
			ImageIcon startIcon = new ImageIcon(startImg.getScaledInstance(32, 32, Image.SCALE_FAST));
			start.setIcon(startIcon);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.add(start);
		

//		start.setBounds(50, 60, 80, 30);
		this.add(reset);
	
	}
}

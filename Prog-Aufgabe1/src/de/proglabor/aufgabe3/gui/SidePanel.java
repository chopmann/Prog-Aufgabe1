package de.proglabor.aufgabe3.gui;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SidePanel extends JPanel {
	public SidePanel() {
		setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(273, 768));
		JButton quitButton = new JButton("Quit0");
		quitButton.setBounds(50, 60, 80, 30);

		JButton quitButton1 = new JButton("Quit1");
		quitButton1.setBounds(50, 60, 80, 30);
		JButton quitButton2 = new JButton("Quit2");
		quitButton2.setBounds(50, 60, 80, 30);
		JButton quitButton3 = new JButton("Quit3");
		quitButton3.setBounds(50, 60, 80, 30);
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		
		this.add(quitButton);
		this.add(quitButton1);
		this.add(quitButton2);
		this.add(quitButton3);
	}
}

package de.proglabor.aufgabe3.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class CenterPanel extends JPanel{
	public CenterPanel() {
		setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(984, 690));
		
		//Simulation Statics
		
		JPanel staticsPanel = new JPanel();
		staticsPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
		staticsPanel.setPreferredSize(new Dimension(this.getWidth(), 32));
		staticsPanel.setLayout(new BoxLayout(staticsPanel, BoxLayout.X_AXIS));
		JLabel staticsLabel = new JLabel("Statics");
		staticsLabel.setHorizontalAlignment(SwingConstants.LEFT);
		staticsPanel.add(staticsLabel);
		this.add(staticsPanel, BorderLayout.NORTH);
		
		JLabel holder = new JLabel("DIE WELT: COOMING SOON");
		holder.setBackground(Color.orange);
		holder.setOpaque(true);
		holder.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(holder, BorderLayout.CENTER);
		
		//Status Bar
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

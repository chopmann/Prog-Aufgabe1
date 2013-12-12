package de.proglabor.aufgabe3.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainWindow extends JFrame{
	
	public MainWindow() {
		setTitle("Simple Simulation");
		setSize(1366, 768);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		SidePanel sidePanel = new SidePanel();
		add(sidePanel, BorderLayout.WEST);
		
		CenterPanel centerPanel = new CenterPanel();
		add(centerPanel, BorderLayout.CENTER);
	}
	   public static void main(String[] args) {
	        
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                MainWindow ex = new MainWindow();
	                ex.setVisible(true);
	            }
	        });
	    }

}

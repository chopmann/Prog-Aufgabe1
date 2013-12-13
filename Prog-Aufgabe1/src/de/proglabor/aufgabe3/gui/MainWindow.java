package de.proglabor.aufgabe3.gui;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import de.proglabor.aufgabe3.Welt;
import de.proglabor.aufgabe3.controllers.Controller;

public class MainWindow extends JFrame implements SimView{
	
	SidePanel sidePanel;
	CenterPanel centerPanel;
	public MainWindow() {
		setTitle("Simple Simulation");
		setSize(1366, 768);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		sidePanel = new SidePanel();
		add(sidePanel, BorderLayout.WEST);
		
		centerPanel = new CenterPanel();
		add(centerPanel, BorderLayout.CENTER);
	}
	@Override
	public void update(Controller controller) {
		centerPanel.update(controller);
	}	
	@Override
    public void addController(Controller controller){
        sidePanel.addController(controller);
    }
}

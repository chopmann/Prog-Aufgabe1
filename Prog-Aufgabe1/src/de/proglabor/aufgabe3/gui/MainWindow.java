package de.proglabor.aufgabe3.gui;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import de.proglabor.aufgabe3.Welt;
import de.proglabor.aufgabe3.controllers.ControlerInterface;
import de.proglabor.aufgabe3.controllers.Controller;

public class MainWindow extends JFrame implements Observer{
	
	SidePanel sidePanel;
	CenterPanel centerPanel;
	private Welt model;
	private ControlerInterface controler;
	public MainWindow(Welt model, ControlerInterface controler) {
        this.model = model;
        this.controler = controler;
        model.addObserver(this);
	}
	/**
	 * @return the sidePanel
	 */
	public SidePanel getSidePanel() {
		return sidePanel;
	}
	/**
	 * @return the centerPanel
	 */
	public CenterPanel getCenterPanel() {
		return centerPanel;
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("Wooot! Wiring Working");
		centerPanel.updateDisplay(model);
		
	}
	public void createView() {     
		setTitle("Simple Simulation");
		setSize(1366, 768);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		sidePanel = new SidePanel();
		sidePanel.addController(controler);
		add(sidePanel, BorderLayout.WEST);
		
		centerPanel = new CenterPanel();
		add(centerPanel, BorderLayout.CENTER);
		this.setVisible(true);
		
	}
	public void clearDisplay() {
		centerPanel.clear();
		
	}
	public void setStatus(String message) {
		centerPanel.setStatus(message);
	}

}

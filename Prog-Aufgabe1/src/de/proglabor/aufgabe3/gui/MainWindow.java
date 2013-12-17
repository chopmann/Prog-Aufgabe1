package de.proglabor.aufgabe3.gui;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import de.proglabor.aufgabe3.Welt;
import de.proglabor.aufgabe3.controllers.ControlerInterface;

/**
 * @author id261708
 *
 */
public class MainWindow extends JFrame implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4154113192447898434L;
	private static final int WINDOW_WIDTH = 1366;
	private static final int WINDOW_LENGHT = 768;
	SidePanel sidePanel;
	CenterPanel centerPanel;
	private Welt model;
	private ControlerInterface controler;
	
	/** 
	 * @param model 
	 * @param controler 
	 */
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
	/**
	 *  
	 */
	public void createView() {     
		setTitle("Simple Simulation");
		setSize(WINDOW_WIDTH, WINDOW_LENGHT);
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
	/**
	 *  
	 */
	public void clearDisplay() {
		centerPanel.clear();
		
	}
	/** 
	 * @param message 
	 */
	public void setStatus(String message) {
		centerPanel.setStatus(message);
	}

}

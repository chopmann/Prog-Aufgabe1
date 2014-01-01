package de.proglabor.aufgabe4.gui;

import de.proglabor.aufgabe4.config.WeltConfig;
import de.proglabor.aufgabe4.controller.ControllerInterface;
import de.proglabor.aufgabe4.utils.SpringUtilities;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author id261708
 */
public class SidePanel extends JPanel implements ActionListener {
    /**
     *
     */
    private static final long serialVersionUID = -957482444761875896L;
    private static final int FIELD_LENGTH = 10;
    private static final int PANEL_WIDTH = 273;
    private static final int PANEL_HEIGHT = 768;
    private static final int ICON_RESOLUTION = 32;
    private JButton start = new JButton("START");
    private JButton clear = new JButton("CLEAR");
    private HashMap<WeltConfig, JTextField> form = new HashMap<WeltConfig, JTextField>();
    private ControllerInterface controller;
    private WeltConfig[] labels = WeltConfig.values();

    /**
     *
     */
    public SidePanel() {
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        JPanel p = new JPanel(new SpringLayout());

        for (int i = 0; i < labels.length; i++) {
            JLabel l = new JLabel(labels[i].toString(), JLabel.TRAILING);
            p.add(l);
            JTextField textField = new JTextField(FIELD_LENGTH);
            textField.setText("" + WeltConfig.values()[i].getDefaultValue());
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
            ImageIcon startIcon = new ImageIcon(startImg.getScaledInstance(
                    ICON_RESOLUTION, ICON_RESOLUTION, Image.SCALE_FAST));
            start.setIcon(startIcon);
            Image resetImg = ImageIO.read(getClass().getResource(
                    "/resources/reset.png"));
            ImageIcon resetIcon = new ImageIcon(resetImg.getScaledInstance(
                    ICON_RESOLUTION, ICON_RESOLUTION, Image.SCALE_FAST));
            clear.setIcon(resetIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.add(start);
        start.addActionListener(this);
        clear.addActionListener(this);
        clear.setEnabled(false);
        this.add(clear);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            boolean ok = true;
            HashMap<WeltConfig, Integer> parameters = new HashMap<WeltConfig, Integer>();
            try {
                for (int i = 0; i < labels.length; i++) {
                    String text = form.get(labels[i]).getText();
                    System.out.println(labels[i].toString() + " :" + text);
                    parameters.put(labels[i], Integer.parseInt(text));
                }
            } catch (Exception e2) {
                System.out.println("WTF Something Missing or wrong input");
                ok = false;
                controller.setStatus("WTF Something Missing or wrong input");
            }
            if (ok) {
                controller.neu(parameters);
                clear.setEnabled(true);
            }
        }
        if (e.getSource() == clear) {
            controller.clear();
        }

    }

    /**
     * @param control
     */
    public void addController(ControllerInterface control) {
        this.controller = control;

    }

}

package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import Tool.Tool;

import devintAPI.Preferences;
import fonctionnement.objet.Cabane;

@SuppressWarnings("serial")
public class IHMCabane extends JButton {
	private Cabane cabane;
	private ActionListener parent;
	private ArrayList<ImageIcon> icones = new ArrayList<ImageIcon>();
	private ArrayList<JLabel> etatConstruction = new ArrayList<JLabel>();
	private String orientation;
	
	IHMCabane(Cabane cabane, String orientation, Color couleur, ActionListener parent) {
		super();
		this.cabane = cabane;
		this.parent = parent;
		this.orientation = orientation;
		
		icones.add(new ImageIcon("../ressources/images//icons/toit.JPG"));
		icones.add(new ImageIcon("../ressources/images//icons/tonneau.JPG"));
		icones.add(new ImageIcon("../ressources/images//icons/filet.JPG"));
		icones.add(new ImageIcon("../ressources/images//icons/atelier.JPG"));
		
		icones.add(new ImageIcon("../ressources/images//icons/voile.JPG"));
		icones.add(new ImageIcon("../ressources/images//icons/coque.JPG"));
		icones.add(new ImageIcon("../ressources/images//icons/mat.JPG"));
		icones.add(new ImageIcon("../ressources/images//icons/gouvernail.JPG"));		
		
		Preferences pref = Preferences.getData();
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(110, 110));
		setBackground(pref.getCurrentBackgroundColor());
		setBorder(BorderFactory.createLineBorder(couleur, 5));
		
		setOpaque(true);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		for(int i=0; i<icones.size(); i++) {
			if(orientation.equals("horizontal")) { gridBagConstraints.gridx = i%4; gridBagConstraints.gridy = i/4;}
			else if(orientation.equals("vertical")) { gridBagConstraints.gridy = i%4; gridBagConstraints.gridx = i/4;}
			
			etatConstruction.add(new JLabel(icones.get(i)));
			etatConstruction.get(i).setPreferredSize(new Dimension(45, 45));
			etatConstruction.get(i).setVisible(false);
			this.add(etatConstruction.get(i), gridBagConstraints);
		}
	}
	
	public void updateCabane(String s) {
		switch(s) {
		case "toit" : etatConstruction.get(0).setVisible(true); break;
		case "tonneau" : etatConstruction.get(1).setVisible(true); break;
		case "filet" : etatConstruction.get(2).setVisible(true); break;
		case "atelier": etatConstruction.get(3).setVisible(true); break;

		case "voile" : etatConstruction.get(4).setVisible(true); break;
		case "coque" : etatConstruction.get(5).setVisible(true); break;
		case "mat" : etatConstruction.get(6).setVisible(true); break;
		case "gouvernail" : etatConstruction.get(7).setVisible(true); break;
		}

	}
}

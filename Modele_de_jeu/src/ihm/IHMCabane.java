package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import devintAPI.Preferences;
import fonctionnement.objet.Cabane;

@SuppressWarnings("serial")
public class IHMCabane extends JButton {
	private Cabane cabane;
	private ActionListener parent;
	
	IHMCabane(Cabane cabane, ActionListener parent) {
		super();
		this.cabane = cabane;
		this.parent = parent;
		Preferences pref = Preferences.getData();
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(110, 110));
		setBackground(pref.getCurrentBackgroundColor());
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setOpaque(true);
		
		
		
	}
}

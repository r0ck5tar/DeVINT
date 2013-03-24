package ihm;

import java.awt.BorderLayout;
import fonctionnement.objet.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import devintAPI.Preferences;

@SuppressWarnings("serial")
public class IHMCase extends JButton{

	private Objet typeRessource; 
	
	IHMCase(ActionListener parent, Objet typeRessource ) {
		super(typeRessource.get);
		Preferences pref = Preferences.getData();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(61, 61));
		setBackground(pref.getCurrentBackgroundColor());
		setForeground(pref.getCurrentForegroundColor());
		setBorder(BorderFactory.createLineBorder(Color.YELLOW));
		setOpaque(true);
		this.addActionListener((ActionListener) parent);
	}
	
	
}

package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import devintAPI.Preferences;


@SuppressWarnings("serial")
public class IHMCase extends JButton{

<<<<<<< HEAD
	private Objet typeRessource; 
	
	IHMCase(ActionListener parent, Objet typeRessource ) {
		super("Case"); //Besoin de récupérer le nom de la ressource sur la case, sauf qu'on ne sait pas qu'elle type de ressource c'est
=======
	IHMCase(ActionListener parent) {
		super("Case");
>>>>>>> f80616f3b7eb668c200c24d179732a01f7a0e3b6
		Preferences pref = Preferences.getData();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(61, 61));
		setBackground(pref.getCurrentBackgroundColor());
		setForeground(pref.getCurrentForegroundColor());
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setOpaque(true);
		this.addActionListener((ActionListener) parent);
	}
	
	
}

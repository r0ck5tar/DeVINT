package ihm;

<<<<<<< HEAD
import java.awt.Component;

import javax.swing.*; 

import fonctionnement.objet.Joueur;

public class IHMInfoJoueur extends JPanel{

	private JLabel infos; 
	//private Joueur joueur; 
	
	public IHMInfoJoueur (Joueur joueur){
		this.infos= new JLabel(joueur.getNom()); 
	}
	
	
=======
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import devintAPI.Preferences;


public class IHMInfoJoueur extends JButton{
	
	public IHMInfoJoueur(ActionListener parent) {
		super("Info Joueur");
		Preferences pref = Preferences.getData();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(320, 410));
		setBackground(pref.getCurrentBackgroundColor());
		setForeground(pref.getCurrentForegroundColor());
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setOpaque(true);
		this.addActionListener((ActionListener) parent);
	}
>>>>>>> origin/HakimIHM
}

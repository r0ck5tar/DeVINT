package ihm;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import devintAPI.Preferences;
import fonctionnement.objet.Joueur;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import devintAPI.Preferences;


public class IHMInfoJoueur extends JButton{
	
	public IHMInfoJoueur(Joueur jouer, ActionListener parent) {
		super(jouer.getNom());
		Preferences pref = Preferences.getData();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(320, 410));
		setBackground(pref.getCurrentBackgroundColor());
		setForeground(pref.getCurrentForegroundColor());

		setBorder(BorderFactory.createLineBorder(Color.YELLOW));

		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setOpaque(true);
		this.addActionListener((ActionListener) parent);
	}
}

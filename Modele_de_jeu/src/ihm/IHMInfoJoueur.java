package ihm;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import devintAPI.Preferences;
import fonctionnement.objet.Joueur;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import devintAPI.Preferences;


public class IHMInfoJoueur extends JTextArea{
	
	public IHMInfoJoueur(Joueur jouer) {
		super(jouer.getNom());
		Preferences pref = Preferences.getData();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(320, 410));
		setBackground(pref.getCurrentBackgroundColor());
		setForeground(pref.getCurrentForegroundColor());
		setFont(new Font("Tahoma", 1, 56));

		setBorder(BorderFactory.createLineBorder(Color.YELLOW));

		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setOpaque(true);
	}
}
